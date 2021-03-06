package cl.buildersoft.business.test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import cl.buildersoft.framework.database.BSmySQL;

public class TestSaveBookForEmployee extends TestCase {
	BSmySQL mysql = null;
	Connection conn = null;
	Long period = null;

	@BeforeClass
	public void setUp() throws Exception {
		mysql = new BSmySQL();
		conn = mysql.getConnection("org.gjt.mm.mysql.Driver", "localhost", "remcon", "admin", "root");

		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.MONTH, 4);
		c.set(Calendar.YEAR, 2012);

		period = Long.parseLong(mysql.callFunction(conn, "pSavePeriod", c));
	}

	@After
	public void tearDown() throws Exception {
		mysql.closeSQL();
	}

	@Test
	public void testSaveBookForEmployee1() {
		List<Object> book = new ArrayList<Object>();
		book.add(1L);
		book.add(1L);

		Long expected = Long.parseLong(mysql.callFunction(conn, "fSaveBookForEmployee", book));

		super.assertTrue(expected == 1L);

	}

	@Test
	public void testSaveBookForEmployee2() {
		List<Object> book = new ArrayList<Object>();
		book.add(1L);
		book.add(2L);

		Long expected = Long.parseLong(mysql.callFunction(conn, "fSaveBookForEmployee", book));
		super.assertTrue(expected == 2L);

	}

}
