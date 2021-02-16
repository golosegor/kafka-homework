#### Execute scripts k8s-configure.sh

#### in new terminal execute

`kubectl port-forward service/prometheus-operated 9090:9090` to get access to metrics

## To create far jar

`clean build buildFatJar`

## To create docker image

`clean build dockerBuildImage`