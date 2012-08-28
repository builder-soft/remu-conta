package cl.buildersoft.web.servlet.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.framework.util.BSWeb;

@WebServlet("/servlet/ajax/IntegerToFormated")
public class IntegerToFormated extends AbstractFormatServlet {
	private static final long serialVersionUID = 1834335139177348807L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String value = request.getParameter("Value");
		String out = null;
		String format = BSWeb.getFormatInteger(request);
		Double valueDouble = null;
		try {
			valueDouble = Double.parseDouble(value);
			out = BSWeb.number2String(valueDouble, format);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			out = "NaN";
		}

		setHeaders(response);
		endWrite(writeToBrowser(response, out));
	}
}
