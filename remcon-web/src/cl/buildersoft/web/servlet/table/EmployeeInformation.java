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
import cl.buildersoft.framework.beans.Agreement;
import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.framework.beans.Employee;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.exception.BSDataBaseException;
import cl.buildersoft.framework.util.BSBeanUtilsSP;
import cl.buildersoft.web.servlet.config.employee.AgreementService;
import cl.buildersoft.web.servlet.config.employee.AgreementServiceImpl;

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
		Long id = Long.parseLong(request.getParameter("cId"));
		Connection conn = null;
		BSmySQL mySQL = new BSmySQL();

		conn = mySQL.getConnection(request);
				
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
			}
			mysql.closeSQL(rsAfp);
		} catch (SQLException e) {
			throw new BSDataBaseException("1000", e.getMessage());
		}
		
		
		List<Object> parameter = new ArrayList<Object>();
		parameter.add(id);
		parameter.add("APV");
		ResultSet rsApvForEmp = mysql.callSingleSP(conn, "pListAccountsForEmployeeAndType",parameter);
		
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
		
		Agreement agreementEmp = getAgreement(conn,new BSBeanUtilsSP(),id);
		
		request.setAttribute("listadoAfp", listadoAfp);
		request.setAttribute("listadoApv", listadoApv);
		request.setAttribute("listadoCurrency", listadoCurrency);
		request.setAttribute("listadoApvEmp", listadoApvEmp);
		request.setAttribute("listadoExBox", listadoExBox);	
		request.setAttribute("listadoHealth", listadoHealth);
		request.setAttribute("agreementEmp", agreementEmp);

		request.setAttribute("Action", "Update");
		request.getRequestDispatcher("/WEB-INF/jsp/table/previtional-information.jsp")
				.forward(request, response);
	}


	public Agreement getAgreement(Connection conn, BSBeanUtilsSP bu,
			Long idEmployee) {
		AgreementService agreementService = new AgreementServiceImpl();
		Agreement out = agreementService.getAgreementByEmployee(conn,
				idEmployee);
		return out;
	}
	
	private Employee getEmployee(Connection conn, BSBeanUtilsSP bu, Long id) {
		Employee out = new Employee();
		out.setId(id);
		bu.search(conn, out);
		return out;
	}
}
