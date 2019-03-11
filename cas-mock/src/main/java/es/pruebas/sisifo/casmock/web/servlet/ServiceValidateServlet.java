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
		if (ticketUsername != null) {
			writer.append(getCasResponseOK(ticketUsername));
		}
		else {
			writer.append(getCasResponseErr(ticketUsername));
		}
		writer.close();
	}


	private String getUserNameFromTicket(final String ticket) {
		if (ticket != null && !ticket.equalsIgnoreCase("null")) {
			return ticket.substring("ticket-".length());
		}
		else {
			return null;
		}
	}

	private String getCasResponseOK(final String username) {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" +
				"<cas:serviceResponse xmlns:cas=\"http://www.yale.edu/tp/cas\">\r\n" +
				" <cas:authenticationSuccess>\r\n" +
				"  <cas:user>" + username  + "</cas:user>\r\n" +
				"  <cas:proxyGrantingTicket>PGTIOU-84678-8a9dxxxx</cas:proxyGrantingTicket>\r\n" +
				" </cas:authenticationSuccess>\r\n" +
				"</cas:serviceResponse>";
	}
	
	private String getCasResponseErr(final String ticket) {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" +
				"<cas:serviceResponse xmlns:cas=\"http://www.yale.edu/tp/cas\">\r\n" + 
				" <cas:authenticationFailure code=\"INVALID_TICKET\">\r\n" + 
				"    Ticket " + ticket + " not recognized\r\n" + 
				"  </cas:authenticationFailure>\r\n" + 
				"</cas:serviceResponse>";
	}

}
