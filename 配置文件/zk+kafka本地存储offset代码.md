### zk

# 创建新的topic
bin/kafka-toplics.sh --create --topic bigdata --zookeeper hadoop102:2181 --partitions 2 --replication-factor 2

# 启动生产者
bin/kafka-console-producer.sh --broker-list hadoop102:9092 --topic bigdata
>hello

# 启动消费者
bin/kafka-console-consumer.sh --zookeeper hadoop102:2181 --topic bigdata

brokers > kafka 集群
ls /brokers/ids
ls /brokers/topics
ls /consumers   消费者组，每加一个topic,加一个组。
group+topic+partition

ls /consumers/console-consumer-88502/offsets


# 开启系统消费命令
bin/kafka-console-consumer.sh --topic __consumer_offsets -- zookeeper hadoop102:2181 --formatter "kafka.coordinator.group.GroupMetadataManager\$OffsetsMessageForm atter" --consumer.config config/consumer.properties --from- beginning

