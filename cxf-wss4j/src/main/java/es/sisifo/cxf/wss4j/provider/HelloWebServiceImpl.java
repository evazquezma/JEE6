package es.sisifo.cxf.wss4j.provider;

import javax.jws.WebService;

import org.springframework.stereotype.Service;

@WebService(serviceName = "helloWebService", targetNamespace="http://provider.wss4j.cxf.sisifo.es/")
@Service("helloWebService")
public class HelloWebServiceImpl implements HelloWebService {

	@Override
	public String sayHello(final String name) {
		return "Hello " + name + "!!";
	}

}
