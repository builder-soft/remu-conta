package cl.buildersoft.web.servlet.config.enterprise;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.framework.database.BSBeanUtils;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.web.servlet.table.AbstractServletUtil;

/**
 * Servlet implementation class EditRecord
 */
@WebServlet("/servlet/config/enterprise/EnterpriseConfig")
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
				"/WEB-INF/jsp/config/enterprise/enterprise-config.jsp").forward(
				request, response);
	}
	
	protected void update(HttpServletRequest request, HttpServletResponse response) {
		
	}
}
