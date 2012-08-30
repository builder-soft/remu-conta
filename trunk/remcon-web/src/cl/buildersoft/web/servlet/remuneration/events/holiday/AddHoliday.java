package cl.buildersoft.web.servlet.remuneration.events.holiday;

import java.io.IOException;
import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.business.service.HolidayService;
import cl.buildersoft.business.service.impl.HolidayServiceImpl;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.util.BSDateTimeUtil;

@WebServlet("/servlet/remuneration/events/holiday/AddHoliday")
public class AddHoliday extends HttpServlet {
	private static final long serialVersionUID = 2664508557443431729L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long employeeId = getEmployeeId(request);
		Date from = BSDateTimeUtil.calendar2Date(getFrom(request));
		Integer normal = getNormal(request);
		Integer creeping = getCreeping(request);

		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);

		HolidayService hs = new HolidayServiceImpl();
		hs.commitHoliday(conn, employeeId, from, normal, creeping);
		mysql.closeConnection(conn);

		request.setAttribute("cId", employeeId);
		request.getRequestDispatcher("/servlet/remuneration/events/holiday/HolidayMain").forward(request, response);

	}

	private Integer getCreeping(HttpServletRequest request) {
		return getParameterAsInteger(request, "cCreeping");
	}

	private Integer getNormal(HttpServletRequest request) {
		return getParameterAsInteger(request, "cNormal");
	}

	private Integer getParameterAsInteger(HttpServletRequest request, String name) {
		String parameter = request.getParameter(name);
		return Integer.parseInt(parameter);
	}

	private Calendar getFrom(HttpServletRequest request) {
		String date = request.getParameter("cFrom");
		return BSDateTimeUtil.string2Calendar(request, date);
	}

	private Long getEmployeeId(HttpServletRequest request) {
		String id = request.getParameter("cId");
		return Long.parseLong(id);
	}
}
