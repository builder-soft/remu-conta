package cl.buildersoft.web.servlet.config.enterprise.profile;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.web.servlet.common.crud.HttpServletCRUD;

/**
 * Servlet implementation class ProfileManager
 */
@WebServlet("/servlet/config/enterprise/profile/ProfileManager")
public class ProfileManager extends HttpServletCRUD {
	private static final long serialVersionUID = 6277545710620940766L;

	@Override
	protected BSTableConfig getBSTableConfig(HttpServletRequest request) {
		BSTableConfig table = initTable(request, "tProfile");
table.setTitle("Perfiles");
		
table.getField("cName").setLabel("Nombre");
table.getField("cCostCenter").setLabel("Centro de Costo");
table.getField("cCompanyCost").setLabel("Costo Empresa");
return table;
	}
	 
   

}
