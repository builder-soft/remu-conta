package cl.buildersoft.web.servlet.remu;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.framework.beans.APV;
import cl.buildersoft.framework.beans.Agreement;
import cl.buildersoft.framework.beans.Currency;
import cl.buildersoft.framework.beans.Employee;
import cl.buildersoft.framework.beans.ExBoxSystem;
import cl.buildersoft.framework.beans.Health;
import cl.buildersoft.framework.beans.PFM;
import cl.buildersoft.framework.beans.RagreementAPV;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.exception.BSDataBaseException;
import cl.buildersoft.framework.util.BSBeanUtilsSP;
import cl.buildersoft.web.servlet.config.employee.AgreementService;
import cl.buildersoft.web.servlet.config.employee.AgreementServiceImpl;
import cl.buildersoft.web.servlet.table.AbstractServletUtil;

/**
 * Servlet implementation class EditRecord
 */
@WebServlet("/servlet/table/EnterpriseConfig")
public class EnterpriseConfig extends AbstractServletUtil {

	private static final long serialVersionUID = -5785656616097922095L;

	public EnterpriseConfig() {
		super();
	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Long enterpriseId = Long.parseLong(request.getParameter("cId"));

		Connection conn = null;

		BSmySQL mysql = new BSmySQL();
		conn = mysql.getConnection(request);
		BSBeanUtilsSP bu = new BSBeanUtilsSP();

		cl.buildersoft.framework.beans.EnterpriseConfig EnterpriseConfig = (cl.buildersoft.framework.beans.EnterpriseConfig) bu.get(conn, new cl.buildersoft.framework.beans.EnterpriseConfig(), "pGetEnterpriseConfig",enterpriseId);
		request.setAttribute("enterpriseConfig", EnterpriseConfig);

		request.setAttribute("Action", "Update");
		request.getRequestDispatcher(
				"/WEB-INF/jsp/table/enterprise-config.jsp").forward(
				request, response);
	}
	
	private Employee getEmployee(Connection conn, BSBeanUtilsSP bu, Long id) {
		Employee out = new Employee();
		out.setId(id);
		bu.search(conn, out);
		return out;
	}	
}
