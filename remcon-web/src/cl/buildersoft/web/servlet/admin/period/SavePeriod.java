package cl.buildersoft.web.servlet.admin.period;

import java.io.IOException;
import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.business.beans.Period;
import cl.buildersoft.business.service.PeriodService;
import cl.buildersoft.business.service.impl.PeriodServiceImpl;
import cl.buildersoft.framework.database.BSBeanUtils;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.exception.BSUserException;
import cl.buildersoft.framework.util.BSDateTimeUtil;

@WebServlet("/servlet/admin/period/SavePeriod")
public class SavePeriod extends HttpServlet {
	private static final long serialVersionUID = 1776719588359492973L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idString = request.getParameter("cId");
		Long id = null;
		Period period = new Period();
		PeriodService ps = new PeriodServiceImpl();

		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);
		BSBeanUtils bu = new BSBeanUtils();

		if (idString == null) {
			Integer year = Integer.parseInt(request.getParameter("cYear"));
			Integer month = Integer.parseInt(request.getParameter("cMonth"));
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, year);
			calendar.set(Calendar.MONTH, month);
			calendar.set(Calendar.DAY_OF_MONTH, 1);

			Boolean exists = dateExists(conn, bu, calendar);

			if (exists) {
				period.setDate(BSDateTimeUtil.calendar2Date(calendar));
				throw new BSUserException("El paríodo " + ps.periodAsString(period) + " ya existe");
			}

			period.setDate(BSDateTimeUtil.calendar2Date(calendar));
			period.setPeriodStatus(1L);
		} else {
			id = (Long) Long.parseLong(idString);
			period.setId(id);
			bu.search(conn, period);
		}

		Double limitIPS = Double.parseDouble(request.getParameter("cLimitIPS"));
		Double utm = Double.parseDouble(request.getParameter("cUTM"));
		Double limitHealth = Double.parseDouble(request.getParameter("cLimitHealth"));
		Double limitInsurance = Double.parseDouble(request.getParameter("cLimitInsurance"));
		Double gratificationFactor = Double.parseDouble(request.getParameter("cGratificationFactor"));
		Double limitGratification = Double.parseDouble(request.getParameter("cLimitGratification"));
		Double minSalary = Double.parseDouble(request.getParameter("cMinSalary"));
		Double uf = Double.parseDouble(request.getParameter("cUF"));
		Double overtimeFactor = Double.parseDouble(request.getParameter("cOvertimeFactor"));
		Integer daysForYear = Integer.parseInt(request.getParameter("cDaysForYear"));

		period.setDaysForYear(daysForYear);
		period.setGratificationFactor(gratificationFactor);
		period.setLimitGratification(limitGratification);
		period.setLimitHealth(limitHealth);
		period.setLimitInsurance(limitInsurance);
		period.setLimitIPS(limitIPS);
		period.setMinSalary(minSalary);
		period.setOvertimeFactor(overtimeFactor);
		period.setUf(uf);
		period.setUtm(utm);

		bu.save(conn, period);

		mysql.closeConnection(conn);
		request.getRequestDispatcher("/servlet/admin/period/PeriodManager").forward(request, response);
	}

	private Boolean dateExists(Connection conn, BSBeanUtils bu, Calendar calendar) {
		Integer size = bu.list(conn, new Period(), "DATE(cDate)=DATE(?)", BSDateTimeUtil.calendar2Date(calendar)).size();
		return size > 0;
	}
}
