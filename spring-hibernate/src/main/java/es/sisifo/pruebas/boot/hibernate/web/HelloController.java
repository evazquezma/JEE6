package es.sisifo.pruebas.boot.hibernate.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.sisifo.pruebas.boot.hibernate.api.model.Store;
import es.sisifo.pruebas.boot.hibernate.api.service.StoreService;

@Controller
public class HelloController {
	@Autowired
	private StoreService storeService;

	@RequestMapping("/")
	@ResponseBody
	String home() {
		return "Hello World!";
	}


	@RequestMapping("/store")
	@ResponseBody
	String store() throws IOException {
		final File file = new File("D:/tmp/afirma/grande.pdf");

		Store store = new Store();
		store.setName("test");
		store.setContenStream(new FileInputStream(file));
		store.setContentSize((int)file.length());
		store = storeService.saveStore(store);



		final Store sotreDb = storeService.getStore(store.getId());
		final FileOutputStream fos = new FileOutputStream("D:/tmp/afirma/salida.pdf");
		IOUtils.copy(sotreDb.getContenStream(), fos);
		fos.close();

		return "OK";
	}
}
