package cl.buildersoft.web.servlet.config.enterprise.banch;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.web.servlet.common.HttpServletCRUD;

@WebServlet("/servlet/config/enterprise/branch/BranchManager")
public class BranchManager extends HttpServletCRUD {
	private static final long serialVersionUID = -2048068668878392177L;

 
	@Override
	protected BSTableConfig getBSTableConfig(HttpServletRequest request) {
		BSTableConfig table = initTable(request, "tBranch");

		table.setTitle("Sucursales");
		
		table.getField("cName").setLabel("Nombre");
		table.getField("cEnterprise").setLabel("Empresa");
		table.getField("cAddress").setLabel("Dirección");
		table.getField("cPhone").setLabel("Teléfono");
		
		return table;
	}

}
