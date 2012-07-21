package cl.buildersoft.web.servlet.remuneration.events.overtime;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.business.beans.Employee;
import cl.buildersoft.business.beans.Overtime;
import cl.buildersoft.business.beans.Period;
import cl.buildersoft.business.service.EmployeeService;
import cl.buildersoft.business.service.impl.EmployeeServiceImpl;
import cl.buildersoft.framework.database.BSBeanUtils;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.util.BSConfig;

@WebServlet("/servlet/remuneration/events/overtime/OvertimeEmployee")
public class OvertimeEmployee extends HttpServlet {
	private static final long serialVersionUID = 835430674208654536L;

	public OvertimeEmployee() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);
		BSBeanUtils bu = new BSBeanUtils();

		Period period = getPeriod(conn, mysql, bu, request);
		Employee employee = getEmployee(conn, request);
		List<Overtime> overtimes = getOvertimes(conn, employee, period);

		request.setAttribute("Period", period);
		request.setAttribute("Employee", employee);
		request.setAttribute("Overtimes", overtimes);

		request.getRequestDispatcher("/WEB-INF/jsp/remuneration/events/overtime/overtime.jsp").forward(request, response);

	}

	private List<Overtime> getOvertimes(Connection conn, Employee employee, Period period) {
		BSBeanUtils bu = new BSBeanUtils();

		List<Overtime> out = (List<Overtime>) bu.list(conn, new Overtime(), "cEmployee=? AND cPeriod=?", employee.getId(),
				period.getId());

		return out;
	}

	private Employee getEmployee(Connection conn, HttpServletRequest request) {
		Employee employee = null;
		String employeeId = request.getParameter("cId") == null ? (String) request.getAttribute("cId") : request
				.getParameter("cId");
		Long id = Long.parseLong(employeeId);
		EmployeeService employeeService = new EmployeeServiceImpl();
		employee = employeeService.getEmployee(conn, id);
		return employee;
	}

	private Period getPeriod(Connection conn, BSmySQL mysql, BSBeanUtils bu, HttpServletRequest request) {
		Long periodId = Long.parseLong(mysql.callFunction(conn, "fGetOpenedPeriod", null));
		Period period = new Period();
		period.setId(periodId);
		bu.search(conn, period);
		return period;
	}

}
