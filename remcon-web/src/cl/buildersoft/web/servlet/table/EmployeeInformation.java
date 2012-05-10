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
import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.framework.beans.Employee;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.exception.BSDataBaseException;
import cl.buildersoft.framework.util.BSBeanUtilsSP;

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
		Long id = Long.parseLong(request.getParameter("cId"));
		synchronized (session) {
			table = (BSTableConfig) session.getAttribute("BSTable");
		}

		Connection conn = null;
		BSmySQL mySQL = new BSmySQL();

		conn = mySQL.getConnection(request);
		Employee employee = getEmployee(conn, new BSBeanUtilsSP(), id);
				
		BSmySQL mysql = new BSmySQL();
		ResultSet rsApv = mysql.callSingleSP(conn, "pListBoardByType","APV");
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
			mysql.closeSQL(rsApv);
		} catch (SQLException e) {
			throw new BSDataBaseException("1000", e.getMessage());
		}
		
		ResultSet rsAfp = mysql.callSingleSP(conn, "pListBoardByType","PFM");
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
		parameter.add(id);
		parameter.add("APV");
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
		parameterEmp.add(id);
		parameterEmp.add("PFM");
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
		
		
		
		ResultSet rsCurrency = mysql.callSingleSP(conn, "pListBoardByType","CURRENCY");
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
	
		ResultSet rsExBox = mysql.callSingleSP(conn, "pListBoardByType","EX_BOX");
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
		parameterExboxEmp.add(id);
		parameterExboxEmp.add("EX_BOX");		
		ResultSet rsExBoxEmp = mysql.callSingleSP(conn, "pGetAccountsForEmployeeAndTypeBoard",parameterExboxEmp);
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
		
		ResultSet rsHealth = mysql.callSingleSP(conn, "pListBoardByType","HEALTH");
		Account bsHealth =  null;
		List<Account> listadoHealth = new ArrayList<Account>();
		try {
			while (rsHealth.next()) {
				bsHealth = new Account();
				bsHealth.setId(rsHealth.getLong("cId"));
				bsHealth.setKey(rsHealth.getString("cKey"));
				bsHealth.setValue(rsHealth.getString("cName"));
				listadoHealth.add(bsHealth);
			}
			mysql.closeSQL(rsHealth);
		} catch (SQLException e) {
			throw new BSDataBaseException("1000", e.getMessage());
		}		

		List<Object> parameterHealthEmp = new ArrayList<Object>();
		parameterHealthEmp.add(id);
		parameterHealthEmp.add("HEALTH");		
		ResultSet rsHealthEmp = mysql.callSingleSP(conn, "pGetAccountsForEmployeeAndTypeBoard",parameterHealthEmp);
		Account healthEmp = new Account();
		try {
			while (rsHealthEmp.next()) {
				healthEmp = new Account();
				healthEmp.setId(rsHealthEmp.getLong("cId"));
				healthEmp.setKey(rsHealthEmp.getString("cKey"));
				healthEmp.setValue(rsHealthEmp.getString("cName"));
			}
			mysql.closeSQL(rsHealthEmp);
		} catch (SQLException e) {
			throw new BSDataBaseException("1000", e.getMessage());
		}		
		
		request.setAttribute("listadoAfp", listadoAfp);
		request.setAttribute("listadoApv", listadoApv);
		request.setAttribute("listadoCurrency", listadoCurrency);
		request.setAttribute("listadoApvEmp", listadoApvEmp);
		request.setAttribute("listadoExBox", listadoExBox);	
		request.setAttribute("listadoHealth", listadoHealth);
		request.setAttribute("afpEmp", bsAfpEmp);
		request.setAttribute("healthEmp", healthEmp);

		request.setAttribute("Action", "Update");
		request.getRequestDispatcher("/WEB-INF/jsp/table/previtional-information.jsp")
				.forward(request, response);
	}


	private Employee getEmployee(Connection conn, BSBeanUtilsSP bu, Long id) {
		Employee out = new Employee();
		out.setId(id);
		bu.search(conn, out);
		return out;
	}
}
