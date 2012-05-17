package cl.buildersoft.web.filter.config.employee;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cl.buildersoft.framework.beans.BSTableConfig;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.web.servlet.config.employee.AccountService;
import cl.buildersoft.web.servlet.config.employee.AccountServiceImpl;
import cl.buildersoft.web.servlet.config.employee.AgreementService;
import cl.buildersoft.web.servlet.config.employee.AgreementServiceImpl;

@WebFilter(urlPatterns = { "/servlet/table/InsertRecord" }, dispatcherTypes = { DispatcherType.REQUEST })
public class SaveEmployeeFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		// System.out.println("EN EL FILTRO DE GRABADO");

		chain.doFilter(servletRequest, servletResponse);

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpSession session = request.getSession();

		BSTableConfig table = null;
		synchronized (session) {
			table = (BSTableConfig) session.getAttribute("BSTable");
		}

		String tableName = table.getTableName();

		if (tableName.equals("tEmployee")) {
			Long employeeId = (Long) request.getAttribute("cId");

			BSmySQL mysql = new BSmySQL();
			Connection conn = mysql.getConnection(request);

			AgreementService agreementService = new AgreementServiceImpl();
			agreementService.getDefaultAgreement(conn, employeeId);
			
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// System.out.println("INICIANDO EN EL FILTRO DE GRABADO");
	}

}
