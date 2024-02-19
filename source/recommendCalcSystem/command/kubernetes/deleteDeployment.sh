K8S_SERVICE_NAME=`cat ./command/kubernetes/value/k8s_service_name.txt`

kubectl delete deployment.apps/$K8S_SERVICE_NAME