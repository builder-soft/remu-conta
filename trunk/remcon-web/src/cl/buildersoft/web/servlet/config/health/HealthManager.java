package cl.buildersoft.web.servlet.config.health;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.web.servlet.common.BSHttpServlet;

@WebServlet("/servlet/config/health/HealthManager")
public class HealthManager extends BSHttpServlet {
	private static final long serialVersionUID = 4686868722586369145L;

	@Override
	protected BSTableConfig getBSTableConfig(HttpServletRequest request) {
		BSTableConfig table = super.initTable(request, "tHealth");

		table.setTitle("Instituciones de salud");

		table.getField("cKey").setLabel("Llave de integración");
		table.getField("cName").setLabel("Nombre");
		table.getField("cFactor").setLabel("Factor descuento");

		return table;
	}
}