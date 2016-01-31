package noise.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * Grid实体类
 * @author Zhaokun Qin
 * @date  2016-1-30
 */
@Entity
@Data
@Table(name="realtime_noise_data")
public class RealtimeNoiseData {

	@Id
	@Column(name = "id")
	private Long id;
	
	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private UserInfo userInfo;
	
	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(name = "grid_id", nullable = false)
    private Grid grid;
	
	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(name = "time_id", nullable = false)
    private Time time;
	
	@Column(name = "realtime")
	private Date realtime;

	@Column(name = "noise_level")
	private Long noiseLevel;
}
