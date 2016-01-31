package noise.service;

import noise.model.RealtimeNoiseData;
import noise.repository.IRealtimeNoiseDataDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RealtimeNoiseDataService {

	@Autowired
	private IRealtimeNoiseDataDao iRealtimeNoiseDataDao;
	
	public void insertOne(RealtimeNoiseData realtimeNoiseData){
		iRealtimeNoiseDataDao.save(realtimeNoiseData);
	}
}
