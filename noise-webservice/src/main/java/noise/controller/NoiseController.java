package noise.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import noise.model.testTable;



@Controller
public class NoiseController {
	
	@RequestMapping("/upload")
	public ModelAndView showMessage(
			 @RequestBody testTable data) {
		
		//testTable test = iTestTableDao.findOne(id);
		System.out.println(data.getName());
		ModelAndView mv = new ModelAndView("helloworld");
		mv.addObject("name", data.getName());
		return mv;
	}
}