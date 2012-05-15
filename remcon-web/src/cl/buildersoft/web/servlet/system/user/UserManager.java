package cl.buildersoft.web.servlet.system.user;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cl.buildersoft.framework.beans.BSAction;
import cl.buildersoft.framework.beans.BSField;
import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.framework.beans.User;
import cl.buildersoft.framework.type.BSActionType;
import cl.buildersoft.web.servlet.BSHttpServlet;

/**
 * Servlet implementation class UserManager
 */
@WebServlet("/servlet/system/user/UserManager")
public class UserManager extends BSHttpServlet {
	private static final long serialVersionUID = -3497399350893131897L;

	@Override
	protected BSTableConfig getBSTableConfig(HttpServletRequest request) {
		HttpSession session = request.getSession();

		Boolean isAdmin = null;
		User user = null;
		BSTableConfig table = null;
		synchronized (session) {
			user = (User) session.getAttribute("User");
			isAdmin = user.getAdmin();
		}

		BSField field = null;
		if (isAdmin) {
			table = new BSTableConfig("bsframework", "tUser", "vUserAdmin");
			table.setSaveSP("bsframework.pSaveUserAdmin");
		} else {
			table = new BSTableConfig("bsframework", "tUser", "vUser");
		}
		table.setDeleteSP("bsframework.pDelUser");

		field = new BSField("cId", "ID");
		field.setPK(true);
		table.addField(field);

		field = new BSField("cMail", "Mail");
		table.addField(field);

		field = new BSField("cName", "Nombre");
		table.addField(field);

		if (isAdmin) {
			field = new BSField("cAdmin", "Administrador");
			table.addField(field);
			
			BSAction domainRelation = new BSAction("ROL_DOMAIN", null);
			domainRelation.setNatTable("bsframework", "tR_UserDomain", "bsframework", "tDomain");
			domainRelation.setLabel("Dominios del usuario");
			table.addAction(domainRelation);
		}

		BSAction changePassword = new BSAction("CH_PASS", BSActionType.Record);
		changePassword.setLabel("Cambio de clave");
		changePassword.setUrl("/servlet/system/changepassword/SearchPassword");
		table.addAction(changePassword);

		BSAction rolRelation = new BSAction("ROL_RELATION", null);
		rolRelation.setNatTable("remcon", "tR_UserRol", "remcon", "tRol");
		rolRelation.setLabel("Roles de usuario");
		table.addAction(rolRelation);

		return table;
	}

	/**
	 * <code>
	 * 
	 * @Override protected void service(HttpServletRequest request,
	 *           HttpServletResponse response) throws ServletException,
	 *           IOException {
	 * 
	 *           BSmySQL mysql = new BSmySQL(); Connection conn =
	 *           mysql.getConnection(request.getServletContext(),
	 *           "bsframework");
	 * 
	 *           List<Object> prms = getParams(mysql, request);
	 * 
	 *           ResultSet rs = mysql.callSingleSP(conn, "pGetUserList", prms);
	 * 
	 *           request.setAttribute("Data", rs);
	 * 
	 *           request.getRequestDispatcher(
	 *           "/WEB-INF/jsp/system/user/user-list.jsp") .forward(request,
	 *           response); } / * * <code> protected BSTableConfig
	 *           getBSTableConfig(HttpServletRequest request) { BSTableConfigSP
	 *           table = new BSTableConfigSP("bsframework", "getUserList", "");
	 * 
	 *           table.configFields(conn, mysql);
	 * 
	 *           table.setTitle("Mantenimiento de usuarios");
	 * 
	 *           BSField field = null; field = new BSField("cId", "ID");
	 *           table.addField(field);
	 * 
	 *           field = new BSField("cMail", "Correo electrónico/usuario");
	 *           field.setValidationOnBlur("validationUser");
	 *           table.addField(field);
	 * 
	 *           field = new BSField("cName", "Nombre"); table.addField(field);
	 *           BSAction changePassword = new BSAction("CH_PASS",
	 *           BSActionType.Record);
	 *           changePassword.setLabel("Cambio de clave");
	 *           changePassword.setUrl
	 *           ("/servlet/system/changepassword/SearchPassword");
	 *           table.addAction(changePassword);
	 * 
	 *           BSAction rolRelation = new BSAction("ROL_RELATION", null);
	 *           rolRelation.setNatTable("tR_UserRol", "tRol");
	 *           rolRelation.setLabel("Roles de usuario");
	 *           table.addAction(rolRelation);
	 * 
	 *           return table; } </code>
	 * 
	 * @param mysql
	 * 
	 *            private List<Object> getParams(BSmySQL mysql,
	 *            HttpServletRequest request) { List<Object> out = new
	 *            ArrayList<Object>();
	 * 
	 *            HttpSession session = request.getSession(); User user = null;
	 *            synchronized (session) { user = (User)
	 *            session.getAttribute("User"); }
	 * 
	 *            out.add(user.getId()); out.add("%%"); out.add(0);
	 * 
	 *            // BSConfig config = new BSConfig(); out.add((new
	 *            BSConfig()).getRecordsPerPage(mysql.getConnection(request)));
	 * 
	 *            return out; }
	 */

}
