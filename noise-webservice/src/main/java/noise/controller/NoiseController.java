package noise.controller;

import org.noise.common.kafka.NoiseProducer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import noise.model.testTable;



@Controller
public class NoiseController {
	
	@RequestMapping("/upload")
	public ModelAndView uploadNoiseDataToKafka() {
		
		String url = "www.example.com";
		NoiseProducer noiseProducer = new NoiseProducer();
		boolean isSuccessed = noiseProducer.sendNoiseData(url);
		ModelAndView mv = new ModelAndView("helloworld");
		mv.addObject("name", isSuccessed);
		return mv;
	}
}