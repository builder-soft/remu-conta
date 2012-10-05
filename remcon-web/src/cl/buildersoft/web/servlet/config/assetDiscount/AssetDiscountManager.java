package cl.buildersoft.web.servlet.config.assetDiscount;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.web.servlet.common.BSHttpServlet;

 
@WebServlet("/servlet/config/assetDiscount/AssetDiscountManager")
public class AssetDiscountManager extends BSHttpServlet {
	private static final long serialVersionUID = -7130205551180841782L;

	@Override
	protected BSTableConfig getBSTableConfig(HttpServletRequest request) {
		BSTableConfig table = super.initTable(request, "tAssetDiscount");
		
		table.removeAction("DELETE");
		table.removeAction("INSERT");
		table.setTitle("Tabla de haberes y descuentos");
		
		return table;
	}
	
}
