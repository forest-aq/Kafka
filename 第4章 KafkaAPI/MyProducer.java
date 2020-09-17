public class MyProducer{

    public static void main(String[] args){

        // 1. 创建Kafka生产者的配置信息
        Properties prop = new Properties();

        // 2. 指定连接的kafka集群
        prop.put("bootstrap servers", "hadoop102:9092");

        // 3. ACK应答级别
        prop.put("asks", "all");

        // 4. 重试次数
        prop.put("retries", 1);

        // 5. 批次大小   一次发送大小数据 16k
        prop.put("batch.size", 16384);

        // 6. 等待时间
        prop.put("linger.ms", 1)

        // 7. RecordAccumulator缓冲区大小
        prop.put("buffer.memory", 33554432);   # 32M

        // 8. k, v 都要序列化
        prop.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")

        prop.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

        // 9. 创建生产者对象
        KafkaProducer(String, String) producer = new KafkaProducer<String, String>(properties);

        // 10.发送数据
        for(int i=0; i<10; i++){
            producer.send(new ProducerRecord<String, String>("first", "atguigu--"+i)) ;
        }

        // 11.关闭资源
        producer.close();

        // 假如  6. 等待时间 没有到 1ms, 或者没到16k, 那么 关闭资源 会清空内存。
    }

}


/*

# 控制台启动消费者
cd kafka
bin/kafka-console-consumer.sh --zookeeper hadoop102:2181 --topic first

# 生产者是批量发送 + 轮询发送 + 先消费0分区，在消费1分区
# 显示结果0246813579

*/