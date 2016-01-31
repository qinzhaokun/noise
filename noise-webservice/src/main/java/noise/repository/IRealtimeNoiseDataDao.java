package noise.repository;

import noise.model.RealtimeNoiseData;

import org.springframework.data.repository.CrudRepository;

public interface IRealtimeNoiseDataDao extends CrudRepository<RealtimeNoiseData,Long>{
	
}
