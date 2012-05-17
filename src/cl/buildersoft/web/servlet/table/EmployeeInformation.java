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

import cl.buildersoft.framework.beans.Account;
import cl.buildersoft.framework.beans.Account2;
import cl.buildersoft.framework.beans.Agreement;
import cl.buildersoft.framework.beans.BSBean;
import cl.buildersoft.framework.beans.Board;
import cl.buildersoft.framework.beans.Employee;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.exception.BSDataBaseException;
import cl.buildersoft.framework.type.BSBoolean;
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
		BSBeanUtilsSP bu = new BSBeanUtilsSP();
		List<Board> listadoApv = (List<Board>) bu.list(conn, new Board(),
				"pListBoardByType", "APV");
		List<Board> listadoAfp = (List<Board>) bu.list(conn, new Board(),
				"pListBoardByType", "PFM");		

		List<Board> listadoCurrency = (List<Board>) bu.list(conn, new Board(),
				"pListBoardByType", "CURRENCY");			

		List<Board> listadoHealth = (List<Board>) bu.list(conn, new Board(),
				"pListBoardByType", "HEALTH");	

		List<Board> listadoExBox = (List<Board>) bu.list(conn, new Board(),
				"pListBoardByType", "EX_BOX");
		
		List<Object> parameter = new ArrayList<Object>();
		parameter.add(id);
		parameter.add("APV");
		 List<Account2> listadoApvEmp = (List<Account2>) bu.list(conn, new Account2(),
				"pListAccountsForEmployeeAndType2",parameter);	
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
