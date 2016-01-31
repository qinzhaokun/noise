package noise.controller;

import org.noise.common.kafka.NoiseProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;



 
@Controller
public class HelloWorldController {
	String message = "Welcome to Spring MVC!";
	
	
 
	@RequestMapping("/hello")
	public ModelAndView showMessage(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name,
			@RequestParam(value = "id", required = false, defaultValue = "1") Long id) {
		
		
		System.out.println("dd");
		ModelAndView mv = new ModelAndView("helloworld");
		mv.addObject("message", message);
		mv.addObject("name", name);
		return mv;
	}
	
	@RequestMapping("/send")
	public ModelAndView sendMessageToKafka(
			@RequestParam(value = "message", required = false, defaultValue = "World") String message,
			@RequestParam(value = "id", required = false, defaultValue = "1") Long id) {
	
		NoiseProducer noiseProducer = new NoiseProducer();
		boolean isSuccessed = noiseProducer.sendNoiseData(message);
		ModelAndView mv = new ModelAndView("helloworld");
		mv.addObject("name", isSuccessed);
		return mv;

	}
}