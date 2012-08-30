package cl.buildersoft.web.servlet.remuneration.events.overtime;

import java.io.IOException;
import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.business.beans.Overtime;
import cl.buildersoft.business.beans.Period;
import cl.buildersoft.framework.database.BSBeanUtils;
import cl.buildersoft.framework.database.BSmySQL;

@WebServlet("/servlet/remuneration/events/overtime/AddOvertime")
public class AddOvertime extends HttpServlet {
	private static final long serialVersionUID = -2050274960694477685L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);

		Long employeeId = Long.parseLong(request.getParameter("cId"));
		Long periodId = Long.parseLong(request.getParameter("cPeriod"));

		String day = request.getParameter("cDay");
		Integer percent = Integer.parseInt(request.getParameter("cPercent"));
		Integer amount = Integer.parseInt(request.getParameter("cAmount"));

		Date date = getDate(conn, day, periodId);

		Overtime overtime = new Overtime();
		overtime.setAmount(amount);
		overtime.setDate(date);
		overtime.setEmployee(employeeId);
		overtime.setPercent(percent);
		overtime.setPeriod(periodId);

		BSBeanUtils bu = new BSBeanUtils();
		bu.save(conn, overtime);
		mysql.closeConnection(conn);

		request.setAttribute("cId", employeeId);
		request.getRequestDispatcher("/servlet/remuneration/events/overtime/OvertimeMain").forward(request, response);

	}

	public Date getDate(Connection conn, String day, Long periodId) {
		BSBeanUtils bu = new BSBeanUtils();
		Period period = new Period();
		period.setId(periodId);
		bu.search(conn, period);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(period.getDate());
		calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));

		Date date = calendar.getTime();

		return date;
	}
}
