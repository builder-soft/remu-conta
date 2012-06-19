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
import cl.buildersoft.web.servlet.table.AbstractServletUtil;

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
		Long additionalHealthCurrency = Long.valueOf(request.getParameter("additionalHealthCurrency"));

		Long health = Long.valueOf(request.getParameter("health"));
		Double additionalHealthAmount = Double.valueOf(request.getParameter("additionalHealthAmount"));
		Integer simpleLoads = Integer.valueOf(request.getParameter("simpleLoad"));
		Boolean pensionary = Boolean.parseBoolean(request.getParameter("Pensionary"));
		Long familyAssignmentStretch= Long.parseLong(request.getParameter("FamilyAssignmentStretch"));

		agreement.setExBoxSystem(exBox);
		agreement.setDisabilityBurdens(disabilityBurdens);
		agreement.setMaternalLoads(maternalLoad);
		agreement.setPfm(afpEmp);
		agreement.setAdditionalHealthCurrency(additionalHealthCurrency);
		agreement.setHealth(health);
		agreement.setAdditionalHealthAmount(additionalHealthAmount);
		agreement.setSimpleLoads(simpleLoads);
		agreement.setPensionary(pensionary);
		agreement.setFamilyAssignmentStretch(familyAssignmentStretch);

		bu.save(conn, agreement);

		request.getRequestDispatcher("/servlet/remu/EmployeeManager").forward(request, response);
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
