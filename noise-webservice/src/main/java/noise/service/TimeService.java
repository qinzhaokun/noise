package noise.service;

import java.util.Date;
import java.util.List;

import noise.model.Time;
import noise.repository.ITimeDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimeService {
	@Autowired
	private ITimeDao iTimeDao;
	
	public Time getTimeByDayAndTimeSlot(Date day,Long timeSlot){
		return iTimeDao.findByDayAndTimeSlot(day, timeSlot);
	}
	
	public List<Time> getTimesByDay(Date day){
		return iTimeDao.findByDay(day);
	}
	
	public List<Time> getTimesByTimeSlot(Long timeSlot){
		return iTimeDao.findByTimeSlot(timeSlot);
	}
}
