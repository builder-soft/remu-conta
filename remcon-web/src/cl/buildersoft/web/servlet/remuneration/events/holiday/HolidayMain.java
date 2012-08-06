package cl.buildersoft.web.servlet.remuneration.events.holiday;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.business.beans.Employee;
import cl.buildersoft.business.beans.Period;
import cl.buildersoft.business.service.EmployeeService;
import cl.buildersoft.business.service.impl.EmployeeServiceImpl;
import cl.buildersoft.framework.database.BSBeanUtils;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.web.servlet.remuneration.events.overtime.OvertimeMain;

@WebServlet("/servlet/remuneration/events/holiday/HolidayMain")
public class HolidayMain extends HttpServlet {
	private static final long serialVersionUID = 2684269459350769175L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);
		BSBeanUtils bu = new BSBeanUtils();

		Period period = getPeriod(conn, mysql, bu, request);
		Employee employee = getEmployee(conn, request);

		request.setAttribute("Period", period);
		request.setAttribute("Employee", employee);

		request.getRequestDispatcher("/WEB-INF/jsp/remuneration/events/holiday/holiday.jsp").forward(request, response);
	}

	private Employee getEmployee(Connection conn, HttpServletRequest request) {
		EmployeeService employeeService = new EmployeeServiceImpl();
		return employeeService.getEmployee(conn, request);
	}

	private Period getPeriod(Connection conn, BSmySQL mysql, BSBeanUtils bu, HttpServletRequest request) {
		OvertimeMain overtime = new OvertimeMain();
		Period period = overtime.getPeriod(conn, mysql, bu, request);
		return period;
	}

}
