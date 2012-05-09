package cl.buildersoft.web.servlet.table;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.buildersoft.framework.beans.BSAccount;
import cl.buildersoft.framework.beans.BSField;
import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.exception.BSDataBaseException;

/**
 * Servlet implementation class EditRecord
 */
@WebServlet("/servlet/table/EmployeeInformation")
public class EmployeeInformation extends AbstractServletUtil {

	private static final long serialVersionUID = -5785656616097922095L;

	public EmployeeInformation() {
		super();
	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		BSTableConfig table = null;
		synchronized (session) {
			table = (BSTableConfig) session.getAttribute("BSTable");
		}

		String idField = table.getIdField().getName();
		String sql = getSQL4Search(table, idField);
		Long id = Long.parseLong(request.getParameter(idField));

		Connection conn = null;
		BSmySQL mySQL = new BSmySQL();

		conn = mySQL.getConnection(request);
		ResultSet rs = mySQL.queryResultSet(conn, sql, array2List(id));
		resultset2Table(rs, table);
		
		BSmySQL mysql = new BSmySQL();
		ResultSet rsApv = mysql.callSingleSP(conn, "pGetAPVList",null);
		BSAccount bsApv =  null;
		List<BSAccount> listadoApv = new ArrayList<BSAccount>();
		try {
			while (rsApv.next()) {
				bsApv = new BSAccount();
				bsApv.setId(rsApv.getLong("cId"));
				bsApv.setKey(rsApv.getString("cKey"));
				bsApv.setValue(rsApv.getString("cName"));
				listadoApv.add(bsApv);
				//bu.search(conn, domainAttribute);

				//out.put(domainAttribute.getKey(), domainAttribute);
			}
			mysql.closeSQL(rs);
		} catch (SQLException e) {
			throw new BSDataBaseException("1000", e.getMessage());
		}
		
		List<Object> parameter = new ArrayList<Object>();
		Object param1 = "1";
		Object param2 = "47";
		parameter.add(param1);
		parameter.add(param2);
		ResultSet rsApvForEmp = mysql.callSingleSP(conn, "pGetAccountsForEmployee",parameter);
		
		BSAccount bsApvEmp =  null;
		List<BSAccount> listadoApvEmp = new ArrayList<BSAccount>();
		try {
			while (rsApvForEmp.next()) {
				bsApvEmp = new BSAccount();
				bsApvEmp.setId(rsApvForEmp.getLong("cId"));
				bsApvEmp.setKey(rsApvForEmp.getString("cKeyInstitution"));
				bsApvEmp.setValue(rsApvForEmp.getString("cNameInstitution"));
				bsApvEmp.setcKeyCurrency(rsApvForEmp.getString("cKeyCurrency"));
				bsApvEmp.setcIdCurrency(rsApvForEmp.getString("cCurrency"));
				listadoApvEmp.add(bsApvEmp);
				//bu.search(conn, domainAttribute);

				//out.put(domainAttribute.getKey(), domainAttribute);
			}
			mysql.closeSQL(rsApvForEmp);
		} catch (SQLException e) {
			throw new BSDataBaseException("1000", e.getMessage());
		}		
		
		ResultSet rsCurrency = mysql.callSingleSP(conn, "pGetCurrencyList",null);
		BSAccount bsCurrency =  null;
		List<BSAccount> listadoCurrency = new ArrayList<BSAccount>();
		try {
			while (rsCurrency.next()) {
				bsCurrency = new BSAccount();
				bsCurrency.setId(rsCurrency.getLong("cId"));
				bsCurrency.setKey(rsCurrency.getString("cKey"));
				bsCurrency.setValue(rsCurrency.getString("cName"));
				listadoCurrency.add(bsCurrency);
				//bu.search(conn, domainAttribute);

				//out.put(domainAttribute.getKey(), domainAttribute);
			}
			mysql.closeSQL(rs);
		} catch (SQLException e) {
			throw new BSDataBaseException("1000", e.getMessage());
		}			
		
		request.setAttribute("listadoApv", listadoApv);
		request.setAttribute("listadoCurrency", listadoCurrency);
		request.setAttribute("listadoApvEmp", listadoApvEmp);
		request.setAttribute("Data", rs);

		request.setAttribute("Action", "Update");
		request.getRequestDispatcher("/WEB-INF/jsp/table/previtional-information.jsp")
				.forward(request, response);
	}

	private void resultset2Table(ResultSet rs, BSTableConfig table) {
		BSField[] fields = table.getFields();
		try {
			if (rs.next()) {
				for (BSField f : fields) {
					f.setValue(rs.getObject(f.getName()));
				}
			}
		} catch (SQLException e) {
			throw new BSDataBaseException("0300", e.getMessage());
		}
	}

	private String getSQL4Search(BSTableConfig table, String idField) {
		BSField[] fields = table.getFields();
		String sql = "SELECT " + getFieldsNamesWithCommas(fields);
		sql += " FROM " + table.getDatabase() + "." + table.getTableName();
		sql += " WHERE " + idField + "=?";
		return sql;
	}
}
