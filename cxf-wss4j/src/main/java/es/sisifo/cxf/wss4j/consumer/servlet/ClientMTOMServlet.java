package es.sisifo.cxf.wss4j.consumer.servlet;

import java.io.File;
import java.io.IOException;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import es.sisifo.cxf.wss4j.consumer.mtom.HelloMTOMWebService;
import es.sisifo.cxf.wss4j.consumer.mtom.SendData.Data;

/**
 * Servlet implementation class ClientServlet
 */
@WebServlet(displayName = "ClientMTOMServlet", urlPatterns = "/clientMTOM")
public class ClientMTOMServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HelloMTOMWebService helloMTOMWebServiceClient;

	@Override
	public void init(final ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);

		final ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
		helloMTOMWebServiceClient = context.getBean(HelloMTOMWebService.class);
	}

	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		final Data data = new Data();
		data.setName("test");

		final DataSource source = new FileDataSource(new File("D:/tmp/arq/Impr/MiniApplet-v1-5-manual-integrador.pdf"));
		data.setFileData(new DataHandler(source));

		helloMTOMWebServiceClient.sendData(data);
		response.getWriter().append("Response: ").append("OK");
	}

}
