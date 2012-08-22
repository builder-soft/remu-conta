package cl.buildersoft.web.servlet.remuneration.events.holiday;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.business.beans.Agreement;
import cl.buildersoft.business.beans.Employee;
import cl.buildersoft.business.beans.Holiday;
import cl.buildersoft.business.service.AgreementService;
import cl.buildersoft.business.service.impl.AgreementServiceImpl;
import cl.buildersoft.framework.database.BSBeanUtils;
import cl.buildersoft.framework.database.BSmySQL;

@WebServlet("/servlet/remuneration/events/holiday/ReadCertificate")
public class ReadCertificate extends HttpServlet {
	private static final long serialVersionUID = 1304690747291004678L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("cId"));

		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);

		BSBeanUtils bu = new BSBeanUtils();
		Holiday holiday = new Holiday();

		holiday.setId(id);
		bu.search(conn, holiday);

		Employee employee = getEmployee(conn, bu, holiday);
		Agreement agreement = getAgreement(conn, bu, employee);

		request.setAttribute("Agreement", agreement);
		request.setAttribute("Employee", employee);
		request.setAttribute("Holiday", holiday);
		request.setAttribute("Days", holiday.getNormal()+holiday.getCreeping());
		request.getRequestDispatcher("/WEB-INF/jsp/remuneration/events/holiday/certificate.jsp").forward(request, response);
	}

	private Agreement getAgreement(Connection conn, BSBeanUtils bu, Employee employee) {
		AgreementService as = new AgreementServiceImpl();
		Agreement  agreement = as.getAgreementByEmployee(conn, employee.getId());
		return agreement;
	}

	private Employee getEmployee(Connection conn, BSBeanUtils bu, Holiday holiday) {
		Employee employee = new Employee();
		employee.setId(holiday.getEmployee());
		bu.search(conn, employee);
		return employee;
	}
}
