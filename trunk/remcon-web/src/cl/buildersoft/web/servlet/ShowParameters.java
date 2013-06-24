package cl.buildersoft.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShowParameters
 */
@WebServlet("/servlet/ShowParameters")
public class ShowParameters extends HttpServlet {
	private static final long serialVersionUID = -6514375742618981280L;

	public ShowParameters() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/jsp/common/header2.jsp").include(request, response);
		request.getRequestDispatcher("/WEB-INF/jsp/common/menu2.jsp").include(request, response);

		PrintWriter out = response.getWriter();
		showParameters(request, out);
		out.println("<hr>");
		showAttributes(request, out);

		request.getRequestDispatcher("/WEB-INF/jsp/common/footer2.jsp").include(request, response);

		out.flush();
	}

	private void showAttributes(HttpServletRequest request, PrintWriter out) {
		out.print("<h2 class='cTitle2'>Atributos</h2>");

		Enumeration<String> names = request.getAttributeNames();
		out.print("<ul>");
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();

			out.println("<li>" + name);
			Object value = request.getAttribute(name);
			out.println("(" + value.getClass().getName() + ") = <span>" + value.toString() + "</span>");
			out.println("</li>");
		}
		out.println("</ul>");
	}

	private void showParameters(HttpServletRequest request, PrintWriter out) {
		Enumeration<String> names = request.getParameterNames();

		out.print("<h2>Parámetros</h2>");
		out.print("<ul>");
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();

			out.println("<li>" + name);
			String[] values = request.getParameterValues(name);

			out.print("<ul>");
			for (String value : values) {
				out.println("<li>" + value + "</li>");
			}
			out.print("</ul></li>");

		}
		out.print("</ul>");

//		names = request.getAttributeNames();
//
//		out.print("<ul class='cLabel'>");
//		while (names.hasMoreElements()) {
//			String name = (String) names.nextElement();
//
//			out.println("<li>" + name);
//			Object value = request.getAttribute(name);
//
//			out.print("<ul>");
//			out.println("<li class='cData'>" + value.toString() + "</li>");
//			out.print("</ul></li>");
//		}
//		out.print("</ul>");
	}

}
