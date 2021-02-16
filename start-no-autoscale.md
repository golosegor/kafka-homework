1) ensure all k8s infrastructure up and running
2) download kafka client to ubuntu `/opt/kafka_2.12-2.7.0/bin` and execute following commands
`/opt/kafka_2.12-2.7.0/bin/kafka-topics.sh --bootstrap-server  172.24.237.20:9094 --list`
3) create 3 partition topics 
   for inbound: 
   `/opt/kafka_2.12-2.7.0/bin/kafka-topics.sh \
   --create --partitions 3 --topic inScalingTopic \
   --bootstrap-server  172.24.237.20:9094 --replication-factor 1`

for outbound:
   `/opt/kafka_2.12-2.7.0/bin/kafka-topics.sh \
   --create --partitions 1 --topic outScalingTopic \
   --bootstrap-server  172.24.237.20:9094 --replication-factor 1`

ensure topics are created:
`/opt/kafka_2.12-2.7.0/bin/kafka-topics.sh --bootstrap-server  172.24.237.20:9094 --list`


3) build project `clean build fatJar` 
4) execute in bash

start consumer

`
java \
-cp build/libs/streaming-fatjar-1.0-SNAPSHOT.jar \
-Dcom.sun.management.jmxremote.port=5555 \
-Dcom.sun.management.jmxremote.authenticate=false \
-Djava.rmi.server.hostname=172.24.237.20 \
-Dcom.sun.management.jmxremote.ssl=false \
com.home.streaming.App
`

start producer:
`
java \
-cp build/libs/streaming-fatjar-1.0-SNAPSHOT.jar \
com.home.streaming.Sender
`