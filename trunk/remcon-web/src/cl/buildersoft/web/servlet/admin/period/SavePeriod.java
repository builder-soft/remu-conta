package cl.buildersoft.web.servlet.admin.period;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.business.beans.Period;
import cl.buildersoft.framework.database.BSBeanUtils;
import cl.buildersoft.framework.database.BSmySQL;

@WebServlet("/servlet/admin/period/SavePeriod")
public class SavePeriod extends HttpServlet {
	private static final long serialVersionUID = 1776719588359492973L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = (Long) Long.parseLong(request.getParameter("cId"));
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

		/**
		 * <code>
			cId
			cLimitIPS
			cUTM
			cLimitHealth
			cLimitInsurance
			cGratificationFactor
			cLimitGratification
			cMinSalary
			cUF
			cOvertimeFactor 
			cDaysForYear
			</code>
		 */

		Period period = new Period();
		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);
		BSBeanUtils bu = new BSBeanUtils();

		period.setId(id);
		bu.search(conn, period);
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
}
