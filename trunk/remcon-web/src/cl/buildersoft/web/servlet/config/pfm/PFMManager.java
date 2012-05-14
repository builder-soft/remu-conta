package cl.buildersoft.web.servlet.config.pfm;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import cl.buildersoft.framework.beans.BSField;
import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.framework.beans.Domain;
import cl.buildersoft.web.servlet.BSHttpServlet;

@WebServlet("/servlet/config/pfm/PFMManager")
public class PFMManager extends BSHttpServlet {
	private static final long serialVersionUID = -913523455848692014L;

	@Override
	protected BSTableConfig getBSTableConfig(HttpServletRequest request) {
		Domain domain = (Domain) request.getSession().getAttribute("Domain");
		BSTableConfig table = new BSTableConfig(domain.getAlias(), "tBoard",
				"vPFM");
		table.setTitle("Listado de AFP");
		table.setSaveSP("pSavePFM");
		table.setDeleteSP("pDelPFM");

		BSField field = null;
		field = new BSField("cId", "Identificador");
		field.setPK(Boolean.TRUE);
		table.addField(field);

		field = new BSField("cKey", "Código");
		table.addField(field);

		field = new BSField("cName", "Nombre");
		table.addField(field);

		field = new BSField("cEnable", "Habilitado");
		table.addField(field);

		field = new BSField("cFactor", "Factor");
		table.addField(field);

		field = new BSField("cSIS", "SIS");
		table.addField(field);
		

		return table;
	}
}