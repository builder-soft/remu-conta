package cl.buildersoft.web.servlet.config.health;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import cl.buildersoft.framework.beans.BSField;
import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.framework.beans.Domain;
import cl.buildersoft.web.servlet.BSHttpServlet;

@WebServlet("/servlet/config/health/HealthManager")
public class HealthManager extends BSHttpServlet {
	private static final long serialVersionUID = 4686868722586369145L;
	@Override
	protected BSTableConfig getBSTableConfig(HttpServletRequest request) {
		Domain domain = (Domain) request.getSession().getAttribute("Domain");
		BSTableConfig table = new BSTableConfig(domain.getAlias(), "tBoard",
				"vHealth");
		
		BSField field = null;
		field = new BSField("cId", "Identificador");
		
		
		return table;
	}
}