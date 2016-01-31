package noise.repository;

import noise.model.UserInfo;

import org.springframework.data.repository.CrudRepository;

public interface IUserInfoDao extends CrudRepository<UserInfo,Long>{
	UserInfo findByImei(String imei);
}
