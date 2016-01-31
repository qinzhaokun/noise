package noise.util;

import java.util.Date;



import noise.model.Grid;
import lombok.Data;

@Data
public class OriginNoiseDataTuple {
	private String contributor; 
	
	private Date time;
	
	private Grid grid;
	
	private Integer noise_level;
}
