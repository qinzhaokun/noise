package noise.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;


/**
 * testTable实体类
 * @author Zhaokun Qin
 * @date  2015-12-23
 */
@Entity
@Data
@Table(name="test_table")
public class testTable {
	@Id
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	
}