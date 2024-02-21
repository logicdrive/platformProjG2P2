# 테스트용으로 이미지를 빌드하고 관련 컨테이너를 실행시킴

DOCKER_IMAGE_NAME=`echo $(cat ./commands/docker/values/docker_image_name.txt)-test`
DOCKER_VERSION="test"

docker build --no-cache -t "$DOCKER_IMAGE_NAME:$DOCKER_VERSION" .

docker-compose up