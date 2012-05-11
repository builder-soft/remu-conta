package cl.buildersoft.web.servlet.config.employee;

import java.sql.Connection;

import cl.buildersoft.framework.beans.Account;

public interface AccountService {
	public Account getDefaultAccount(Connection conn, Long employee);

	public Account getAccountByEmployee(Connection conn, Long employee);
}
