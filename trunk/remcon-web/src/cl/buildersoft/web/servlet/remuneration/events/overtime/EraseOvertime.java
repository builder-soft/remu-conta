package cl.buildersoft.web.servlet.remuneration.events.overtime;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.business.beans.Overtime;
import cl.buildersoft.framework.database.BSBeanUtils;
import cl.buildersoft.framework.database.BSmySQL;

@WebServlet("/servlet/remuneration/events/overtime/EraseOvertime")
public class EraseOvertime extends HttpServlet {
	private static final long serialVersionUID = 3064781182383615132L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);

		Long employeeId = Long.parseLong(request.getParameter("cId"));
		Long overtimeId = Long.parseLong(request.getParameter("cOvertime"));

		BSBeanUtils bu = new BSBeanUtils();
		Overtime overtime = new Overtime();
		overtime.setId(overtimeId);
		bu.delete(conn, overtime);
		mysql.closeConnection(conn);

		request.setAttribute("cId", employeeId);
		request.getRequestDispatcher("/servlet/remuneration/events/overtime/OvertimeMain").forward(request, response);

	}

}
