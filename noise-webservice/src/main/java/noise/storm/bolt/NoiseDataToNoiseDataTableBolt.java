package noise.storm.bolt;

import java.util.Map;








import noise.model.NoiseData;
import noise.service.NoiseService;
import noise.util.OriginNoiseDataTuple;
import noise.util.TimeHelper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;

public class NoiseDataToNoiseDataTableBolt extends BaseRichBolt{

	/**
	 * 
	 */
	private static final Log LOG = LogFactory.getLog(NoiseDataToNoiseDataTableBolt.class);
	private static final long serialVersionUID = 1L;
	private OutputCollector collector;
	public void execute(Tuple input) {
		System.out.println("RECV[kafka -> NoiseDataToNoiseDataTableBolt] " + input);
		OriginNoiseDataTuple noiseData = (OriginNoiseDataTuple) input.getValue(0);
		NoiseService noiseService = new NoiseService();
		NoiseData noiseDataInDatabase = noiseService.getNoiseDataByGridAndTime(noiseData.getGrid(), 
				TimeHelper.getNoiseDataTimeInDatabase(noiseData.getTime()));
		Long newNoiseLevel = (noiseDataInDatabase.getNoiseLevel() + new Long(noiseData.getNoise_level()))/2;
		noiseService.saveNoiseData(noiseDataInDatabase);
		collector.ack(input);
	}

	public void prepare(Map map, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
		
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub
		
	}

}
