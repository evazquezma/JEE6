package es.sisifo.cxf.wss4j.provider.mtom;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.jws.WebService;

import org.apache.cxf.helpers.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@WebService(serviceName = "helloMTOMWebService", targetNamespace="http://provider.mtom.cxf.sisifo.es/")
@Service("helloMOTMWebService")
public class HelloMTOMWebServiceImpl implements HelloMTOMWebService {
	private static final Logger LOGGER = LoggerFactory.getLogger(HelloMTOMWebServiceImpl.class);


	@Override
	public void sendData(final HelloMTOMData data) {
		LOGGER.info("Recibido mensaje MTOM. Nombre: {}, tipo: {}", data.getName(), data.getFileData().getContentType());

		try {
			final OutputStream outputStream = new FileOutputStream(File.createTempFile("MTOM", ".pdf"));
			//final OutputStream outputStream = new FileOutputStream(new File("D:/tmp/arq/Impr/salida.pdf"));
			IOUtils.copy(data.getFileData().getInputStream(), outputStream);
			outputStream.close();
		}
		catch(final Exception e) {
			LOGGER.error("Error al procesar fichero", e);
			throw new RuntimeException("Error al procesar fichero");
		}
	}

}
