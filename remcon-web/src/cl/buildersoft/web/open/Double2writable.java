package cl.buildersoft.web.open;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.util.BSConfig;

@WebServlet("/open/Double2writable")
public class Double2writable extends HttpServlet {
	private static final long serialVersionUID = -1937146590122545659L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BSmySQL mysql = new BSmySQL();
		Connection conn = null;
		String decimalSeparator = ",";
		String groupSeparator = ".";
		String out = null;
		try {
			conn = mysql.getConnection(request);
		} catch (Exception e) {
			decimalSeparator = ",";
			groupSeparator = ".";
		}

		if (conn != null) {
			BSConfig config = new BSConfig();
			decimalSeparator = config.getString(conn, "DECIMAL_SEPARATOR");
			groupSeparator = config.getString(conn, "GROUP_SEPARATOR");
		}
		String value = request.getParameter("Value");
		value = value.replaceAll("[" + groupSeparator + "]", "");
		value = value.replaceAll("[" + decimalSeparator + "]", ".");

		try {
			double number = Double.valueOf(value).doubleValue();
			out = "" + number;
		} catch (Exception e) {
			out = "NaN";
		}
		setHeaders(response);
		PrintWriter writer = response.getWriter();
		writer.write(out);
		writer.flush();
	}

	private void setHeaders(HttpServletResponse response) {
		// HttpServletResponse response = (HttpServletResponse) servletResponse;
		response.setDateHeader("Date", new Date().getTime());
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-cache, must-revalidate, s-maxage=0, proxy-revalidate, private");
		response.setHeader("Pragma", "no-cache");
		response.setContentType("text/plain");
	}
}
