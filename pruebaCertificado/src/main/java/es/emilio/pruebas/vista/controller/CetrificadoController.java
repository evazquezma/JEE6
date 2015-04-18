package es.emilio.pruebas.vista.controller;

import java.security.cert.X509Certificate;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/infoCertificado")
public class CetrificadoController {
	
	private String X509CERT_ATTRIBUTE = "javax.servlet.request.X509Certificate";

	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView prueba(HttpServletRequest request) {
		//Recuperar el certificado de la cabecera
		 X509Certificate[] certs = (X509Certificate[]) request.getAttribute(X509CERT_ATTRIBUTE);

		if (certs == null || certs.length == 0) {
			return crearVistaError("No existe certificado en la request");
		}

		return crearVistaCertificado(certs[0]);
	}

	private ModelAndView crearVistaCertificado(X509Certificate certificado) {
		ModelAndView mav = new ModelAndView("infoCertificado");
		mav.addObject("certificadoRaw", 	certificado.toString());
		return mav;
	}


	private ModelAndView crearVistaError(String mensaje) {
		ModelAndView mav = new ModelAndView("infoCertificadoError");
		mav.addObject("mensaje", mensaje);
		return mav;
	}
}
