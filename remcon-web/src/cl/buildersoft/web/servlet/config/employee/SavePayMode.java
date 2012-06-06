package cl.buildersoft.web.servlet.config.employee;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.business.service.AgreementService;
import cl.buildersoft.business.service.impl.AgreementServiceImpl;
import cl.buildersoft.framework.beans.Agreement;
import cl.buildersoft.framework.beans.Currency;
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

		Agreement agreement = getAgreement(conn, employeeId);

		Long accountType = Long.parseLong(request.getParameter("cAccountType"));
		Long paymentType = Long.parseLong(request.getParameter("cPaymentType"));
		Long bank = Long.parseLong(request.getParameter("cBank"));
		String number = request.getParameter("cNumber");

		agreement.setPaymentType(paymentType);
		agreement.setAccountType(accountType);
		agreement.setBank(bank);
		agreement.setAccountNumber(number);
		agreement.setCurrencyAccount2(getCurrencyCLP(conn, bu));

		bu.save(conn, agreement);

		request.getRequestDispatcher("/servlet/remu/EmployeeManager").forward(
				request, response);
	}

	private Long getCurrencyCLP(Connection conn, BSBeanUtilsSP bu) {
		Currency currency = (Currency) bu.get(conn, new Currency(),
				"pGetCurrencyByKey", "CLP");
		return currency.getId();

	}

	private Agreement getAgreement(Connection conn, Long idEmployee) {
		AgreementService agreementService = new AgreementServiceImpl();
		Agreement out = agreementService.getAgreementByEmployee(conn,
				idEmployee);
		return out;
	}
}
