package noise.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * NoiseData实体类
 * @author Zhaokun Qin
 * @date  2015-1-3
 */
@Entity
@Data
@Table(name="noise_data")
public class NoiseData {
	
	@Id
	@Column(name = "id")
	private Long id;
	
	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(name = "grid_id", nullable = false)
    private Grid grid;
	
	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(name = "time_id", nullable = false)
    private Time time;
	
	@Column(name = "noise_level")
	private Long noiseLevel;
	
}
