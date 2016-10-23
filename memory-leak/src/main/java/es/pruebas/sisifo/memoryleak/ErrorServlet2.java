package es.pruebas.sisifo.memoryleak;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name="errorServlet2", value="/error2")
public class ErrorServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		try {
			generateOOM();
		} catch (final Exception e) {
			e.printStackTrace();
			response.getWriter().append("Error durante la prueba").append(request.getContextPath());
			return;
		}

		response.getWriter().append("No se ha conseguido producir el error").append(request.getContextPath());
	}


	private void generateOOM() {
		final StringBuffer myStringBuffer = new StringBuffer();
		while (true) {
			myStringBuffer.append("Hello world");
		}
	}
}
