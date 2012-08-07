package cl.buildersoft.web.servlet.remuneration.events.holiday;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.business.beans.Agreement;
import cl.buildersoft.business.beans.Employee;
import cl.buildersoft.business.service.AgreementService;
import cl.buildersoft.business.service.EmployeeService;
import cl.buildersoft.business.service.impl.AgreementServiceImpl;
import cl.buildersoft.business.service.impl.EmployeeServiceImpl;
import cl.buildersoft.framework.database.BSmySQL;

@WebServlet("/servlet/remuneration/events/holiday/HolidayMain")
public class HolidayMain extends HttpServlet {
	private static final long serialVersionUID = 2684269459350769175L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);

		Employee employee = getEmployee(conn, request);
		ResultSet holidays = mysql.callSingleSP(conn, "pListHoliday", employee.getId());
		Agreement agreement = getAgreement(conn, employee);
		// List<Holiday> holidays = (List<Holiday>) bu.list(conn, new Holiday(),
		// "cEmployee=?", employee.getId());

		request.setAttribute("Agreement", agreement);
		request.setAttribute("Employee", employee);
		request.setAttribute("Holidays", holidays);

		request.getRequestDispatcher("/WEB-INF/jsp/remuneration/events/holiday/holiday.jsp").forward(request, response);
	}

	private Employee getEmployee(Connection conn, HttpServletRequest request) {
		EmployeeService employeeService = new EmployeeServiceImpl();
		return employeeService.getEmployee(conn, request);
	}

	private Agreement getAgreement(Connection conn, Employee employee) {
		AgreementService agreement = new AgreementServiceImpl();
		return agreement.getAgreementByEmployee(conn, employee.getId());
	}
}
