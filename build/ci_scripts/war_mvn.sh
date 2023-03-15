#!/bin/bash

#params
###################################################################################################
if [ -z "${NAME}" ]; then
    NAME="aeca"
fi

if [ -z "${MAJOR}" ]; then
    MAJOR="0"
fi

if [ -z "${MINOR}" ]; then
    MINOR="0"
fi

if [ -z "${BUILD}" ]; then
    BUILD="0"
fi

if [ -z "${VERSION}" ]; then
    VERSION="${MAJOR}.${MINOR}.${BUILD}"	
fi

if [ -z "$PACKAGE_FULLNAME" ]; then
    PACKAGE_FULLNAME="${NAME}_${VERSION}"
fi

echo "Building distribution of ${PACKAGE_FULLNAME}"
echo "there will be local build sometimes..."

######################################################################################################


echo "Building .war lib for ${PACKAGE_FULLNAME}"

CURRENT_DIR=$(pwd)
echo "Working at ${CURRENT_DIR}"

cd ${CURRENT_DIR}

mvn clean install

cd ${CURRENT_DIR}/target/
mv aeca-email-1.0.0.war aeca-email-${VERSION}.war
cp aeca-email-${VERSION}.war /opt/lib

#sshpass -p "qwerty123" scp /opt/lib/aeca-email* adm123@192.168.111.159:/opt/lib/

# recreate dir for artifacts
#if ! [ -d "${ARTIFACTS_DIR}" ]; then
#    mkdir --verbose ${ARTIFACTS_DIR}    
#fi

# copy artifacts to ARTIFACTS_DIR
#find ${BUILD_DIR} -name "*.war" -exec cp {} ${ARTIFACTS_DIR}/ \;
