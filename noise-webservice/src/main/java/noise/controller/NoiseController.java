package noise.controller;

import org.noise.common.kafka.NoiseProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import noise.model.NoiseData;
import noise.model.testTable;
import noise.service.NoiseService;



@Controller
public class NoiseController {
	
	@Autowired
	private NoiseService noiseService;
	@RequestMapping("/upload")
	public ModelAndView uploadNoiseDataToKafka() {
		
		String url = "www.example.com";
		NoiseProducer noiseProducer = new NoiseProducer();
		boolean isSuccessed = noiseProducer.sendNoiseData(url);
		ModelAndView mv = new ModelAndView("helloworld");
		mv.addObject("name", isSuccessed);
		return mv;
	}
	
	@RequestMapping("/noiselevel")
	public ModelAndView getNoiseLevel(
			@RequestParam(value = "id", required = false, defaultValue = "") Long id) {
		
		if(!id.equals("")){
			NoiseData noiseData = noiseService.getNoiseDataById(id);
			System.out.println(noiseData.getNoiseLevel());
		}
		ModelAndView mv = new ModelAndView("helloworld");
		mv.addObject("name", "zqin");
		return mv;
	}
}