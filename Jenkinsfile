@Library('ru.aladdin.jenkins.bhl') _

def release     = '1.2.0'
def namePackage = 'aeca'
def commitId    =  null
def repoECA     = 'http://10.0.1.191/osinit/eca.git'

addons.setBuildNumberByNumerator(this, "aeca_1")

properties(
    [
        disableConcurrentBuilds(),
        buildDiscarder(
            logRotator(
                artifactNumToKeepStr: '5',
                numToKeepStr: '5'
            )
        ),
        pipelineTriggers([pollSCM('0 1 * * *')]),
        parameters(
            [
                booleanParam( name: 'PROMOTE_BUILD', defaultValue: true   )
            ]
        )
    ]
)

env.ECA_BRANCH = "${env.BRANCH_NAME}"
env.MAJOR      = release.find(/[^.]*/)
env.MINOR      = release.find(/[0-9]+\.([0-9]+)$/)
env.NAME       = "${namePackage}"
env.VERSION    = VersionNumber([versionNumberString: "${env.MAJOR}.${env.MINOR}.${env.BUILD_NUMBER}", worstResultForIncrement: 'NOT_BUILT' ])

def checkoutSource(repoECA, branch) {
    checkout([
        $class: 'GitSCM',
        userRemoteConfigs: [[url:    repoECA,
            credentialsId: 'df4c4e6b-b3f1-4c17-9d94-f1f2cf35cdd9']],
            branches:          [[name:   "${branch}"]],
            extensions:        [[$class: 'CloneOption', timeout: 60], [$class: 'GitLFSPull']]
    ])
    commitId = sh(returnStdout: true, script: 'git rev-parse HEAD').trim()
    return commitId
}

def createPackage(workspace, scriptName, repoECA, branch) {
    env.BUILD = env.BUILD_NUMBER
    sh "rm -rf ${env.WORKSPACE}/_${workspace}"
    ws("${env.WORKSPACE}/_${workspace}") {
        withEnv(["HOME=${env.WORKSPACE}"]) {
            checkoutSource(repoECA, branch)
            unstash name: 'rpm-pkg'
            sh "export version=\$VERSION; bash -x ${env.WORKSPACE}/${scriptName}"
            archiveArtifacts artifacts: 'artifacts/**', onlyIfSuccessful: true
        }
    }
}

try {
    stage("building") {
        node("osinit-eca"){
            step([$class: 'WsCleanup'])
            env.BUILD = env.BUILD_NUMBER
            commitId = checkoutSource(repoECA, env.ECA_BRANCH)
            sh "export HOME=$env.WORKSPACE; export version=\$VERSION; bash -xe $WORKSPACE/build/ci_scripts/build_npm.sh"
            sh "if [ -f \"${WORKSPACE}/CHANGELOG.md\" ]; then cp \"${WORKSPACE}/CHANGELOG.md\" \"${WORKSPACE}/artifacts/\"; fi"
            sh "if [ -f \"${WORKSPACE}/mscs2aeca.ps1\" ]; then cp \"${WORKSPACE}/mscs2aeca.ps1\" \"${WORKSPACE}/artifacts/\"; fi"
            stash includes: 'artifacts/**' , name : 'rpm-pkg'
        }
    }

    stage("packaging") {
        parallel([
            rpm: {
                node("osinit-eca"){
                    parallel([
                        'installer_AECA-CA-rpm': {
                            createPackage('installer_AECA-CA-rpm', 'build/ci_scripts/installer_AECA-CA-rpm.sh', repoECA, env.ECA_BRANCH)
                        },
                        'installer_AECA-Va_rpm': {
                            createPackage('installer_AECA-Va_rpm', 'build/ci_scripts/installer_AECA-Va_rpm.sh', repoECA, env.ECA_BRANCH)
                        },
                        'createrpm': {
                            createPackage('createrpm', 'build/ci_scripts/createrpm.sh', repoECA, env.ECA_BRANCH)
                        }
                    ])
                }
            },
            deb: {
                node("eca-al_1_6"){
                    parallel([
                        'installer_AECA-CA_deb': {
                            createPackage('installer_AECA-CA_deb', 'build/ci_scripts/installer_AECA-CA_deb.sh', repoECA, env.ECA_BRANCH)
                        },
                        'installer_AECA-VA_deb': {
                            createPackage('installer_AECA-VA_deb', 'build/ci_scripts/installer_AECA-VA_deb.sh', repoECA, env.ECA_BRANCH)
                        },
                        'createdeb': {
                            createPackage('createdeb', 'build/ci_scripts/createdeb.sh', repoECA, env.ECA_BRANCH)
                        }
                    ])
                }
            }
        ])
    }

    stage("packaging") {
        parallel([
            deb: {
                node("eca-al_1_6"){
                    parallel([
                        'patching_aecaVa': {
                            createPackage('patching_aecaVa', 'build/ci_scripts/patching_aecaVa.sh', repoECA, env.ECA_BRANCH)
                        },
                        'patching_aecaCa': {
                            createPackage('patching_aecaCa', 'build/ci_scripts/patching_aecaCa.sh', repoECA, env.ECA_BRANCH)
                        }
                    ])
                }
            }
        ])
    }

    stage("Promote") {
        if ( env.PROMOTE_BUILD == 'true' ) {
            node('master') {
                step([$class: 'WsCleanup'])
                withCredentials([string(credentialsId: '9facaf7a-a087-4d5b-891e-f8eea1eb1dd8', variable: 'GITLAB_OSINIT_API_TOKEN')]){
                    def response = httpRequest (
                        url: 'http://10.0.1.191/api/v4/projects/osinit%2FECA/repository/tags?tag_name=v' + env.VERSION + '&ref=' + commitId + '&message=Release%20tag%20for%20version%20' + env.VERSION,
                        customHeaders: [[
                            name: 'PRIVATE-TOKEN',
                            value: env.GITLAB_OSINIT_API_TOKEN
                        ]],
                        httpMode: 'POST',
                        validResponseCodes: '201'
                    )
                }
                copyArtifacts fingerprintArtifacts: true, projectName: "/${JOB_NAME}", selector: specific('${BUILD_NUMBER}')
                cifsPublisher(
                    publishers: [[
                        configName: 'TestArifacts',
                        transfers: [[
                            cleanRemote: false,
                            excludes: '',
                            flatten: false,
                            makeEmptyDirs: false,
                            noDefaultExcludes: false,
                            patternSeparator: '[, ]+',
                            remoteDirectory: "AladdinECA/${env.VERSION}",
                            remoteDirectorySDF: false, 
                            removePrefix: 'artifacts', 
                            sourceFiles: '**'
                        ]],
                        usePromotionTimestamp: false,
                        useWorkspaceInPromotion: false,
                        verbose: false
                    ]]
                )
                step([$class: 'WsCleanup'])
                addBadge(icon: 'success.gif', text: 'Promote success')
            }
        }
    }
    stage("Autotests") {
        node('master') {
            step([$class: 'WsCleanup'])
            withCredentials([usernameColonPassword(credentialsId: '2cddd379-9aae-4acb-914b-32b7df5e2b1f', variable: 'USERPASS'),
                         usernamePassword(credentialsId: '81b359bf-2b1d-419a-8bf6-b6f6e66c1f75', usernameVariable: 'TEST_USER', passwordVariable: 'TEST_PWD')]) {
                def url      = "http://${USERPASS}@git-srv.aladdin.ru/jenkins/job/AutoTest/job/QAJenkins/job/QAJenkins%2FAECAStandDeploy/job/master"
                def query    = "BUILD_ID=${BUILD_ID}\\&AECA_VER=${VERSION}\\&BRANCH=${BRANCH_NAME}"
                sh "curl -X POST ${url}/buildWithParameters?${query}"
            }
        }
    }
    stage("SAST analyze") {
        node('master') {
            build (
                job: '/zSAST/osinit_ECA/master',
                wait: false,
                parameters: [
                    string( name: 'UPSTREAM_VERSION',    value: env.VERSION ),
                    string( name: 'UPSTREAM_JOB_NAME',    value: env.JOB_NAME ),
                    string( name: 'UPSTREAM_GIT_BRANCH',    value: env.BRANCH_NAME )               
                ]
            )
        }
    }
    stage("Send message about successfull build") {
        node('master') {
            emailext(
                to: 'd.polushin@aladdin.ru',
                subject: "AECA ${JOB_NAME} Successfully Builded!!!",
                body: """
                    AECA ${JOB_NAME} Successfully Builded!!!

                    URL: ${BUILD_URL}
                """,
                attachLog: false
            )   
        }
    }
    stage("CleanWS") {
        node('master') {
            cleanWs()
        }
    }
} catch (Exception err) {
    println err.message
    currentBuild.result = 'FAILURE'
    emailext(
        to: 'd.polushin@aladdin.ru',
        subject: "Build ${JOB_NAME} FAILED!!!",
        body: """
            Build ${JOB_NAME} FAILED!!!

            Ошибка: "${err.message}"
            URL: ${BUILD_URL}
        """,
        attachLog: false
    )
}
