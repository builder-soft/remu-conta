package cl.buildersoft.web.servlet.remuneration.process.previred;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.business.beans.Period;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.util.BSBeanUtilsSP;

@WebServlet("/servlet/remuneration/process/previred/Previred")
public class Previred extends HttpServlet {
	private static final long serialVersionUID = -3216596595663493054L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);
		BSBeanUtilsSP bu = new BSBeanUtilsSP();

		List<Period> periods = (List<Period>) bu.list(conn, new Period(), "pListPeriod");

		// List<Period> periods = mysql.callSingleSP(conn, , null);

		// Period period = new Period();
		// period.setId(periodId);
		// bu.search(conn, period);

		request.setAttribute("Periods", periods);
		mysql.closeConnection(conn);

		request.getRequestDispatcher("/WEB-INF/jsp/remuneration/process/previred/process-previred.jsp")
				.forward(request, response);
	}
}
