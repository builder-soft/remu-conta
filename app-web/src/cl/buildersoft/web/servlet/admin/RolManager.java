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
 * Servlet implementation class RolManager
 */
@WebServlet("/admin/RolManager")
public class RolManager extends BSHttpServlet {
	private static final long serialVersionUID = 1L;

	public RolManager() {
		super();

	}
/**<code>
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		BSTableConfig table = new BSTableConfig("tRol");
		table.setTitle("Mantenimeito de Roles");
		
		BSField field = new BSField("cId", "Id");
		table.addField(field);
		
		field = new BSField("cName", "Nombre");
		table.addField(field);

		HttpSession session = request.getSession();
		synchronized (session) {
			session.setAttribute("BSTable", table);
		}

		request.getRequestDispatcher("/admin/LoadTable").forward(request,
				response);
	}
</code>*/

	@Override
	protected BSTableConfig getBSTableConfig() {
		BSTableConfig table = new BSTableConfig("tRol");
		table.setTitle("Mantenimeito de Roles");
		
		BSField field = new BSField("cId", "Id");
		table.addField(field);
		
		field = new BSField("cName", "Nombre");
		table.addField(field);
		
		return table;
	}
}
