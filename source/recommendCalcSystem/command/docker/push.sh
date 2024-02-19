# 현재 디렉토리에서 생성된 이미지를 외부 저장소로 푸시

DOCKER_USER_NAME=`cat ./command/docker/value/docker_user_name.txt`
DOCKER_IMAGE_NAME=`cat ./command/docker/value/docker_image_name.txt`
DOCKER_VERSION=`cat ./command/docker/value/docker_version.txt`

docker push $DOCKER_USER_NAME/$DOCKER_IMAGE_NAME:$DOCKER_VERSION