package cl.buildersoft.web.servlet.remuneration.events.holiday;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.business.beans.Agreement;
import cl.buildersoft.business.beans.Employee;
import cl.buildersoft.business.beans.HolidayDevelop;
import cl.buildersoft.business.service.AgreementService;
import cl.buildersoft.business.service.EmployeeService;
import cl.buildersoft.business.service.HolidayService;
import cl.buildersoft.business.service.impl.AgreementServiceImpl;
import cl.buildersoft.business.service.impl.EmployeeServiceImpl;
import cl.buildersoft.business.service.impl.HolidayServiceImpl;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.util.BSConfig;

@WebServlet("/servlet/remuneration/events/holiday/HolidayMain")
public class HolidayMain extends HttpServlet {
	private static final long serialVersionUID = 2684269459350769175L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);

		Employee employee = getEmployee(conn, request);
		ResultSet holidays = mysql.callSingleSP(conn, "pListHoliday", employee.getId());
		Agreement agreement = getAgreement(conn, employee);

		List<Object> params = getParameters(employee, agreement);
		ResultSet holidayInfo = mysql.callSingleSP(conn, "pGetHolidayInfo", params);
		List<HolidayDevelop> holidayDevelop = getHolidayDevelop(conn, employee);
		Double normalBalance = getNormalBalance(holidayDevelop);
		Double creepingBalance = getCreepingBalance(holidayDevelop);
		String defaultHoliday = getDefaultHoliday(conn);
		

		request.setAttribute("NormalBalance", normalBalance);
		request.setAttribute("CreepingBalance", creepingBalance);
		request.setAttribute("Agreement", agreement);
		request.setAttribute("Employee", employee);
		request.setAttribute("Holidays", holidays);
		request.setAttribute("HolidayInfo", holidayInfo);
		request.setAttribute("HolidayDevelop", holidayDevelop);
		request.setAttribute("DefaultHoliday", defaultHoliday);

		request.getRequestDispatcher("/WEB-INF/jsp/remuneration/events/holiday/holiday.jsp").forward(request, response);
	}

	private String getDefaultHoliday(Connection conn) {
		BSConfig config  = new BSConfig();
		return config.getString(conn, "DAYS_FOR_YEAR");
	}

	private Double getCreepingBalance(List<HolidayDevelop> holidayDevelop) {
		HolidayDevelop last = getLastDevelop(holidayDevelop);
		return last.getCreepingBalance();
	}

	private Double getNormalBalance(List<HolidayDevelop> holidayDevelop) {
		HolidayDevelop last = getLastDevelop(holidayDevelop);
		return last.getNormalBalance();
	}

	private HolidayDevelop getLastDevelop(List<HolidayDevelop> holidayDevelop) {
		HolidayDevelop last = holidayDevelop.get(holidayDevelop.size() - 1);
		return last;
	}

	private List<HolidayDevelop> getHolidayDevelop(Connection conn, Employee employee) {
		HolidayService hs = new HolidayServiceImpl();
		List<HolidayDevelop> out = hs.listDevelop(conn, employee.getId());
		return out;
	}

	private List<Object> getParameters(Employee employee, Agreement agreement) {
		List<Object> params = new ArrayList<Object>();
		params.add(employee.getId());
		params.add(agreement.getStartContract());
		params.add(getLastDate(agreement));
		return params;
	}

	private Object getLastDate(Agreement agreement) {
		return agreement.getContractType().equals(1L) ? new Date() : agreement.getEndContract();
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
