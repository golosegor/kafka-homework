#### Execute scripts k8s-configure.sh

#### in new terminal execute

`kubectl port-forward service/prometheus-operated 9090:9090` to get access to metrics

## To create far jar

`clean build buildFatJar`

## Start local docker repo
`docker run -d -p 5000:5000 --restart=always --name registry registry:2`

## To create docker image

`clean build dockerBuildImage`

## push to local fake repository

docker tag com.home.streaming/kafka-homework:1.0-snapshot 172.24.237.20:5000/com.home.streaming/kafka-homework:1.0-snapshot
docker push 172.24.237.20:5000/com.home.streaming/kafka-homework:1.0-snapshot

