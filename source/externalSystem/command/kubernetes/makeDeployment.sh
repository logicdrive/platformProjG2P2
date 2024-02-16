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
sed "s/\$AWS_ACCESS_KEY_VALUE/$AWS_ACCESS_KEY/g" |\
sed "s/\$AWS_SECRET_ACCESS_KEY_VALUE/$AWS_SECRET_ACCESS_KEY/g" |\
sed "s/\$AWS_BUCKET_NAME_VALUE/book-generator/g" |\
sed "s/\$AWS_REGION_CODE_VALUE/ap-northeast-2/g" |\
sed "s/\$GOOGLE_API_KEY_VALUE/$GOOGLE_API_KEY/g" |\
sed "s/\$GOOGLE_PROJECT_CX_VALUE/$GOOGLE_PROJECT_CX/g" |\
sed "s/\$OPENAI_API_KEY_VALUE/$OPENAI_API_KEY/g" |\
kubectl apply -f -