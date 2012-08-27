package cl.buildersoft.web.servlet.remuneration.events.holiday;

import java.io.IOException;
import java.sql.Connection;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.util.BSDateTimeUtil;
import cl.buildersoft.framework.util.BSUtils;
import cl.buildersoft.framework.util.BSWeb;

@WebServlet("/servlet/remuneration/events/holiday/ValidateHolidayParameters")
public class ValidateHolidayParameters extends HttpServlet {
	private static final long serialVersionUID = -122693918942394203L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String from = request.getParameter("cFrom");
		String normal = request.getParameter("cNormal");
		String creeping = request.getParameter("cCreeping");

		Integer days = Integer.parseInt(normal) + Integer.parseInt(creeping);

		String formatDate = BSDateTimeUtil.getFormatDate(request);

		if (!BSDateTimeUtil.isValidDate(from, formatDate)) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().write("Fecha no es valida");

		} else {
			BSmySQL mysql = new BSmySQL();
			Connection conn = mysql.getConnection(request);
			
			Calendar date = BSDateTimeUtil.string2Calendar(from, formatDate);
			String newDate = mysql.callFunction(conn, "fBusinessDate", BSUtils.array2List(date, days));
			date = BSDateTimeUtil.string2Calendar(newDate, "yyyy-MM-dd");
			response.getWriter().print(BSDateTimeUtil.calendar2String(date, formatDate));

		}
		// Calendar fromAsDate = BSUtils.string2Calendar(from, formatDate);
		response.flushBuffer();
	}

}
