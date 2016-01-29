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
			int events=100;
			// 设置配置属性
			Properties props = new Properties();
			props.put("metadata.broker.list","192.168.1.84:9092");
			props.put("serializer.class", "kafka.serializer.StringEncoder");
			// key.serializer.class默认为serializer.class
			props.put("key.serializer.class", "kafka.serializer.StringEncoder");
			// 触发acknowledgement机制，否则是fire and forget，可能会引起数据丢失
			// 值为0,1,-1,可以参考
			// http://kafka.apache.org/08/configuration.html
			props.put("request.required.acks", "1");
			ProducerConfig config = new ProducerConfig(props);
 
			// 创建producer
			Producer<String, String> producer = new Producer<String, String>(config);
			// 产生并发送消息
			long start=System.currentTimeMillis();
			for (long i = 0; i < events; i++) {
				long runtime = new Date().getTime();
				String ip = "192.168.1.110" + i;//rnd.nextInt(255);
				String msg = runtime + "," + noiseData + "," + ip;
				//如果topic不存在，则会自动创建，默认replication-factor为1，partitions为0
				KeyedMessage<String, String> data = new KeyedMessage<String, String>(
                    "noise", ip, msg);
				producer.send(data);
			}
			System.out.println("耗时:" + (System.currentTimeMillis() - start));
			// 关闭producer
			producer.close();
		}
		catch(Exception ex) {
			System.out.println("输入有误" + ex.getMessage());
			isSuccessed = false;
		}
		
		return isSuccessed;
	}
}