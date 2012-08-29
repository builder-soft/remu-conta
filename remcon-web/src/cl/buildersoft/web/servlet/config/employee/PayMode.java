package cl.buildersoft.web.servlet.config.employee;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.business.beans.AccountType;
import cl.buildersoft.business.beans.Agreement;
import cl.buildersoft.business.beans.Bank;
import cl.buildersoft.business.beans.Employee;
import cl.buildersoft.business.beans.PaymentType;
import cl.buildersoft.business.service.AgreementService;
import cl.buildersoft.business.service.impl.AgreementServiceImpl;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.util.BSBeanUtilsSP;
import cl.buildersoft.web.servlet.common.AbstractServletUtil;

@WebServlet("/servlet/config/employee/PayMode")
public class PayMode extends AbstractServletUtil {
	private static final long serialVersionUID = -7622439846904256208L;

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Long employeeId = Long.parseLong(request.getParameter("cId"));

		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);
		BSBeanUtilsSP bu = new BSBeanUtilsSP();

		List<Bank> banks = (List<Bank>) bu.list(conn, new Bank(), "pListBank");
		List<AccountType> accountTypes = (List<AccountType>) bu.list(conn,
				new AccountType(), "pListAccountType");
		List<PaymentType> paymentTypes = (List<PaymentType>) bu.list(conn,
				new PaymentType(), "pListPaymentType");

		// RagreementAPV account = getAccount(conn, bu, employeeId);

		request.setAttribute("Banks", banks);
		request.setAttribute("AccountTypes", accountTypes);
		request.setAttribute("PaymentTypes", paymentTypes);
		request.setAttribute("Employee", getEmployee(conn, bu, employeeId));
		request.setAttribute("Agreement", getAgreement(conn, bu, employeeId));

		request.getRequestDispatcher(
				"/WEB-INF/jsp/config/employee/pay-mode.jsp").forward(request,
				response);
	}

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
