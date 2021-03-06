package kafka.template.writer;


import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;


public class SendMessageSimpleCycle {
	public static void main(String[] args) {
		// 创建生产者
		Properties kafkaProps = new Properties();
		// 指定broker
		kafkaProps.put("bootstrap.servers", "slave1:9092,slave2:9092,slave3:9092");
		kafkaProps.put("group.id", "CountryCounter");	// 消费者群组
		// 设置序列化
		kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		kafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		// 实例化出producer
		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(kafkaProps);

		try {
			for (int i = 0; i < 1000000; i++) {
				// 创建ProducerRecord对象
				ProducerRecord<String, String>  record = new ProducerRecord<String, String>("CustomerCountry",
						"Hello", "Sync-Test-" + i);
				// 发送消息
				producer.send(record);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			producer.close();
		}
	}
}





