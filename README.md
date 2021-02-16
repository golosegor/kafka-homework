#### Execute scripts k8s-configure.sh

#### in new terminal execute

`kubectl port-forward service/prometheus-operated 9090:9090` to get access to metrics

## To create far jar

`java -cp build/libs/kafka-homework-all-1.0-SNAPSHOT.jar -Dcom.sun.management.jmxremote.port=5554 -Dcom.sun.management.jmxremote.authenticate=false -Djava.rmi.server.hostname=172.24.237.20 -Dcom.sun.management.jmxremote.ssl=false com.home.streaming.App`
`clean build buildFatJar`

## To create docker image

`clean build dockerBuildImage`