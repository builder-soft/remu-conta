package cl.buildersoft.web.servlet.admin.user;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.framework.database.BSmySQL;

/**
 * Servlet implementation class UserSave
 */
@WebServlet("/servlet/admin/user/UserSave")
public class UserSave extends HttpServlet {
	private static final long serialVersionUID = 2626535852650186256L;

	public UserSave() {
		super();
	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request.getServletContext(),
				"bsframework");

		List<Object> prms = new ArrayList<Object>();

		String mail = request.getParameter("cMail");
		String name = request.getParameter("cName");
		Boolean admin = request.getParameter("cAdmin") != null;

		prms.add(-1);
		prms.add(mail);
		prms.add(name);
		prms.add(null);
		prms.add(admin);

		mysql.callSingleSP(conn, "setUserSave", prms);
		
		request.getRequestDispatcher("/servlet/Home")
		.forward(request, response);
		
	}

}
