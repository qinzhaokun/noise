package noise.storm.bolt;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import noise.model.Grid;
import noise.service.GridService;
import noise.util.NoiseCount;
import noise.util.OriginNoiseDataTuple;
import noise.util.TimeHelper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public class NoiseDataSplitterBolt  extends BaseRichBolt{
	private static final Log LOG = LogFactory.getLog(NoiseDataSplitterBolt.class);
	private static final long serialVersionUID = 1L;
	private OutputCollector collector;

	public void execute(Tuple input) {
		String line = input.getString(0);
        String[] datas = line.split(";");
        if(datas == null){
        	System.out.println("empty data");
        	collector.ack(input);
        	return;
        }
        String [] header = datas[0].split("_");
        if(header.length != 7){
        	System.out.println("error header");
        	collector.ack(input);
        	return;
        }
        String u_emei = header[0]; 
		String u_time = header[3];
		String u_gps_state = header[4];
		if(!u_gps_state.equals("1")){
			System.out.println("gps fail");
        	collector.ack(input);
        	return;
		}
		System.out.println("RECV[kafka -> NoiseDataSplitterBolt] " + u_emei);
		OriginNoiseDataTuple noiseData = new OriginNoiseDataTuple();
		noiseData.setContributor(u_emei);
		noiseData.setTime(TimeHelper.getDateFromUTime(u_time));
		GridService gridService = new GridService();
		Map<Grid,NoiseCount> map = new HashMap<Grid,NoiseCount>();
		System.out.println("checkpoint1");
		for(int i = 1;i < datas.length;i++){
			String [] singleNoiseData = datas[i].split(",");
			System.out.println("checkpoint2");
			Integer noiseLevel = Integer.parseInt(singleNoiseData[0]);
			Double longtitude = Double.parseDouble(singleNoiseData[1]);
			Double latitude = Double.parseDouble(singleNoiseData[2]);
			Grid grid = gridService.getGridByLongitudeAndLatitude(longtitude, latitude);
			if(grid != null){
				if(map.containsKey(grid)){
					NoiseCount noiseCount = map.get(grid);
					noiseCount.addOne(noiseLevel);
					map.put(grid, noiseCount);
				}
				else{
					NoiseCount noiseCount = new NoiseCount(1,noiseLevel);
					map.put(grid, noiseCount);
				}
			}			
			
		}
		System.out.println("checkpoint2");
		Iterator iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			Grid grid = (Grid)entry.getKey();
			NoiseCount noiseCount = (NoiseCount)entry.getValue();
			noiseData.setGrid(grid);
			noiseData.setNoise_level(noiseCount.calAverageNoiseLevel());
			collector.emit(input, new Values(noiseData));
		}
		
        collector.ack(input);
		
	}

	public void prepare(Map map, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
		
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("noiseTuple"));
		
	}

}
