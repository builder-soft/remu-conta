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

import cl.buildersoft.business.beans.APV;
import cl.buildersoft.business.beans.Agreement;
import cl.buildersoft.business.beans.Currency;
import cl.buildersoft.business.beans.Employee;
import cl.buildersoft.business.beans.ExBoxSystem;
import cl.buildersoft.business.beans.Health;
import cl.buildersoft.business.beans.PFM;
import cl.buildersoft.business.beans.RagreementAPV;
import cl.buildersoft.business.service.AgreementService;
import cl.buildersoft.business.service.impl.AgreementServiceImpl;
import cl.buildersoft.framework.database.BSBeanUtils;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.exception.BSDataBaseException;
import cl.buildersoft.framework.util.BSBeanUtilsSP;
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
		BSBeanUtils bu = new BSBeanUtils();
		cl.buildersoft.business.beans.EnterpriseConfig enterpriseCong =  new cl.buildersoft.business.beans.EnterpriseConfig();
		enterpriseCong.setId(enterpriseId);
		bu.search(conn, enterpriseCong);
		request.setAttribute("enterpriseConfig", enterpriseCong);

		request.setAttribute("Action", "Update");
		request.getRequestDispatcher(
				"/WEB-INF/jsp/table/enterprise-config.jsp").forward(
				request, response);
	}
	
	protected void update(HttpServletRequest request, HttpServletResponse response) {
		
	}
}
