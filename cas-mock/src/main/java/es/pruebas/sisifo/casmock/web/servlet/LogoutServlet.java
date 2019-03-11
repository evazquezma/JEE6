package es.pruebas.sisifo.casmock.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger(LogoutServlet.class);

    public LogoutServlet() {
        super();
    }


	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		final String service = request.getParameter("service");
		LOGGER.info("Logout. Service: {}", service);

		if (service != null) {
			response.sendRedirect(service);
		}
		else {
			response.getWriter().append("Logout successfuly");
		}
	}
}
