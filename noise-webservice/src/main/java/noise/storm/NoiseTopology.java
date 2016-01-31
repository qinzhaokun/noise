package noise.storm;

import java.util.Arrays;

import noise.storm.bolt.NoiseDataSplitterBolt;
import noise.storm.bolt.NoiseDataToNoiseDataTableBolt;
import noise.storm.bolt.NoiseDataToRealtimeTableBolt;

import org.apache.storm.hdfs.bolt.HdfsBolt;
import org.apache.storm.hdfs.bolt.format.DefaultFileNameFormat;
import org.apache.storm.hdfs.bolt.format.DelimitedRecordFormat;
import org.apache.storm.hdfs.bolt.format.FileNameFormat;
import org.apache.storm.hdfs.bolt.format.RecordFormat;
import org.apache.storm.hdfs.bolt.rotation.FileRotationPolicy;
import org.apache.storm.hdfs.bolt.sync.CountSyncPolicy;
import org.apache.storm.hdfs.bolt.sync.SyncPolicy;

import storm.kafka.BrokerHosts;
import storm.kafka.KafkaSpout;
import storm.kafka.SpoutConfig;
import storm.kafka.StringScheme;
import storm.kafka.ZkHosts;
import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.AuthorizationException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.spout.SchemeAsMultiScheme;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;

public class NoiseTopology {

	public static void main(String[] args) {
		String zks = "192.168.1.84:2181";
        String topic = "noise";
        String zkRoot = "/storm"; // default zookeeper root configuration for storm
        String id = "noise";
       
        BrokerHosts brokerHosts = new ZkHosts(zks);
        SpoutConfig spoutConf = new SpoutConfig(brokerHosts, topic, zkRoot, id);
        spoutConf.scheme = new SchemeAsMultiScheme(new StringScheme());
        //spoutConf.forceFromStart = false;
        spoutConf.zkServers = Arrays.asList(new String[] {"192.168.1.84"});
        spoutConf.zkPort = 2181;
       
       /* RecordFormat format = new DelimitedRecordFormat().withFieldDelimiter(" : ");

        SyncPolicy syncPolicy = new CountSyncPolicy(1000);

        FileNameFormat fileNameFormat = new DefaultFileNameFormat().withPath("/storm/").withPrefix("app_").withExtension(".txt");

        HdfsBolt hdfsBolt = new HdfsBolt().withFsUrl("hdfs://192.168.1.84:8020").withFileNameFormat(fileNameFormat).withRecordFormat(format)
                          .withSyncPolicy(syncPolicy);*/
        
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("noiseSpout", new KafkaSpout(spoutConf), 1); // Kafka我们创建了一个5分区的Topic，这里并行度设置为5
        //builder.setBolt("hdfs-bolt", hdfsBolt, 1).shuffleGrouping("noiseSpout");
        
        builder.setBolt("data-splitter", new NoiseDataSplitterBolt(), 1).shuffleGrouping("noiseSpout");
        builder.setBolt("data-saveToNoiseData", new NoiseDataToNoiseDataTableBolt(), 1).shuffleGrouping("data-splitter");
        builder.setBolt("data-saveToRealtimeNoiseData", new NoiseDataToRealtimeTableBolt(), 1).shuffleGrouping("data-splitter");
       
        Config conf = new Config();
       
        String name = NoiseTopology.class.getSimpleName();
        if (args != null && args.length > 0) {
             // Nimbus host name passed from command line
             conf.put(Config.NIMBUS_HOST, args[0]);
             conf.setNumWorkers(3);
             try {
				StormSubmitter.submitTopologyWithProgressBar(name, conf, builder.createTopology());
			} catch (AlreadyAliveException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidTopologyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AuthorizationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } else {
      	  System.out.println("local");
             conf.setMaxTaskParallelism(3);
             LocalCluster cluster = new LocalCluster();
             cluster.submitTopology(name, conf, builder.createTopology());
             try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
             cluster.shutdown();
        }

	}

}
