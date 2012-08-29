package cl.buildersoft.web.servlet.config.pfm;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.web.servlet.common.BSHttpServlet;

@WebServlet("/servlet/config/pfm/PFMManager")
public class PFMManager extends BSHttpServlet {
	private static final long serialVersionUID = -913523455848692014L;

	@Override
	protected BSTableConfig getBSTableConfig(HttpServletRequest request) {
		BSTableConfig table = super.initTable(request, "tPFM");

		table.setTitle("Administradores de Fondos de Pensión");

		 table.getField("cKey").setLabel("Llave de integración");
		 table.getField("cName").setLabel("Nombre");
		 table.getField("cFactor").setLabel("Factor descuento");
		 table.getField("cSIS").setLabel("Seguro de Invalidez y Sobrevivencia");

		return table;
	}
}