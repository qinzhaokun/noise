#! /bin bash

#install zookeeper and cd into zookeeper directory
echo "please install zookeeper in /usr/local/zookeeper-3.4.7"
cd /usr/local/zookeeper-3.4.7

#start zookeeper
bin/zkServer.sh start

#install kafka and cd into kafka directory
echo "please install kafka in /usr/local/kafka_2.11-0.9.0.0"
cd /usr/local/kafka_2.11-0.9.0.0

#start kafka, modify server.properties: port=9092
bin/kafka-server-start.sh config/server.properties

#create noise topic
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic noise

