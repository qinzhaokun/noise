package noise.util;

import lombok.Data;

@Data
public class NoiseCount {
	
	private Integer num;
	
	private Integer totalNoiseLevel;
	
	public NoiseCount(Integer num, Integer totalNoiseLevel){
		this.num = num;
		this.totalNoiseLevel = totalNoiseLevel;
	}
	public NoiseCount addOne(Integer noiseLevel){
		num++;
		totalNoiseLevel += noiseLevel;
		return this;
	}
	
	public Integer calAverageNoiseLevel(){
		return totalNoiseLevel/num;
	}
}

