package cl.buildersoft.web.servlet.conta.chartAccount;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.web.servlet.common.HttpServletCRUD;

/**
 * Servlet implementation class ChartAccountManager
 */
@WebServlet("/servlet/conta/chartAccount/ChartAccountManager")
public class ChartAccountManager extends HttpServletCRUD {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChartAccountManager() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected BSTableConfig getBSTableConfig(HttpServletRequest request) {
		BSTableConfig chartAccount = initTable(request, "tChartAccount"); 
		chartAccount.setTitle("Plan de Cuentas");
		return chartAccount;
	}

}
