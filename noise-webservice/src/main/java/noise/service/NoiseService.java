package noise.service;

import java.util.List;

import noise.model.Grid;
import noise.model.NoiseData;
import noise.model.Time;
import noise.repository.IGridDao;
import noise.repository.INoiseDataDao;
import noise.repository.ITimeDao;

import org.noise.common.kafka.NoiseProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NoiseService {
	@Autowired
	private INoiseDataDao iNoiseDataDao;
	
	@Autowired
	private ITimeDao iTimeDao;
	
	public NoiseData getNoiseDataById(Long id){
		return iNoiseDataDao.findOne(id);
	}
	public NoiseData getNoiseDataByGridAndTime(Grid grid,Time time){
		return iNoiseDataDao.findByGridAndTime(grid, time);
	}
	
	public List<NoiseData> getNoiseDataByGrid(Grid grid){
		return iNoiseDataDao.findByGrid(grid);
	}
	
	public List<NoiseData> getNoiseDataByTime(Time time){
		return iNoiseDataDao.findByTime(time);
	}
	
	@Transactional
	public void saveNoiseData(NoiseData noiseDatas){
		iNoiseDataDao.save(noiseDatas);
	}
	
	public boolean uploadNosieDataToKafka(String noiseData){
		NoiseProducer noiseProducer = new NoiseProducer();
		return noiseProducer.sendNoiseData(noiseData);
	}
}
