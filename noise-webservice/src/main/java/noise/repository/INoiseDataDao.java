package noise.repository;

import java.util.List;

import noise.model.Grid;
import noise.model.NoiseData;
import noise.model.Time;

import org.springframework.data.repository.CrudRepository;

public interface INoiseDataDao extends CrudRepository<NoiseData,Long>{
	public NoiseData findByGridAndTime(Grid grid,Time time);
	public List<NoiseData> findByGrid(Grid grid);
	public List<NoiseData> findByTime(Time time);
}
