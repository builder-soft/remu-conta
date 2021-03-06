package cl.buildersoft.web.servlet.common.crud;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.buildersoft.framework.beans.BSField;
import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.util.BSUtils;
import cl.buildersoft.framework.util.BSWeb;
import cl.buildersoft.web.servlet.common.AbstractServletUtil;

@WebServlet("/servlet/common/crud/InsertRecord")
public class InsertRecord extends AbstractServletUtil {

	private static final long serialVersionUID = 947236230190327847L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/common/no-access.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		BSTableConfig table = null;

		synchronized (session) {
			table = (BSTableConfig) session.getAttribute("BSTable");
		}

		String saveSP = table.getSaveSP();

		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);
		if (saveSP == null) {
			BSField[] fields = table.deleteId();
			String sql = getSQL(table, fields, request);
			// BSmySQL mySQL = new BSmySQL();
			// Connection conn = mySQL.getConnection(request);
			List<Object> params = getValues4Insert(conn, request, fields);
			Long id = mysql.insert(conn, sql, params);
			request.setAttribute("cId", id);
		} else {
			// String sql = getSQLsp(saveSP, table);
			// BSmySQL mySQL = new BSmySQL();
			// Connection conn = mySQL.getConnection(request);
			List<Object> params = getValues4Insert(conn, request, table.getFields());
			mysql.callSingleSP(conn, saveSP, params);
		}
		mysql.closeConnection(conn);

		request.getRequestDispatcher(LoadTable.URL).forward(request, response);
	}

	private String getSQL(BSTableConfig table, BSField[] fields, HttpServletRequest request) {
				String sql = "INSERT INTO " + table.getDatabase() + "." + table.getTableName();
		sql += "(" + BSUtils.unSplitField(fields, ",") + ") ";
		sql += " VALUES (" + getCommas(fields) + ")";

		return sql;
	}

	private String getSQLsp(String spName, BSTableConfig table) {
		String sql = "call " + table.getDatabase() + "." + spName;
		sql += "(" + getCommas(table.getFields()) + ") ";
		return sql;
	}

	private List<Object> getValues4Insert(Connection conn, HttpServletRequest request, BSField[] fields) {

		List<Object> out = new ArrayList<Object>();
		Object value = null;

		for (BSField field : fields) {
			value = BSWeb.value2Object(conn, request, field, true);
			out.add(value);

		}
		return out;
	}

	/**
	 * <code>
	private List<Object> getValues4sp(Connection conn,
			HttpServletRequest request, BSField[] fields) {

		List<Object> out = new ArrayList<Object>();
		Object value = null;

		for (BSField field : fields) {
			value = BSWeb.value2Object(conn, request, field, true);
			out.add(value);
		}
		return out;
	}
	</code>
	 */
}
