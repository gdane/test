#!/bin/bash
if [ -z "${BUILD}" ]; then
    BUILD="0"
fi

curl --request PUT --header "PRIVATE-TOKEN:${CI_API_TOKEN}" "${CI_API_V4_URL}/projects/${CI_PROJECT_ID}/variables/BUILD_VERSION" --form "value=${BUILD}"

