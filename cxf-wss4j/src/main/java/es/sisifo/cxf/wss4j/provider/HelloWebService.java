package es.sisifo.cxf.wss4j.provider;

import javax.jws.WebService;

@WebService(targetNamespace="http://provider.wss4j.cxf.sisifo.es/")
public interface HelloWebService {

	String sayHello(String name);
}
