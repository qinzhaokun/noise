#! /bin bash

cd /usr/local/kafka_2.11-0.9.0.0

bin/kafka-topics.sh --list --zookeeper localhost:2181

bin/kafka-console-producer.sh --broker-list localhost:9092 --topic noise
This is a message
This another message
