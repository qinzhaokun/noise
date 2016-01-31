package noise.util;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import noise.model.Time;
import noise.service.TimeService;



public class TimeHelper {
	
	public static Date getDateFromUTime(String u_time){
		Calendar cal = Calendar.getInstance();
		String [] times = u_time.split("-");
		int year = Integer.parseInt(times[0]);
		int month = Integer.parseInt(times[1]);
		int day = Integer.parseInt(times[2]);
		int hour = Integer.parseInt(times[3]);
		int min = Integer.parseInt(times[5]);
		int sec = Integer.parseInt(times[5]);
		
		cal.set(year, month, day, hour, min, sec);
		return cal.getTime();
	}
	
	public static Date getNoiseDataDayInDatabase(){
		Calendar cal = Calendar.getInstance();
		cal.set(2015, 6, 13, 0, 0, 0);
		return cal.getTime();
	}
	
	public static Long getTimeSlot(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return new Long(cal.HOUR);
	}
}
