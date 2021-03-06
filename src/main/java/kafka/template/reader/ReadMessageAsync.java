package kafka.template.reader;

import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;


public class ReadMessageAsync {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// Properties 对象
		Properties props = new Properties();
		props.put("bootstrap.servers", "slave1:9092,slave2:9092,slave3:9092");
		props.put("group.id", "CountryCounter");	// 消费者群组
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		
		// consumer 对象
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
		
		// 订阅主题
		consumer.subscribe(Collections.singleton("CustomerCountry"));	// 支持订阅多个主题，也支持正则
		
		try {
			// 设置分区开头读取, 0表示立立即返回，无需等待
			//consumer.seekToBeginning(consumer.poll(0).partitions());
			while (true) {
				// 0.1s 的轮询等待
				ConsumerRecords<String, String> records = consumer.poll(100);
				System.out.println(records.count());
				for (ConsumerRecord<String, String> record : records) {
					// 输出到控制台
					System.out.printf("topic = %s, partition = %s, offset = %sd, key = %s, value = %s\n", 
							record.topic(), record.partition(), record.offset(), record.key(), record.value());
				}
				// 异步提交偏移量
				consumer.commitAsync();
				Thread.sleep(500);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			consumer.close();
		}
	}
}





