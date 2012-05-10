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

import cl.buildersoft.framework.beans.Account;
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
		
		List<Object> parameterApv = new ArrayList<Object>();
		Object paramApv = "APV";
		parameterApv.add(paramApv);	
		
		BSmySQL mysql = new BSmySQL();
		ResultSet rsApv = mysql.callSingleSP(conn, "pGetTboardListByType",parameterApv);
		Account bsApv =  null;
		List<Account> listadoApv = new ArrayList<Account>();
		try {
			while (rsApv.next()) {
				bsApv = new Account();
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

		List<Object> parameterAfp = new ArrayList<Object>();
		Object paramAfp = "PFM";
		parameterAfp.add(paramAfp);		
		ResultSet rsAfp = mysql.callSingleSP(conn, "pGetTboardListByType",parameterAfp);
		Account bsAfp =  null;
		List<Account> listadoAfp = new ArrayList<Account>();
		try {
			while (rsAfp.next()) {
				bsAfp = new Account();
				bsAfp.setId(rsAfp.getLong("cId"));
				bsAfp.setKey(rsAfp.getString("cKey"));
				bsAfp.setValue(rsAfp.getString("cName"));
				listadoAfp.add(bsAfp);
				//bu.search(conn, domainAttribute);

				//out.put(domainAttribute.getKey(), domainAttribute);
			}
			mysql.closeSQL(rsAfp);
		} catch (SQLException e) {
			throw new BSDataBaseException("1000", e.getMessage());
		}
		
		
		List<Object> parameter = new ArrayList<Object>();
		Object param1 = "1";
		Object param2 = "APV";
		parameter.add(param1);
		parameter.add(param2);
		ResultSet rsApvForEmp = mysql.callSingleSP(conn, "pGetAccountsForEmployeeAndTypeBoard",parameter);
		
		Account bsApvEmp =  null;
		List<Account> listadoApvEmp = new ArrayList<Account>();
		try {
			while (rsApvForEmp.next()) {
				bsApvEmp = new Account();
				bsApvEmp.setId(rsApvForEmp.getLong("cId"));
				bsApvEmp.setKey(rsApvForEmp.getString("cKeyInstitution"));
				bsApvEmp.setValue(rsApvForEmp.getString("cNameInstitution"));
				bsApvEmp.setcKeyCurrency(rsApvForEmp.getString("cKeyCurrency"));
				bsApvEmp.setcIdCurrency(rsApvForEmp.getLong("cCurrency"));
				bsApvEmp.setcAmount(rsApvForEmp.getLong("cAmount"));
				listadoApvEmp.add(bsApvEmp);
				//bu.search(conn, domainAttribute);

				//out.put(domainAttribute.getKey(), domainAttribute);
			}
			mysql.closeSQL(rsApvForEmp);
		} catch (SQLException e) {
			throw new BSDataBaseException("1000", e.getMessage());
		}	

		List<Object> parameterEmp = new ArrayList<Object>();
		Object paramEmp = "1";
		Object paramAfpEmp = "PFM";
		parameterEmp.add(paramEmp);
		parameterEmp.add(paramAfpEmp);
		ResultSet rsAfpForEmp = mysql.callSingleSP(conn, "pGetAccountsForEmployeeAndTypeBoard",parameterEmp);
		
		Account bsAfpEmp =  null;
		try {
			while (rsAfpForEmp.next()) {
				bsAfpEmp = new Account();
				bsAfpEmp.setId(rsAfpForEmp.getLong("cId"));
				bsAfpEmp.setKey(rsAfpForEmp.getString("cKeyInstitution"));
				bsAfpEmp.setValue(rsAfpForEmp.getString("cNameInstitution"));
				bsAfpEmp.setcKeyCurrency(rsAfpForEmp.getString("cKeyCurrency"));
				bsAfpEmp.setcIdCurrency(rsAfpForEmp.getLong("cCurrency"));
				bsAfpEmp.setcAmount(rsAfpForEmp.getLong("cAmount"));
			}
			mysql.closeSQL(rsApvForEmp);
		} catch (SQLException e) {
			throw new BSDataBaseException("1000", e.getMessage());
		}
		
		
		
		ResultSet rsCurrency = mysql.callSingleSP(conn, "pGetCurrencyList",null);
		Account bsCurrency =  null;
		List<Account> listadoCurrency = new ArrayList<Account>();
		try {
			while (rsCurrency.next()) {
				bsCurrency = new Account();
				bsCurrency.setId(rsCurrency.getLong("cId"));
				bsCurrency.setKey(rsCurrency.getString("cKey"));
				bsCurrency.setValue(rsCurrency.getString("cName"));
				listadoCurrency.add(bsCurrency);
				//bu.search(conn, domainAttribute);

				//out.put(domainAttribute.getKey(), domainAttribute);
			}
			mysql.closeSQL(rsCurrency);
		} catch (SQLException e) {
			throw new BSDataBaseException("1000", e.getMessage());
		}			

		List<Object> parameterExbox = new ArrayList<Object>();
		Object paramExbox = "EX_BOX";
		parameterExbox.add(paramExbox);		
		ResultSet rsExBox = mysql.callSingleSP(conn, "pGetTboardListByType",parameterExbox);
		Account bsExBox =  null;
		List<Account> listadoExBox = new ArrayList<Account>();
		try {
			while (rsExBox.next()) {
				bsExBox = new Account();
				bsExBox.setId(rsExBox.getLong("cId"));
				bsExBox.setKey(rsExBox.getString("cKey"));
				bsExBox.setValue(rsExBox.getString("cName"));
				listadoExBox.add(bsExBox);
			}
			mysql.closeSQL(rsCurrency);
		} catch (SQLException e) {
			throw new BSDataBaseException("1000", e.getMessage());
		}		

		List<Object> parameterExboxEmp = new ArrayList<Object>();
		Object paramExboxEmp = "EX_BOX";
		parameterExboxEmp.add(paramExboxEmp);		
		ResultSet rsExBoxEmp = mysql.callSingleSP(conn, "pGetTboardListByType",parameterExboxEmp);
		Account exBoxEmp = new Account();
		try {
			while (rsExBoxEmp.next()) {
				exBoxEmp = new Account();
				exBoxEmp.setId(rsExBoxEmp.getLong("cId"));
				exBoxEmp.setKey(rsExBoxEmp.getString("cKey"));
				exBoxEmp.setValue(rsExBoxEmp.getString("cName"));
			}
			mysql.closeSQL(rsExBoxEmp);
		} catch (SQLException e) {
			throw new BSDataBaseException("1000", e.getMessage());
		}
		
		request.setAttribute("listadoAfp", listadoAfp);
		request.setAttribute("listadoApv", listadoApv);
		request.setAttribute("listadoCurrency", listadoCurrency);
		request.setAttribute("listadoApvEmp", listadoApvEmp);
		request.setAttribute("listadoExBox", listadoExBox);		
		request.setAttribute("afpEmp", bsAfpEmp);
		request.setAttribute("exBoxEmp", exBoxEmp);
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
