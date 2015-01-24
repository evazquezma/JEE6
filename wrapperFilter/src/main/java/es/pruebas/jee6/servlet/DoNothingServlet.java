package es.pruebas.jee6.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DoNothingServlet
 */
@WebServlet(urlPatterns="/*", name="Test")
public class DoNothingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 PrintWriter out = response.getWriter();
        // Output the results
        out.println("<html lang=\"es\">");
        out.println("<head>");
        out.println("<title>nothing</title>");
        out.println("</head>");
        out.println("<body>");        
        out.println("<h2>ps, ps. Check de logs ;)</h2>");
        out.println("<p>"
        		+ "If you call any url except '/dontCallFilter/' or '/neitherInThisURL/' you will see HELLO WORDL."
        		+ "Otherwise you will only see WORLD because de first filter is not invoked."
        		+ "</p>");
        out.println("</body>");
        out.println("</html>");
        out.close();		
	}

}
