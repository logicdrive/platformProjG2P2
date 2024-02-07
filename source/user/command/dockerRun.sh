# 테스트용으로 이미지를 빌드하고 관련 컨테이너를 실행시킴

DOCKER_IMAGE_NAME=`echo $(cat ./command/docker/value/docker_image_name.txt)-test`
DOCKER_APP_PORT=`cat ./command/docker/value/docker_app_port.txt`
DOCKER_IMAGE_PORT=`cat ./command/docker/value/docker_image_port.txt`
DOCKER_VERSION="test"

rm -rf target
mvn package -B -D maven.test.skip=true
docker build --no-cache -t "$DOCKER_IMAGE_NAME:$DOCKER_VERSION" .

docker-compose up