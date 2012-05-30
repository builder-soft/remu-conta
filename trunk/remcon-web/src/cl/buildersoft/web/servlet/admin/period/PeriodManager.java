package cl.buildersoft.web.servlet.admin.period;

import javax.servlet.Servlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.web.servlet.BSHttpServlet;

@WebServlet("/servlet/admin/period/PeriodManager")
public class PeriodManager extends BSHttpServlet implements Servlet {
	private static final long serialVersionUID = -5347446304866453415L;

	@Override
	protected BSTableConfig getBSTableConfig(HttpServletRequest request) {
		BSTableConfig table = super.initTable(request, "tPeriod");
		
		table.removeAction("DELETE");
		
		return table;
	}
       

}
