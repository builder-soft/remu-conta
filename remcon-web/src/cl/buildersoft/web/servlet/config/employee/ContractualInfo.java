package cl.buildersoft.web.servlet.config.employee;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.framework.beans.Agreement;
import cl.buildersoft.framework.beans.BSBean;
import cl.buildersoft.framework.beans.Employee;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.util.BSBeanUtilsSP;

@WebServlet("/servlet/config/employee/ContractualInfo")
public class ContractualInfo extends HttpServlet {
	private static final long serialVersionUID = -8599267568451620681L;

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("cId"));
		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);

		BSBeanUtilsSP bu = new BSBeanUtilsSP();
		Employee emp = new Employee();
		emp.setId(id);
		bu.search(conn, emp);

		List<Object> prms = new ArrayList<Object>();
		prms.add(id);
		List<BSBean> agreements = bu.list(conn, new Agreement(),
				"pGetAgreementByEmployee", prms);

		request.setAttribute("Employee", emp);
		request.setAttribute("Agreement", (Agreement) agreements.get(0));
		request.getRequestDispatcher(
				"/WEB-INF/jsp/config/employee/contractual-info.jsp").forward(
				request, response);

	}
}
