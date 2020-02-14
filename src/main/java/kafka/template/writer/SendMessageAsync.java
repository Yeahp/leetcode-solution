package kafka.template.writer;

import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class SendMessageAsync {
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
        ProducerRecord<String, String>  record = new ProducerRecord<String, String>("CustomerCountry", "Hello", "Kafka-Callback");
        try {
            // 发送消息
            SendMessageAsync sMCallback = new SendMessageAsync();
            producer.send(record, sMCallback.new DemoProducerCallback());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }
    private class DemoProducerCallback implements Callback {

        @Override
        public void onCompletion(RecordMetadata recordMetadata, Exception e) {
            // TODO Auto-generated method stub
            if (e != null) {
                // 如果消息发送失败，打印异常
                e.printStackTrace();
            } else {
                System.out.println("成功发送消息！");
            }
        }

    }

}
