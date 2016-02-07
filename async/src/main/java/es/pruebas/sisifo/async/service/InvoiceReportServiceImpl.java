package es.pruebas.sisifo.async.service;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import es.pruebas.sisifo.async.model.InvoiceReport;

@Service
public class InvoiceReportServiceImpl implements InvoiceReportService {
	
	@Override
	@Async
	public Future<InvoiceReport> generateReport() {
		Resource resource = new ClassPathResource("spring-boot-reference.pdf");				
		byte[] bytes = null;
		try {
			bytes = IOUtils.toByteArray(resource.getInputStream());
		} catch (IOException e) {
			//Nothing					
		}
		
		simulateDelay();
		
		InvoiceReport report = new InvoiceReport();
		report.setContent(bytes);
		return new AsyncResult<InvoiceReport>(report);
	}

	
	private void simulateDelay() {
		int secondsSleep = new Random().nextInt(2);
		try {
			System.out.println("Going to sleep " + secondsSleep + " seconds");
			TimeUnit.SECONDS.sleep(secondsSleep);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Awake!!");
	}
}
