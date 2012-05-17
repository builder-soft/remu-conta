package cl.buildersoft.web.servlet.config.employee;

import java.sql.Connection;
import java.util.List;

import cl.buildersoft.framework.beans.Account2;
import cl.buildersoft.framework.beans.Board;
import cl.buildersoft.framework.util.BSBeanUtilsSP;
import cl.buildersoft.web.servlet.table.AbstractServletUtil;

public class AccountServiceImpl extends AbstractServletUtil implements
		AccountService {
	private static final long serialVersionUID = -2886361934618815226L;

	@Override
	public Account2 getDefaultAccount(Connection conn, Long employee,
			String type, Long institution) {
		BSBeanUtilsSP bu = new BSBeanUtilsSP();

		Account2 account = (Account2) bu.get(conn, new Account2(),
				"pListAccountsForEmployeeAndType2", array2List(employee, type));

		if (account == null) {
			account = new Account2();
			account.setAccountType(getDefaultType(conn, bu, type));
			account.setCurrency(getDefaultCurrency(conn, bu));
			account.setEmployee(employee);
			account.setInstitution(institution);
			bu.save(conn, account);
		}

		return account;
	}

	private Long getDefaultCurrency(Connection conn, BSBeanUtilsSP bu) {
		return getDefaultType(conn, bu, "CURRENCY");
	}

	private Long getDefaultType(Connection conn, BSBeanUtilsSP bu, String type) {
		Board board = (Board) bu.get(conn, new Board(), "pListBoardByType",
				array2List(type));
		return board.getId();
	}
}
