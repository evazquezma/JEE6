package es.pruebas.sisifo.casmock.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginServlet.class);

    public LoginServlet() {
        super();
    }


	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		final String service = request.getParameter("service");
		LOGGER.info("Login. Service: {}", service);

		request.setAttribute("service", service);
		final RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/pages/login/loginForm.jsp");
		dispatcher.forward(request, response);
	}


	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		final String service = request.getParameter("service");
		final String username = request.getParameter("username");
		final String password = request.getParameter("password");

		LOGGER.info("Mock succesful login. username: {}", username);

		final String ticket = buildTicket(username);
		response.sendRedirect(service + "?" + ticket);
	}


	private String buildTicket(final String username) {
		return "ticket-" + username;
	}
}
