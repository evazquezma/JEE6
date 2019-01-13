package es.pruebas.sisifo.spring4.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import es.pruebas.sisifo.spring4.api.service.TestService;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private TestService testService;

	@RequestMapping("/home")
	public ModelAndView home() {
		final ModelAndView mav = new ModelAndView("home");
		mav.addObject("salute", testService.sayHello("test"));
		return mav;
	}
}
