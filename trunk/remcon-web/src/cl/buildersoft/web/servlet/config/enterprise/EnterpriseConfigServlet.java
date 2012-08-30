package cl.buildersoft.web.servlet.config.enterprise;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.business.beans.EnterpriseConfig;
import cl.buildersoft.framework.database.BSBeanUtils;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.web.servlet.common.AbstractServletUtil;

/**
 * Servlet implementation class EditRecord
 */
@WebServlet("/servlet/config/enterprise/EnterpriseConfigServlet")
public class EnterpriseConfigServlet extends AbstractServletUtil {

	private static final long serialVersionUID = -5785656616097922095L;

	public EnterpriseConfigServlet() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long enterpriseId = Long.parseLong(request.getParameter("cId"));

		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);
		BSBeanUtils bu = new BSBeanUtils();
		
		EnterpriseConfig enterpriseConfig = new EnterpriseConfig();
		enterpriseConfig.setId(enterpriseId);
		bu.search(conn, enterpriseConfig);
		mysql.closeConnection(conn);
		request.setAttribute("EnterpriseConfig", enterpriseConfig);

		request.getRequestDispatcher("/WEB-INF/jsp/config/enterprise/enterprise-config.jsp").forward(request, response);
	}

}
