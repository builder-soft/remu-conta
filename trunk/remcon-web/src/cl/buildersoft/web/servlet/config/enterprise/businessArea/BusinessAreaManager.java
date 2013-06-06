package cl.buildersoft.web.servlet.config.enterprise.businessArea;

import javax.servlet.Servlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.web.servlet.common.HttpServletCRUD;

@WebServlet("/servlet/config/enterprise/businessArea/BusinessAreaManager")
public class BusinessAreaManager extends HttpServletCRUD implements Servlet {
	private static final long serialVersionUID = -204376946656709142L;

	@Override
	protected BSTableConfig getBSTableConfig(HttpServletRequest request) {
		BSTableConfig table = initTable(request, "tBusinessArea");
		table.setTitle("Areas de Negocio");
		return table;
	}

}
