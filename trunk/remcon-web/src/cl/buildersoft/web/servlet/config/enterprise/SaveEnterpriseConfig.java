package cl.buildersoft.web.servlet.config.enterprise;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.business.beans.EnterpriseConfig;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.util.BSBeanUtilsSP;
import cl.buildersoft.web.servlet.common.AbstractServletUtil;

@WebServlet("/servlet/config/employee/SaveEnterpriseConfig")
public class SaveEnterpriseConfig extends AbstractServletUtil {
	private static final long serialVersionUID = 5316369008384063620L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Long enterpriseId = Long.parseLong(request.getParameter("cId"));

		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);
		BSBeanUtilsSP bu = new BSBeanUtilsSP();

		EnterpriseConfig enterpriseConfig = new EnterpriseConfig();
		enterpriseConfig.setId(enterpriseId);
		enterpriseConfig.setEnterprise(Long.valueOf(request.getParameter("enterprise")));
		enterpriseConfig.setShowDateUfUtm(validateCheck(request.getParameter("showDateUfUtm")));
		enterpriseConfig.setShowProfile(validateCheck(request.getParameter("showProfile")));
		enterpriseConfig.setShowCostCenter(validateCheck(request.getParameter("showCostCenter")));
		enterpriseConfig.setShowDataAgreement(validateCheck(request.getParameter("showDataAgreement")));
		enterpriseConfig.setShowSalaryRoot(validateCheck(request.getParameter("showSalary")));
		enterpriseConfig.setShowEmployerBonus(validateCheck(request.getParameter("showEmployerBonus")));
		enterpriseConfig.setShowWorkDay(validateCheck(request.getParameter("showWorkDay")));
		enterpriseConfig.setShowNetPaymentScope(validateCheck(request.getParameter("showNetPaymentScope")));
		enterpriseConfig.setTextFootSalary(request.getParameter("textFootSalary"));
		enterpriseConfig.setMailNotice(request.getParameter("email"));
		enterpriseConfig.setViewLastSettlements(Integer.parseInt(request.getParameter("cViewLastSettlements")));

		bu.update(conn, enterpriseConfig);
		request.getRequestDispatcher("/servlet/config/enterprise/EnterpriseManager").forward(request, response);
	}

	private boolean validateCheck(String check) {
		if (check == null)
			return false;
		return check.equalsIgnoreCase("on") ? true : false;
	}
	/**
	 * <code>
	private Account2 getAccountByEmployee(Connection conn, BSBeanUtilsSP bu,
			Long idEmployee) {
		return null;
	}
</code>
	 */
}
