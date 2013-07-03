package cl.buildersoft.web.servlet.config.enterprise.costCenter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import cl.buildersoft.framework.util.crud.BSTableConfig;
import cl.buildersoft.web.servlet.common.crud.HttpServletCRUD;

@WebServlet("/servlet/config/enterprise/costCenter/CostCenterManager")
public class CostCenterManager extends HttpServletCRUD {
	private static final long serialVersionUID = -477291616717858668L;

	@Override
	protected BSTableConfig getBSTableConfig(HttpServletRequest request) {
		BSTableConfig table = initTable(request, "tCostCenter");

		table.setTitle("Centros de Costo");

		table.getField("cName").setLabel("Nombre");
		table.getField("cBranch").setLabel("Sucursal");

		return table;
	}

}
