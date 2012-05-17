package cl.buildersoft.web.servlet.config.employee;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.framework.beans.Account2;
import cl.buildersoft.framework.beans.Agreement;
import cl.buildersoft.framework.beans.BSBean;
import cl.buildersoft.framework.beans.Board;
import cl.buildersoft.framework.beans.Employee;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.util.BSBeanUtilsSP;
import cl.buildersoft.web.servlet.table.AbstractServletUtil;

@WebServlet("/servlet/config/employee/PayMode")
public class PayMode extends AbstractServletUtil {
	private static final long serialVersionUID = -7622439846904256208L;

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Long employeeId = Long.parseLong(request.getParameter("cId"));

		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);
		BSBeanUtilsSP bu = new BSBeanUtilsSP();

		List<BSBean> banks = (List<BSBean>) bu.list(conn, new Board(),
				"pListBoardByType", "BANK");
		List<BSBean> accountTypes = (List<BSBean>) bu.list(conn, new Board(),
				"pListBoardByType", "ACCOUNT_TYPE");
		List<BSBean> paymentTypes = (List<BSBean>) bu.list(conn, new Board(),
				"pListBoardByType", "PAYMENT_TYPE");

		Account2 account = getAccount(conn, bu, employeeId);

		request.setAttribute("Banks", banks);
		request.setAttribute("AccountTypes", accountTypes);
		request.setAttribute("PaymentTypes", paymentTypes);
		request.setAttribute("Employee", getEmployee(conn, bu, employeeId));
		request.setAttribute("Account", account);
		request.setAttribute("Agreement", getAgreement(conn, bu, employeeId));

		request.getRequestDispatcher(
				"/WEB-INF/jsp/config/employee/pay-mode.jsp").forward(request,
				response);
	}

	
	private Account2 getAccount(Connection conn, BSBeanUtilsSP bu,
			Long employeeId) {
		Account2 account = (Account2) bu.get(conn, new Account2(),
				"pListAccountsForEmployeeAndType2",
				array2List(employeeId, "BANK"));

//		Account2 out = null;
		if (account == null) {
			account = new Account2();
			account.setNumber("");
//			out.setEmployee(employeeId);
		}

		return account;
	}
/**<code>
	public Account getAccount(Connection conn, BSBeanUtilsSP bu, Long employeeId) {
		List<Account> account = (List<Account>) bu.list(conn, new Account(),
				"pListAccountsForEmployeeAndType",
				array2List(employeeId, "BANK_ACCOUNT"));
		return account.size() == 0 ? new Account() : account.get(0);
	}
</code>*/
	private Employee getEmployee(Connection conn, BSBeanUtilsSP bu, Long id) {
		Employee out = new Employee();
		out.setId(id);
		bu.search(conn, out);
		return out;
	}

	public Agreement getAgreement(Connection conn, BSBeanUtilsSP bu,
			Long idEmployee) {
		AgreementService agreementService = new AgreementServiceImpl();
		Agreement out = agreementService.getAgreementByEmployee(conn,
				idEmployee);
		return out;
	}

}
