package noise.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import noise.model.Grid;
import noise.model.NoiseData;
import noise.model.Time;
import noise.service.GridService;
import noise.service.NoiseService;
import noise.service.TimeService;
import noise.util.TimeHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;




@RestController
public class NoiseController {
	
	@Autowired
	private NoiseService noiseService;
	
	@Autowired
	private GridService gridService;
	
	@Autowired
	private TimeService timeService;
	
	@RequestMapping("/upload")
	public ModelAndView uploadNoiseDataToKafka(
			@RequestParam(required = true) String noiseData) {
		boolean isSuccessed = noiseService.uploadNosieDataToKafka(noiseData);
		ModelAndView mv = new ModelAndView("helloworld");
		mv.addObject("isSuccessed", isSuccessed);
		mv.addObject("reward",4);
		return mv;
	}
	
	
	@RequestMapping("/noiselevel")
	public ModelAndView getNoiseLevel(
			@RequestParam(value = "id", required = false, defaultValue = "") Long id,
			@RequestParam(value = "longitude", required = false, defaultValue = "") Double longitude,
			@RequestParam(value = "latitude", required = false, defaultValue = "") Double latitude) {
		
		ModelAndView mv = new ModelAndView("helloworld");
		if(id!= null){
			NoiseData noiseData = noiseService.getNoiseDataById(id);
			System.out.println(noiseData.getNoiseLevel());
			mv.addObject("noiseLevel",noiseData.getNoiseLevel());
		}
		else if(longitude != null && latitude != null){
			Grid grid = gridService.getGridByLongitudeAndLatitude(longitude, latitude);
			if(grid != null){
				Date date = TimeHelper.getNoiseDataDayInDatabase();
				Long timeSlot = TimeHelper.getTimeSlot(new Date());
				Time time = timeService.getTimeByDayAndTimeSlot(date, timeSlot);
				NoiseData noiseData = noiseService.getNoiseDataByGridAndTime(grid, time);
				mv.addObject("noiseLevel",noiseData.getNoiseLevel());
			}
		}
		
		mv.addObject("name", "zqin");
		return mv;
	}
}