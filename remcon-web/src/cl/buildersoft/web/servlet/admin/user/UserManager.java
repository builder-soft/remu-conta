package cl.buildersoft.web.servlet.admin.user;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.buildersoft.framework.beans.User;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.util.BSConfig;

/**
 * Servlet implementation class UserManager
 */
@WebServlet("/servlet/admin/user/UserManager")
public class UserManager extends HttpServlet {
	private static final long serialVersionUID = -3497399350893131897L;

	public UserManager() {
		super();

	}

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request.getServletContext(),
				"bsframework");

		List<Object> prms = getParams(mysql, request);

		ResultSet rs = mysql.callSingleSP(conn, "getUserList", prms);

		request.setAttribute("Data", rs);

		request.getRequestDispatcher("/WEB-INF/jsp/admin/user/user-list.jsp")
				.forward(request, response);
	}

	/**
	 * <code>
	protected BSTableConfig getBSTableConfig(HttpServletRequest request) {
		BSTableConfigSP table = new BSTableConfigSP("bsframework",
				"getUserList", "");

		table.configFields(conn, mysql);

		table.setTitle("Mantenimiento de usuarios");

		BSField field = null; field = new BSField("cId", "ID");
		table.addField(field);
		 
		field = new BSField("cMail", "Correo electrónico/usuario");
		field.setValidationOnBlur("validationUser"); table.addField(field);
		  
		field = new BSField("cName", "Nombre"); table.addField(field);
		BSAction changePassword = new BSAction("CH_PASS", BSActionType.Record);
		changePassword.setLabel("Cambio de clave");
		changePassword.setUrl("/servlet/admin/SearchPassword");
		table.addAction(changePassword);

		BSAction rolRelation = new BSAction("ROL_RELATION", null);
		rolRelation.setNatTable("tR_UserRol", "tRol");
		rolRelation.setLabel("Roles de usuario");
		table.addAction(rolRelation);

		return table;
	}
	</code>
	 * 
	 * @param mysql
	 */

	private List<Object> getParams(BSmySQL mysql, HttpServletRequest request) {
		List<Object> out = new ArrayList<Object>();

		HttpSession session = request.getSession();
		User user = null;
		synchronized (session) {
			user = (User) session.getAttribute("User");
		}

		out.add(user.getId());
		out.add("%%");
		out.add(0);

		// BSConfig config = new BSConfig();
		out.add((new BSConfig()).getRecordsPerPage(mysql.getConnection(request)));

		return out;
	}
}
