package cl.buildersoft.web.servlet.remu;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.web.servlet.BSHttpServlet;

/**
 * Servlet implementation class EmployeeManager
 */
@WebServlet("/servlet/remu/EnterpriseManager")
public class EnterpriseManager extends BSHttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected BSTableConfig getBSTableConfig(HttpServletRequest request) {
		BSTableConfig table = initTable(request, "tEnterprise");

		table.setTitle("Empresas");

		table.getField("cName").setLabel("Nombre/Razón Social");
		table.getField("cLegalRep").setLabel("Representante Legal");
		table.getField("cCategory").setLabel("Giro");
		table.getField("cAddress").setLabel("Dirección");
		table.getField("cPhone").setLabel("Teléfono");
		table.getField("cRutLegalRep").setLabel("Rut representante Legal");
		table.getField("cMutualFactor").setLabel("Factor Mutual");
		table.getField("cCompensationFund").setLabel("Caja de compensación");

		hideFields(table, "cRutLegalRep", "cMutual", "cMutualFactor",
				"cCompensationFund");
		return table;
	}
}
