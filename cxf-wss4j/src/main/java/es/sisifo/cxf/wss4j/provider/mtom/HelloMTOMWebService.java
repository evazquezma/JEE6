package es.sisifo.cxf.wss4j.provider.mtom;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(targetNamespace="http://provider.mtom.cxf.sisifo.es/")
public interface HelloMTOMWebService {

	void sendData(@WebParam(name="data") HelloMTOMData data);
}
