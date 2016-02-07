package es.pruebas.sisifo.async.service.wait;

import org.springframework.stereotype.Service;

import es.pruebas.sisifo.async.model.InvoiceReport;

@Service
public class SendMailAfterWaitCommand implements AfterWaitComand<InvoiceReport> {

	@Override
	public void docommand(InvoiceReport result) {
		System.out.println("Sending invoice result by mail");
		System.out.println(result);		
	}

}
