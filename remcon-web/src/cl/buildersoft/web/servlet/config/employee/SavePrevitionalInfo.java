package cl.buildersoft.web.servlet.config.employee;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.framework.beans.Account2;
import cl.buildersoft.framework.beans.Agreement;
import cl.buildersoft.framework.beans.Board;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.type.BSFieldDataType;
import cl.buildersoft.framework.type.BSFieldType;
import cl.buildersoft.framework.type.BSTypeFactory;
import cl.buildersoft.framework.util.BSBeanUtilsSP;
import cl.buildersoft.web.servlet.table.AbstractServletUtil;

@WebServlet("/servlet/config/employee/SavePrevitionalInfo")
public class SavePrevitionalInfo extends AbstractServletUtil {
	private static final long serialVersionUID = 5316369008384063620L;

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Long id = Long.parseLong(request.getParameter("cId"));

		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);
		BSBeanUtilsSP bu = new BSBeanUtilsSP();

		Board apvType = (Board)bu.get(conn, new Board(), "pGetBoardByTypeAndKey", array2List("INSTITUTION", "APV"));
																							  
		Agreement agreement = getAgreement(conn, bu, id);
		mysql.callSingleSP(conn, "pDelAccountsByEmployeeAndType", array2List(id, apvType.getId()));
				
		BSFieldDataType dateType = BSTypeFactory.create(BSFieldType.Date);
		BSFieldDataType doubleType = BSTypeFactory.create(BSFieldType.Double);

		String[] apvInstitution = (String[]) request.getParameterValues("apvInstitution");
		String[] apvCurrency = (String[]) request.getParameterValues("apvCurrency");
		String[] apvAmount = (String[]) request.getParameterValues("apvAmount");
		for (int i = 0; i < apvInstitution.length; i++) {
			Account2 account = new Account2();
			account.setAccountType(apvType.getId());
			account.setCurrency(new Long(apvCurrency[i]));
			account.setAmount(new Double(apvAmount[i]));
			account.setEmployee(id);
			account.setInstitution(new Long(apvInstitution[i]));
			bu.save(conn, account);
		}
		
		Long exBox = Long.valueOf(request.getParameter("exBox"));
		Integer disabilityBurdens = Integer.valueOf(request
				.getParameter("disabilityBurdens"));
		Integer maternalLoad = Integer.valueOf(request
				.getParameter("maternalLoad"));
		Long afpEmp = Long.valueOf(request.getParameter("afpEmp"));
		Double additionalHealthUF = Double.valueOf(request
				.getParameter("additionalHealthUF"));

		Long health = Long.valueOf(request.getParameter("health"));
		Double additionalHealthCLP = Double.valueOf(request
				.getParameter("additionalHealthCLP"));

		agreement.setExBoxSystem(exBox);
		agreement.setDisabilityBurdens(disabilityBurdens);
		agreement.setMaternalLoads(maternalLoad);
		agreement.setPfm(afpEmp);
		agreement.setAdditionalHealthUF(additionalHealthUF);
		agreement.setHealth(health);
		agreement.setAdditionalHealthCLP(additionalHealthCLP);

		bu.save(conn, agreement);

		request.getRequestDispatcher("/servlet/remu/EmployeeManager").forward(
				request, response);
	}

	private Agreement getAgreement(Connection conn, BSBeanUtilsSP bu,
			Long idEmployee) {
		ContractualInfo ci = new ContractualInfo();
		return ci.getAgreement(conn, bu, idEmployee);
	}

	private Account2 getAccountByEmployee(Connection conn, BSBeanUtilsSP bu,
			Long idEmployee) {
		return null;
	}

}
