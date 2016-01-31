package noise.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * NoiseData实体类
 * @author Zhaokun Qin
 * @date  2015-1-30
 */
@Entity
@Data
@Table(name="user_info")
public class UserInfo {
	
	@Id
	@Column(name = "id")
	private Long id;
	
	@Column(name = "imei")
	private String imei;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "alipay")
	private String alipay;
	
	@Column(name = "award")
	private Long award;
}
