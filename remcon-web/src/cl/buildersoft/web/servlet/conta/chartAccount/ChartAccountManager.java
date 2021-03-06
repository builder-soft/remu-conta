package cl.buildersoft.web.servlet.conta.chartAccount;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import cl.buildersoft.framework.util.crud.BSTableConfig;
import cl.buildersoft.web.servlet.common.crud.HttpServletCRUD;

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
	}

	@Override
	protected BSTableConfig getBSTableConfig(HttpServletRequest request) {
		BSTableConfig chartAccount = initTable(request, "tChartAccount");
		chartAccount.setTitle("Plan de Cuentas");
		return chartAccount;
	}

}
