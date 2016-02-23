package es.pruebas.sisifo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {	
	
	@RequestMapping("/home")
	public ModelAndView viewHome() {
		ModelAndView mav = new ModelAndView("home");
		mav.addObject("mensaje", "Hello world");
		return mav;
	}	
}
