package cl.buildersoft.web.servlet.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.util.BSConfig;

public abstract class AbstractFormatServlet extends HttpServlet {
	private static final long serialVersionUID = 7962194037862243536L;

	protected void setHeaders(HttpServletResponse response) {
		response.setDateHeader("Date", new Date().getTime());
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-cache, must-revalidate, s-maxage=0, proxy-revalidate, private");
		response.setHeader("Pragma", "no-cache");
		response.setContentType("text/plain");
	}

	protected Connection getConnection(HttpServletRequest request) {
		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);
		return conn;
	}

	protected String getDecimalSeparator(Connection conn) {
		BSConfig config = new BSConfig();
		return config.getString(conn, "DECIMAL_SEPARATOR");
	}

	protected String getGroupSeparator(Connection conn) {
		BSConfig config = new BSConfig();
		return config.getString(conn, "GROUP_SEPARATOR");
	}

	protected PrintWriter writeToBrowser(HttpServletResponse response, String out) throws IOException {
		PrintWriter writer = response.getWriter();
		writer.write(out);
		return writer;
	}

	protected void endWrite(PrintWriter writer) {
		writer.flush();
		writer.close();
	}
}
