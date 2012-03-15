package cl.buildersoft.web.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.buildersoft.framework.beans.BSField;
import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.web.servlet.BSHttpServlet;

/**
 * Servlet implementation class UserManager
 */
@WebServlet("/admin/UserManager")
public class UserManager extends BSHttpServlet {
	private static final long serialVersionUID = 1L;

	public UserManager() {
		super();

	}

	@Override
	protected BSTableConfig getBSTableConfig() {
		BSTableConfig table = new BSTableConfig("tUser");
		table.setTitle("Mantenimeito de usuarios");

		BSField field = null;
				
		field = new BSField("cMail", "Correo electrónico/usuario");
		table.addField(field);
		
		field = new BSField("cName", "Nombre");
		table.addField(field);

		field = new BSField("cId", "ID");
		field.setVisible(Boolean.FALSE);
		table.addField(field);

		table.setCanInsert(false);
		table.setCanEdit(false);
		table.setCanDelete(false);
		return table;
	}

}
