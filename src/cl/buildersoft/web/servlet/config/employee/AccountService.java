package cl.buildersoft.web.servlet.config.employee;

import java.sql.Connection;

import cl.buildersoft.framework.beans.Account2;

public interface AccountService {
	public Account2 getDefaultAccount(Connection conn, Long employee,
			String type, Long institution);
	/*
	 * public Account2 getAccountByEmployee(Connection conn, Long employee,
	 * String type);
	 */
}
