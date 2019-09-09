package kafka.template.writer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class SendMessageSync {
    public static void main(String[] args) {

        // 创建生产者
        Properties kafkaProps = new Properties();
        // 指定broker（这里指定了2个，1个备用），如果你是集群更改主机名即可，如果不是只写运行的主机名
        kafkaProps.put("bootstrap.servers", "slave1:9092,slave2:9092,slave3:9092");
        kafkaProps.put("group.id", "CountryCounter");	// 消费者群组
        // 设置序列化（自带的StringSerializer，如果消息的值为对象，就需要使用其他序列化方式，如Avro ）
        kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // 实例化出producer
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(kafkaProps);

        // 创建ProducerRecord对象
        ProducerRecord<String, String> record = new ProducerRecord<String, String>("CustomerCountry", "Hello", "Kafka-Get");
        try {
            // 发送消息，调用get() 方法等待
            producer.send(record).get();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }
}
