# 현재 디렉토리의 코드를 기반으로 도커 이미지 빌드를 수행(테스트용)

DOCKER_IMAGE_NAME=`echo $(cat ./command/docker/value/docker_image_name.txt)-test`
DOCKER_VERSION="test"

docker-compose down

docker image rm $DOCKER_IMAGE_NAME:$DOCKER_VERSION