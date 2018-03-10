package es.sisifo.cxf.wss4j.consumer.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import es.sisifo.cxf.wss4j.consumer.HelloWebService;

/**
 * Servlet implementation class ClientServlet
 */
@WebServlet(displayName = "ClientServlet", urlPatterns = "/client")
public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HelloWebService helloWebServiceClient;

	@Override
	public void init(final ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);

		final ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
		helloWebServiceClient = context.getBean(HelloWebService.class);
	}

	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Response: ").append(helloWebServiceClient.sayHello(request.getParameter("name")));
	}

}
