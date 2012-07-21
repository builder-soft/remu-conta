package cl.buildersoft.web.servlet.remuneration.events.overtime;

import java.io.IOException;
import java.sql.Connection;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.business.beans.Overtime;
import cl.buildersoft.framework.database.BSBeanUtils;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.type.BSFieldDataType;
import cl.buildersoft.framework.type.BSFieldType;
import cl.buildersoft.framework.type.BSTypeFactory;

@WebServlet("/servlet/remuneration/events/overtime/AddOvertime")
public class AddOvertime extends HttpServlet {
	private static final long serialVersionUID = -2050274960694477685L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);

		Long employeeId = Long.parseLong(request.getParameter("cId"));
		Long periodId = Long.parseLong(request.getParameter("cPeriod"));

		String dateString = request.getParameter("cDate");
		Integer percent = Integer.parseInt(request.getParameter("cPercent"));
		Integer amount = Integer.parseInt(request.getParameter("cAmount"));

		BSFieldDataType dataType = BSTypeFactory.create(BSFieldType.Date);
		Date date = (Date) dataType.parse(conn, dateString);

		Overtime overtime = new Overtime();
		overtime.setAmount(amount);
		overtime.setDate(date);
		overtime.setEmployee(employeeId);
		overtime.setPercent(percent);
		overtime.setPeriod(periodId);

		BSBeanUtils bu = new BSBeanUtils();
		bu.save(conn, overtime);

		request.setAttribute("cId", employeeId);
		request.getRequestDispatcher("/servlet/remuneration/events/overtime/OvertimeEmployee").forward(request, response);

	}
}
