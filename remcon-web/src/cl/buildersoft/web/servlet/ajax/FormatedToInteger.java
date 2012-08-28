package cl.buildersoft.web.servlet.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet/ajax/FormatedToInteger")
public class FormatedToInteger extends AbstractFormatServlet {
	private static final long serialVersionUID = 139721871849042522L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		String decimalSeparator = ",";
		String groupSeparator = ".";
		String out = null;
		try {
			conn = getConnection(request);
		} catch (Exception e) {
			decimalSeparator = ",";
			groupSeparator = ".";
		}

		if (conn != null) {
			decimalSeparator = getDecimalSeparator(conn);
			groupSeparator = getGroupSeparator(conn);
		}
		String value = request.getParameter("Value");
		value = value.replaceAll("[" + groupSeparator + "]", "");
		value = value.replaceAll("[" + decimalSeparator + "]", ".");

		try {
//			double number = Double.valueOf(value).doubleValue();
			Integer number = Integer.parseInt(value);
			out = "" + number;
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			out = "NaN";
		}
		setHeaders(response);
		PrintWriter writer = writeToBrowser(response, out);
		endWrite(writer);

	}

}
