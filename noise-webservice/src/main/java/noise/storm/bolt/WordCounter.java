package noise.storm.bolt;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;

public class WordCounter extends BaseRichBolt{

	private static final Log LOG = LogFactory.getLog(WordCounter.class);
    private static final long serialVersionUID = 886149197481637894L;
    private OutputCollector collector;
    private Map<String, AtomicInteger> counterMap;
    
    
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this.collector = collector;    
        this.counterMap = new HashMap<String, AtomicInteger>();
	}

	
	public void execute(Tuple input) {
		String word = input.getString(0);
        int count = input.getInteger(1);
        System.out.println("RECV[splitter -> counter] " + word + " : " + count);
        AtomicInteger ai = this.counterMap.get(word);
        if(ai == null) {
             ai = new AtomicInteger();
             this.counterMap.put(word, ai);
        }
        ai.addAndGet(count);
        collector.ack(input);
        System.out.println("CHECK statistics map: " + this.counterMap);
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("word", "count"));  
	}
	
	@Override
    public void cleanup() {
		System.out.println("The final result:");
         Iterator<Entry<String, AtomicInteger>> iter = this.counterMap.entrySet().iterator();
         while(iter.hasNext()) {
              Entry<String, AtomicInteger> entry = iter.next();
              System.out.println(entry.getKey() + "\t:\t" + entry.getValue().get());
         }
        
    }

}
