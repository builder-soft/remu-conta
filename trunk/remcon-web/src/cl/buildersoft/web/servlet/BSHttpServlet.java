package cl.buildersoft.web.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.framework.database.BSmySQL;

public abstract class BSHttpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected abstract BSTableConfig getBSTableConfig(HttpServletRequest request);

	public BSHttpServlet() {
		super();
	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// BSHeadConfig head = getBSHeadConfig();
		BSTableConfig table = getBSTableConfig(request);

		String uri = request.getRequestURI().substring(
				request.getContextPath().length());

		table.setUri(uri);

		HttpSession session = request.getSession();
		synchronized (session) {
			session.setAttribute("BSTable", table);
			// session.setAttribute("BSHead", head);
		}

		request.getRequestDispatcher("/servlet/table/LoadTable").forward(
				request, response);
	}

	protected BSTableConfig initTable(HttpServletRequest request,
			String database, String tableName) {
		BSTableConfig table = new BSTableConfig(database, tableName);
		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request.getServletContext(),
				database);
		table.configFields(conn, mysql);
		return table;
	}

	protected void hideFields(BSTableConfig table, String... hideFields) {
		for (String fieldName : hideFields) {
			table.getField(fieldName).setVisible(false);
		}
	}
}
