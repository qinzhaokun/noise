package noise.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
* Time实体类
* @author Zhaokun Qin
* @date  2015-1-3
*/
@Entity
@Data
@Table(name="time")
public class Time {
	@Id
	@Column(name = "id")
	private Long id;
	
	@Column(name = "day")
	private Date day;
	
	@Column(name = "time_slot")
	private Long timeSlot;
	
	@JsonIgnore
    @OneToMany(mappedBy = "time", cascade = {}, fetch = FetchType.LAZY)
    private List<NoiseData> noiseDatas = new ArrayList<>();
}
