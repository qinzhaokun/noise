package noise.service;

import noise.model.UserInfo;
import noise.repository.IUserInfoDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {
	
	@Autowired
	private IUserInfoDao iUserInfoDao;
	
	public UserInfo getUserInfoByImei(String imei){
		return iUserInfoDao.findByImei(imei);
	}
	
	public void create(UserInfo userInfo){
		iUserInfoDao.save(userInfo);
	}
}
