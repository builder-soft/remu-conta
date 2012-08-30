package cl.buildersoft.web.servlet.remuneration.process.salary;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.framework.database.BSmySQL;

@WebServlet("/servlet/remuneration/process/salary/CalculateSalary")
public class CalculateSalary extends HttpServlet {
	private static final long serialVersionUID = 6118990651043646111L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);

		mysql.callSingleSP(conn, "pCalculateAllSalary", null);
		mysql.closeSQL();
		mysql.closeConnection(conn);

		request.getRequestDispatcher("/servlet/Home").forward(request, response);
	}
}
