K8S_IMAGE_NAME=`cat ./command/kubernetes/value/k8s_image_name.txt`
K8S_IMAGE_USERNAME=`cat ./command/kubernetes/value/k8s_image_username.txt`
K8S_IMAGE_VERSION=`cat ./command/kubernetes/value/k8s_image_version.txt`
K8S_PORT=`cat ./command/kubernetes/value/k8s_port.txt`
K8S_SERVICE_NAME=`cat ./command/kubernetes/value/k8s_service_name.txt`
K8S_TARGET_PORT=`cat ./command/kubernetes/value/k8s_target_port.txt`

sed "s/\$k8s_image/$K8S_IMAGE_USERNAME\/$K8S_IMAGE_NAME:$K8S_IMAGE_VERSION/g" ./kubernetes/deployment.yaml |\
sed "s/\$k8s_port/$K8S_PORT/g" |\
sed "s/\$k8s_service_name/$K8S_SERVICE_NAME/g" |\
sed "s/\$k8s_target_port/$K8S_TARGET_PORT/g" |\
sed "s/\$COLLECTED_DATA_HOST_VALUE/collected-data/g" |\
sed "s/\$COLLECTED_DATA_PORT_VALUE/8080/g" |\
kubectl apply -f -