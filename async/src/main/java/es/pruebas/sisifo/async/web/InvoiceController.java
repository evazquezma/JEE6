package es.pruebas.sisifo.async.web;

import java.io.IOException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.pruebas.sisifo.async.model.InvoiceReport;
import es.pruebas.sisifo.async.service.InvoiceReportService;
import es.pruebas.sisifo.async.service.wait.AfterWaitComand;
import es.pruebas.sisifo.async.service.wait.WaitUntilFinishService;

@Controller
public class InvoiceController {
	
	@Autowired
	private InvoiceReportService invoiceReportSerive;
	
	@Autowired
	private WaitUntilFinishService<InvoiceReport> waitUntilFinishService;
	
	@Autowired
	private AfterWaitComand<InvoiceReport> sendMailAfterWaitCommand;
	
	
	
	@RequestMapping(value="/report", method=RequestMethod.POST)
	public HttpEntity<byte[]> getReport(HttpServletResponse response) throws IOException {
		Future<InvoiceReport> reportFutre = invoiceReportSerive.generateReport();
		
		InvoiceReport invoiceReport = null;
		try {
			invoiceReport = reportFutre.get(1, TimeUnit.SECONDS);
			System.out.println("In time");		
			return responseNow(invoiceReport);
		}
		catch(TimeoutException e) {
			System.out.println("Too late");
			waitUntilFinishService.waitUntilFinish(reportFutre, sendMailAfterWaitCommand);			
			response.sendRedirect("/laterPlease");
			return null;
		}
		catch(Exception e) {
			//Handle exception
			return null;
		}
	}

	
	private HttpEntity<byte[]> responseNow(InvoiceReport invoiceReport) {	
	    HttpHeaders header = new HttpHeaders();
	    header.setContentType(new MediaType("application", "pdf"));
	    header.set("Content-Disposition", "attachment; filename= \"report.pdf\"");
	    header.setContentLength(invoiceReport.getContent().length);

	    return new HttpEntity<byte[]>(invoiceReport.getContent(), header);	
	}
}
