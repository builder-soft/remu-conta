package cl.buildersoft.web.servlet.remuneration.events.holiday;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.business.beans.Agreement;
import cl.buildersoft.business.beans.Employee;
import cl.buildersoft.business.beans.Holiday;
import cl.buildersoft.business.beans.HolidayDetail;
import cl.buildersoft.business.service.AgreementService;
import cl.buildersoft.business.service.EmployeeService;
import cl.buildersoft.business.service.HolidayService;
import cl.buildersoft.business.service.impl.AgreementServiceImpl;
import cl.buildersoft.business.service.impl.EmployeeServiceImpl;
import cl.buildersoft.business.service.impl.HolidayServiceImpl;
import cl.buildersoft.framework.database.BSmySQL;

@WebServlet("/servlet/remuneration/events/holiday/ReadCertificate")
public class ReadCertificate extends HttpServlet {
	private static final long serialVersionUID = 1304690747291004678L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("cId"));
		HolidayService hs = new HolidayServiceImpl();
//		BSBeanUtils bu = new BSBeanUtils();

		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);

		Holiday holiday = hs.getHoliday(conn, id);
		Employee employee = getEmployee(conn, holiday);
		Agreement agreement = getAgreement(conn, employee);
		List<HolidayDetail> holidayDetails = hs.getHolidayDetail(conn, id);

		request.setAttribute("Agreement", agreement);
		request.setAttribute("Employee", employee);
		request.setAttribute("Holiday", holiday);
		request.setAttribute("Days", holiday.getNormal() + holiday.getCreeping());
		request.setAttribute("To", hs.getEndDate(conn, holiday));
		request.setAttribute("HolidayDetails", holidayDetails);
		mysql.closeConnection(conn);
		
		request.getRequestDispatcher("/WEB-INF/jsp/remuneration/events/holiday/certificate.jsp").forward(request, response);
	}

	private List<HolidayDetail> getHolidayDetails(Connection conn, Long id) {
		 
		return null;
	}

	private Agreement getAgreement(Connection conn, Employee employee) {
		AgreementService as = new AgreementServiceImpl();
		Agreement agreement = as.getAgreementByEmployee(conn, employee.getId());
		return agreement;
	}

	private Employee getEmployee(Connection conn, Holiday holiday) {
		EmployeeService es = new EmployeeServiceImpl();
		return es.getEmployee(conn, holiday.getEmployee());

	}
}
