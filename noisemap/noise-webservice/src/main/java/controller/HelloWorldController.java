package controller;

import org.noise.common.kafka.NoiseProducer;
import org.noise.common.repository.ITestTableDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

 
@Controller
public class HelloWorldController {
	String message = "Welcome to Spring MVC!";
	
	 @Autowired
	 protected ITestTableDao iTestTableDao;
 
	@RequestMapping("/hello")
	public ModelAndView showMessage(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name,
			@RequestParam(value = "id", required = false, defaultValue = "1") Long id) {
		
		//testTable test = iTestTableDao.findOne(id);
		//System.out.println(test.getName());
		ModelAndView mv = new ModelAndView("helloworld");
		mv.addObject("message", message);
		mv.addObject("name", name);
		NoiseProducer noiseProducer = new NoiseProducer();
		noiseProducer.sendNoiseData();
		//mv.addObject("fromDatabase", test.getName());
		return mv;
	}
}