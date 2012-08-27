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

@WebServlet("/servlet/remuneration/events/overtime/UpdateOvertime")
public class UpdateOvertime extends HttpServlet {
	private static final long serialVersionUID = -8225234569078888328L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);

		Long employeeId = Long.parseLong(request.getParameter("cId"));
		Long periodId = Long.parseLong(request.getParameter("cPeriod"));
		Long overtimeId = Long.parseLong(request.getParameter("cOvertime"));

		String day = request.getParameter("cDay");
		Integer percent = Integer.parseInt(request.getParameter("cPercent"));
		Integer amount = Integer.parseInt(request.getParameter("cAmount"));

		Date date = getDate(conn, day, periodId);

		BSBeanUtils bu = new BSBeanUtils();
		Overtime overtime = new Overtime();
		overtime.setId(overtimeId);
		bu.search(conn, overtime);
		
		overtime.setAmount(amount);
		overtime.setDate(date);
		overtime.setPercent(percent);

		bu.update(conn, overtime);

		request.setAttribute("cId", employeeId);
		request.getRequestDispatcher("/servlet/remuneration/events/overtime/OvertimeMain").forward(request, response);

	}

	private Date getDate(Connection conn, String day, Long periodId) {
		AddOvertime addOvertimeServlet = new AddOvertime();
		return addOvertimeServlet.getDate(conn, day, periodId);
	}

}
