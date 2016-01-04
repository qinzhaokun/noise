package noise.model;

import java.util.ArrayList;
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
 * Grid实体类
 * @author Zhaokun Qin
 * @date  2016-1-3
 */
@Entity
@Data
@Table(name="grid")
public class Grid {
	@Id
	@Column(name = "id")
	private Long id;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "longitude1")
	private Double longitude1;
	
	@Column(name = "latitude1")
	private Double latitude1;
	
	@Column(name = "longitude2")
	private Double longitude2;
	
	@Column(name = "latitude2")
	private Double latitude2;
	
	@Column(name = "longitude3")
	private Double longitude3;
	
	@Column(name = "latitude3")
	private Double latitude3;
	
	@Column(name = "longitude4")
	private Double longitude4;
	
	@Column(name = "latitude4")
	private Double latitude4;
	
	@Column(name = "grid_x")
	private Long gridX;
	
	@Column(name = "grid_y")
	private Long gridY;
	
	@JsonIgnore
    @OneToMany(mappedBy = "grid", cascade = {}, fetch = FetchType.LAZY)
    private List<NoiseData> noiseDatas = new ArrayList<NoiseData>();
}
