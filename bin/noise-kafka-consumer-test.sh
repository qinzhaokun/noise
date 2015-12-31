#! /bin bash

cd /usr/local/kafka_2.11-0.9.0.0

bin/kafka-console-consumer.sh --zookeeper localhost:2181 --topic test --from-beginning