package cl.buildersoft.web.servlet.remuneration.process.salary;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.business.beans.Period;
import cl.buildersoft.framework.database.BSBeanUtils;
import cl.buildersoft.framework.database.BSmySQL;

@WebServlet("/servlet/remuneration/process/salary/ProcessSalary")
public class ProcessSalary extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 8964108846441089172L;

	public ProcessSalary() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);
		BSBeanUtils bu = new BSBeanUtils();

		Long periodId = Long.parseLong(mysql.callFunction(conn, "fGetOpenedPeriod", null));

		Period period = new Period();
		period.setId(periodId);
		bu.search(conn, period);

		request.setAttribute("Period", period);

		request.getRequestDispatcher("/WEB-INF/jsp/remuneration/process/salary/process-salary.jsp").forward(request, response);
	}
}
