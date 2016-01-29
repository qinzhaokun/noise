package org.noise.common.kafka;

import java.util.Date;
import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class NoiseProducer {
	public boolean sendNoiseData(String noiseData){
		boolean isSuccessed = true;
		try{
			int events=10;
			// 设置配置属性
			Properties props = new Properties();
			props.put("zk.connect", "192.168.1.84:2181");  
			props.put("metadata.broker.list","192.168.1.84:9092");
			props.put("serializer.class", "kafka.serializer.StringEncoder");
			// key.serializer.class默认为serializer.class
			props.put("key.serializer.class", "kafka.serializer.StringEncoder");
			// 触发acknowledgement机制，否则是fire and forget，可能会引起数据丢失
			// 值为0,1,-1,可以参考
			// http://kafka.apache.org/08/configuration.html
			props.put("request.required.acks", "1");
			ProducerConfig config = new ProducerConfig(props);
 
			// create producer
			Producer<String, String> producer = new Producer<String, String>(config);
			// send message to Kafka
			long start=System.currentTimeMillis();
			KeyedMessage<String, String> data = new KeyedMessage<String, String>(
                    "noise", noiseData);
			producer.send(data);
			System.out.println("Running time:" + (System.currentTimeMillis() - start));
			// close producer
			producer.close();
		}
		catch(Exception ex) {
			System.out.println("Input error : " + ex.getMessage());
			isSuccessed = false;
		}
		
		return isSuccessed;
	}
}