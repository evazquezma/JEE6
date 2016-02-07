package es.pruebas.sisifo.async.service;

import java.util.concurrent.Future;

import es.pruebas.sisifo.async.model.InvoiceReport;

public interface InvoiceReportService {

	Future<InvoiceReport> generateReport();

}
