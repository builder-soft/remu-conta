package cl.buildersoft.web.servlet.admin.period;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.business.beans.Period;
import cl.buildersoft.business.service.PeriodService;
import cl.buildersoft.business.service.impl.PeriodServiceImpl;
import cl.buildersoft.framework.database.BSmySQL;

@WebServlet("/servlet/admin/period/ReadPeriod")
public class ReadPeriod extends HttpServlet {
	private static final long serialVersionUID = -5718323555309174269L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("cId"));

		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);

		PeriodService ps = new PeriodServiceImpl();
		Period period = ps.getPeriod(conn, id);

		request.setAttribute("Period", period);
		request.setAttribute("PeriodName", ps.periodAsString(period));
		period.setPeriodStatus(1L);
		request.setAttribute("StatusName", ps.getStatusName(conn, period));
		
		mysql.closeConnection(conn);
		request.getRequestDispatcher("/WEB-INF/jsp/config/period/period-duplicate.jsp").forward(request, response);
	}

}
