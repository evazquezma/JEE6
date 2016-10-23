package es.pruebas.sisifo.memoryleak;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name="errorServlet1", value="/error1")
public class ErrorServlet extends HttpServlet {
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


	private void generateOOM() throws InterruptedException {
		int iteratorValue = 20;
		System.out.println("\n=================> OOM test started..\n");
		for (int outerIterator = 1; outerIterator < 20; outerIterator++) {
			System.out.println("Iteration " + outerIterator + " Free Mem: " + Runtime.getRuntime().freeMemory());
			int loop1 = 2;
			final int[] memoryFillIntVar = new int[iteratorValue];
			do {
				memoryFillIntVar[loop1] = 0;
				loop1--;
			} while (loop1 > 0);

			iteratorValue *= 5;
			System.out.println("\nRequired Memory for next loop: " + iteratorValue);
			Thread.sleep(500L);
		}
	}
}
