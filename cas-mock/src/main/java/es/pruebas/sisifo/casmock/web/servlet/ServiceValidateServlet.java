package es.pruebas.sisifo.casmock.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@WebServlet("/serviceValidate")
public class ServiceValidateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceValidateServlet.class);

    public ServiceValidateServlet() {
        super();
    }


	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		final String service = request.getParameter("service");
		final String ticket = request.getParameter("ticket");

		LOGGER.info("Validating ticket '{}' for service '{}'", ticket, service);

		final String ticketUsername = getUserNameFromTicket(ticket);

		response.setContentType("text/xml;charset=UTF-8");

		final PrintWriter writer = response.getWriter();
		writer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		writer.append(getCasResponse(ticketUsername));
		writer.close();
	}


	private String getUserNameFromTicket(final String ticket) {
		return ticket.substring("ticket-".length());
	}

	private String getCasResponse(final String username) {
		return "<cas:serviceResponse xmlns:cas=\"http://www.yale.edu/tp/cas\">\r\n" +
				" <cas:authenticationSuccess>\r\n" +
				"  <cas:user>" + username  + "</cas:user>\r\n" +
				"  <cas:proxyGrantingTicket>PGTIOU-84678-8a9dxxxx</cas:proxyGrantingTicket>\r\n" +
				" </cas:authenticationSuccess>\r\n" +
				"</cas:serviceResponse>";
	}

}
