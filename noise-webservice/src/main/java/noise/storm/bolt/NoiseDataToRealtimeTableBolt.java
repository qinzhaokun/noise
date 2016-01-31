package noise.storm.bolt;

import java.util.Map;














import noise.model.RealtimeNoiseData;
import noise.model.Time;
import noise.model.UserInfo;
import noise.service.RealtimeNoiseDataService;
import noise.service.UserInfoService;
import noise.util.OriginNoiseDataTuple;
import noise.util.TimeHelper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;

public class NoiseDataToRealtimeTableBolt extends BaseRichBolt{

	/**
	 * 
	 */
	private static final Log LOG = LogFactory.getLog(NoiseDataToRealtimeTableBolt.class);
	private static final long serialVersionUID = 1L;
	private OutputCollector collector;
	public void execute(Tuple input) {
		System.out.println("RECV[kafka -> NoiseDataToRealtimeTableBolt] " + input);
		OriginNoiseDataTuple noiseData = (OriginNoiseDataTuple) input.getValue(0);
		Time time = TimeHelper.getNoiseDataTimeInDatabase(noiseData.getTime());
		UserInfoService userInfoService = new UserInfoService();
		UserInfo userInfo = userInfoService.getUserInfoByImei(noiseData.getContributor());
		if(userInfo == null){
			userInfo = new UserInfo();
			userInfo.setImei(noiseData.getContributor());
			userInfo.setAward(new Long(2));
			userInfoService.create(userInfo);
		}
		RealtimeNoiseDataService realtimeNoiseDataService = new RealtimeNoiseDataService();
		RealtimeNoiseData realTimeNoiseData = new RealtimeNoiseData();
		realTimeNoiseData.setGrid(noiseData.getGrid());
		realTimeNoiseData.setTime(time);
		realTimeNoiseData.setUserInfo(userInfo);
		realTimeNoiseData.setNoiseLevel(new Long(noiseData.getNoise_level()));
		realtimeNoiseDataService.insertOne(realTimeNoiseData);
		collector.ack(input);
		
	}

	public void prepare(Map map, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
	}

	public void declareOutputFields(OutputFieldsDeclarer arg0) {
		
		
	}

}
