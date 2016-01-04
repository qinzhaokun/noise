package noise.repository;

import java.util.Date;
import java.util.List;

import noise.model.Time;

import org.springframework.data.repository.CrudRepository;

public interface ITimeDao extends CrudRepository<Time,Long>{
	public Time findByDayAndTimeSlot(Date day, Long timeSlot);
	public List<Time> findByDay(Date day);
	public List<Time> findByTimeSlot(Long timeSlot);
}
