package cl.buildersoft.web.servlet.config.employee;

import java.sql.Connection;

import cl.buildersoft.framework.beans.Account;
import cl.buildersoft.framework.beans.Agreement;
import cl.buildersoft.framework.util.BSBeanUtilsSP;

public class AccountServiceImpl implements AccountService {

	@Override
	public Account getDefaultAccount(Connection conn, Long employee) {
		BSBeanUtilsSP bu = new BSBeanUtilsSP();

		Account account= new Account();
		
		
		
		return account;
	}

	@Override
	public Account getAccountByEmployee(Connection conn, Long employee) {
		// TODO Auto-generated method stub
		return null;
	}

}
