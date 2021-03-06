package cl.buildersoft.web.servlet.csv;

import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.framework.beans.BSField;
import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.framework.dataType.BSDataType;
import cl.buildersoft.framework.dataType.BSDataTypeUtil;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.exception.BSDataBaseException;
import cl.buildersoft.framework.util.BSConfig;

/**
 * Servlet implementation class DownloadFile
 */
// @WebServlet("/servlet/csv/DownloadCSV")
public abstract class DownloadCSVServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected abstract BSTableConfig getBSTableConfig();

	public DownloadCSVServlet() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BSTableConfig table = getBSTableConfig();

		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);

		table.configFields(conn, mysql);
		BSField[] fields = table.deleteId();

		String sql = getSQL(table, fields);

		char separator = new BSConfig().getCSVSeparator(conn);

		ServletOutputStream output = configHeaderAsCSV(response, table.getTableName());
		CsvWriter csv = new CsvWriter(output, separator, Charset.defaultCharset());

		for (BSField field : fields) {
			csv.write(field.getName());
		}
		csv.endRecord();

		ResultSet rs = mysql.queryResultSet(conn, sql, null);

		try {
			Object value = null;
			while (rs.next()) {
				for (BSField field : fields) {
					value = rs.getObject(field.getName());
					BSDataType type = BSDataTypeUtil.create(field.toString());
					value = type.format(conn, value);
					csv.write(value.toString());
				}
				csv.endRecord();
			}
		} catch (SQLException e) {
			throw new BSDataBaseException(e);
		} finally {
			mysql.closeSQL(rs);
			mysql.closeConnection(conn);
		}

		csv.flush();
		csv.close();
	}

	private ServletOutputStream configHeaderAsCSV(HttpServletResponse response, String fileName) throws IOException {
		ServletOutputStream output = response.getOutputStream();
		response.setContentType("text/csv");
		String disposition = "attachment; fileName=" + fileName + ".csv";
		response.setHeader("Content-Disposition", disposition);
		return output;
	}

	private String getSQL(BSTableConfig table, BSField[] fields) {
		String out = "SELECT " + table.unSplitFieldNames(fields, ",");
		out += " FROM " + table.getDatabase() + "." + table.getTableName();
		return out;
	}
}
