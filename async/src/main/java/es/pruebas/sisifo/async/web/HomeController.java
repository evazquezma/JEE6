package es.pruebas.sisifo.async.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {	
	
	@RequestMapping("/")
	public ModelAndView viewHome() {
		return new ModelAndView("home");
	}
	
	
	@RequestMapping("/laterPlease")
	public ModelAndView laterPlease() {
		return new ModelAndView("laterPlease");
	}	
}
