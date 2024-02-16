# 현재 디렉토리에서 생성된 이미지로 컨테이너 생성

DOCKER_USER_NAME=`cat ./command/docker/value/docker_user_name.txt`
DOCKER_IMAGE_NAME=`cat ./command/docker/value/docker_image_name.txt`
DOCKER_VERSION=`cat ./command/docker/value/docker_version.txt`
DOCKER_APP_PORT=`cat ./command/docker/value/docker_app_port.txt`
DOCKER_IMAGE_PORT=`cat ./command/docker/value/docker_image_port.txt`


docker run --name ${DOCKER_IMAGE_NAME}_con \
    -e AWS_ACCESS_KEY=$AWS_ACCESS_KEY \
    -e AWS_SECRET_ACCESS_KEY=$AWS_SECRET_ACCESS_KEY \
    -e AWS_BUCKET_NAME=book-generator \
    -e AWS_REGION_CODE=ap-northeast-2 \
    -e GOOGLE_API_KEY=$GOOGLE_API_KEY \
    -e GOOGLE_PROJECT_CX=$GOOGLE_PROJECT_CX \
    -e OPENAI_API_KEY=$OPENAI_API_KEY
    -p $DOCKER_IMAGE_PORT:$DOCKER_APP_PORT $DOCKER_USER_NAME/$DOCKER_IMAGE_NAME:$DOCKER_VERSION