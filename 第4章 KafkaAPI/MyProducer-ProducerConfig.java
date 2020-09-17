public class MyProducer{

    public static void main(String[] args){

        // 1. 创建Kafka生产者的配置信息
        Properties prop = new Properties();

        // 2. 指定连接的kafka集群
        prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "hadoop102:9092");

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

ProducerConfig 类中 定义了所有Producer参数。
# acks = 0  producer 不等待 broker 的 ack
# acks = 1  producer 等待 broker 的 ack，partition 的 leader 落盘成功后返回 ack.
# acks=-1   producer 等待 broker 的 ack，partition 的 leader 和 follower 全部落盘成功后才 返回 ack。  等待ISR中的副本。

*/