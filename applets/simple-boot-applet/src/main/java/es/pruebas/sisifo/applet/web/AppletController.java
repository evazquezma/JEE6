package es.pruebas.sisifo.applet.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppletController {

	@RequestMapping("/applet")
    public String greeting() {
        return "myTestApplet";
    }
}
