package cl.buildersoft.business.test;

import java.sql.Connection;

import cl.buildersoft.framework.database.BSmySQL;

public class AbstractTestUtil {
	protected Connection getConnection(BSmySQL mysql) {
		return mysql.getConnection("org.gjt.mm.mysql.Driver", "localhost", "remcon", "admin", "root");
	}
	
	protected Connection getConnection() {
		return getConnection(new BSmySQL());
	}
}
