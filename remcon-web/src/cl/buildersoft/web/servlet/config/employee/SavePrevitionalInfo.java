package cl.buildersoft.web.servlet.config.employee;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.business.beans.Agreement;
import cl.buildersoft.business.beans.RagreementAPV;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.util.BSBeanUtilsSP;
import cl.buildersoft.web.servlet.common.AbstractServletUtil;

@WebServlet("/servlet/config/employee/SavePrevitionalInfo")
public class SavePrevitionalInfo extends AbstractServletUtil {
	private static final long serialVersionUID = 5316369008384063620L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Long employeeId = Long.parseLong(request.getParameter("cId"));

		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);
		BSBeanUtilsSP bu = new BSBeanUtilsSP();

		// Board apvType = (Board)bu.get(conn, new Board(),
		// "pGetBoardByTypeAndKey", array2List("INSTITUTION", "APV"));

		Agreement agreement = getAgreement(conn, bu, employeeId);
		mysql.callSingleSP(conn, "pDelAgreementAPVByEmployee", employeeId);

		String[] apvInstitution = (String[]) request.getParameterValues("apvInstitution");
		String[] apvCurrency = (String[]) request.getParameterValues("apvCurrency");
		String[] apvAmount = (String[]) request.getParameterValues("apvAmount");

		for (int i = 0; apvInstitution != null && i < apvInstitution.length; i++) {
			RagreementAPV agreementAPV = new RagreementAPV();

			agreementAPV.setAgreement(agreement.getId());
			agreementAPV.setAmount(new Double(apvAmount[i]));
			agreementAPV.setApv(new Long(apvInstitution[i]));
			agreementAPV.setCurrency(new Long(apvCurrency[i]));

			mysql.callSingleSP(
					conn,
					"pSaveAPVForAgreement",
					array2List(agreementAPV.getAgreement(), agreementAPV.getApv(), agreementAPV.getCurrency(),
							agreementAPV.getAmount()));

		}

		Long exBox = Long.valueOf(request.getParameter("exBox"));
		Integer disabilityBurdens = Integer.valueOf(request.getParameter("disabilityBurdens"));
		Integer maternalLoad = Integer.valueOf(request.getParameter("maternalLoad"));
		Long afpEmp = Long.valueOf(request.getParameter("afpEmp"));
		Integer monthsQuoted = Integer.parseInt(request.getParameter("MonthsQuoted"));
		Long healthCurrency = Long.valueOf(request.getParameter("HealthCurrency"));

		Long health = Long.valueOf(request.getParameter("health"));
		Double healthAmount = Double.valueOf(request.getParameter("HealthAmount"));
		Integer simpleLoads = Integer.valueOf(request.getParameter("simpleLoad"));
		Boolean pensionary = Boolean.parseBoolean(request.getParameter("Pensionary"));
		Long familyAssignmentStretch = Long.parseLong(request.getParameter("FamilyAssignmentStretch"));

		Long currencyAccount2 = Long.parseLong(request.getParameter("CurrencyAccount2"));
		Double amountAccount2 = Double.parseDouble(request.getParameter("AmountAccount2"));

		Long additionalPFMCurrency = Long.parseLong(request.getParameter("AdditionalPFMCurrency"));
		Double additionalPFMAmount = Double.parseDouble(request.getParameter("AdditionalPFMAmount"));

		agreement.setExBoxSystem(exBox);
		agreement.setDisabilityBurdens(disabilityBurdens);
		agreement.setMaternalLoads(maternalLoad);
		agreement.setPfm(afpEmp);
		agreement.setMonthsQuoted(monthsQuoted);
		agreement.setHealthCurrency(healthCurrency);
		agreement.setHealth(health);
		agreement.setHealthAmount(healthAmount);
		agreement.setSimpleLoads(simpleLoads);
		agreement.setPensionary(pensionary);
		agreement.setFamilyAssignmentStretch(familyAssignmentStretch);
		agreement.setCurrencyAccount2(currencyAccount2);
		agreement.setAmountAccount2(amountAccount2);
		agreement.setAdditionalPFMAmount(additionalPFMAmount);
		agreement.setAdditionalPFMCurrency(additionalPFMCurrency);

		bu.save(conn, agreement);
		mysql.closeConnection(conn);
		request.getRequestDispatcher("/servlet/config/employee/EmployeeManager").forward(request, response);
	}

	private Agreement getAgreement(Connection conn, BSBeanUtilsSP bu, Long idEmployee) {
		ContractualInfo ci = new ContractualInfo();
		return ci.getAgreement(conn, bu, idEmployee);
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
