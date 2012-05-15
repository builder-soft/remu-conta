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
import cl.buildersoft.framework.util.BSBeanUtilsSP;
import cl.buildersoft.web.servlet.table.AbstractServletUtil;

@WebServlet("/servlet/config/employee/SavePayMode")
public class SavePayMode extends AbstractServletUtil {
	private static final long serialVersionUID = -5817512346998187995L;

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Long employeeId = Long.parseLong(request.getParameter("cId"));

		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);
		BSBeanUtilsSP bu = new BSBeanUtilsSP();

		Account2 account = getAccount(conn, bu, employeeId);
		Agreement agreement = getAgreement(conn, employeeId);

		/**
		 * BSFieldDataType dateType = BSTypeFactory.create(BSFieldType.Date);
		 * BSFieldDataType doubleType =
		 * BSTypeFactory.create(BSFieldType.Double);
		 */
		Long accountType = Long.parseLong(request.getParameter("cAccountType"));
		Long paymentType = Long.parseLong(request.getParameter("cPaymentType"));
		Long bank = Long.parseLong(request.getParameter("cBank"));
		String number = request.getParameter("cNumber");

		agreement.setPaymentType(paymentType);

		account.setAccountType(accountType);
		account.setInstitution(bank);
		account.setNumber(number);
		account.setCurrency(getCurrencyCLP(conn, bu));
		account.setEmployee(employeeId);

		bu.save(conn, account);
		bu.save(conn, agreement);

		request.getRequestDispatcher("/servlet/remu/EmployeeManager").forward(
				request, response);
	}

	private Long getCurrencyCLP(Connection conn, BSBeanUtilsSP bu) {
		Board currency = (Board) bu.get(conn, new Board(),
				"pGetBoardByTypeAndKey", array2List("CURRENCY", "CLP"));
		return currency.getId();

	}

	private Account2 getAccount(Connection conn, BSBeanUtilsSP bu,
			Long employeeId) {
		Account2 account = (Account2) bu.get(conn, new Account2(),
				"pListAccountsForEmployeeAndType2",
				array2List(employeeId, "BANK"));

		Account2 out = null;
		if (account == null) {
			out = new Account2();
//			out.setEmployee(employeeId);
		} else {
			out = account;
		}

		return out;
	}

	private Agreement getAgreement(Connection conn, Long idEmployee) {
		AgreementService agreementService = new AgreementServiceImpl();
		Agreement out = agreementService.getAgreementByEmployee(conn,
				idEmployee);
		return out;
	}
}