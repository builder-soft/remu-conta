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

import cl.buildersoft.business.beans.Holiday;
import cl.buildersoft.framework.database.BSBeanUtils;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.util.BSDateTimeUtil;

@WebServlet("/servlet/remuneration/events/holiday/AddHoliday")
public class AddHoliday extends HttpServlet {
	private static final long serialVersionUID = 2664508557443431729L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long employeeId = getEmployeeId(request);
		Date from = BSDateTimeUtil.calendar2Date(getFromCalendar(request));
		Integer normal = getNormal(request);
		Integer creeping = getCreeping(request);

		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);

		Holiday holiday = new Holiday();
		holiday.setCreation(new Date());
		holiday.setEmployee(employeeId);
		holiday.setFrom(from);
		holiday.setNormal(normal);
		holiday.setCreeping(creeping);

		BSBeanUtils bu = new BSBeanUtils();
		bu.save(conn, holiday);

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

	private Calendar getFromCalendar(HttpServletRequest request) {
		String date = request.getParameter("cFrom");
		return BSDateTimeUtil.string2Calendar(request, date);
	}

	private Long getEmployeeId(HttpServletRequest request) {
		String id = request.getParameter("cId");

		return Long.parseLong(id);
	}

}
