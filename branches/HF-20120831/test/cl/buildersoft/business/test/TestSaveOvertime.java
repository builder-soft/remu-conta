package cl.buildersoft.business.test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cl.buildersoft.framework.database.BSmySQL;

public class TestSaveOvertime {
	BSmySQL mysql = null;
	Connection conn = null;
	Long period = null;

	@Before
	public void setUp() throws Exception {
		mysql = new BSmySQL();
		conn = mysql.getConnection("org.gjt.mm.mysql.Driver", "localhost", "remcon", "admin", "root");

		Calendar datePeriod = Calendar.getInstance();
		datePeriod.set(Calendar.DAY_OF_MONTH, 1);
		datePeriod.set(Calendar.MONTH, 4);
		datePeriod.set(Calendar.YEAR, 2012);

		period = Long.parseLong(mysql.callFunction(conn, "fSavePeriod", datePeriod));

		datePeriod.set(Calendar.DAY_OF_MONTH, 5);
		
		clearOvertime(2L);
		
		addOvertime(2L, period, datePeriod, 2);

	}

	private void clearOvertime(Long employee) {
		String sql = "DELETE FROM tOvertime WHERE cEmployee=?;";
		mysql.update(conn, sql, employee);		
	}

	@After
	public void tearDown() throws Exception {
		mysql.closeSQL();
	}

	@Test
	public void test1() {
		// pSaveOvertime(pBook, pEmployee, pPeriod);
		List<Object> prms = new ArrayList<Object>();
		prms.add(1L);
		prms.add(1L);
		prms.add(period);

		mysql.callSingleSP(conn, "pSaveOvertime", prms);
		// assertTrue(expected == 1L);
		Assert.assertTrue(getOvertime(conn, 1L) == 0);
	}

	private Double getOvertime(Connection conn, Long book) {
		String sql = "SELECT cOvertime FROM remcon.tBook WHERE cEmployee=?";
		Double out = Double.parseDouble(mysql.queryField(conn, sql, book));
		return out;
	}

	private void addOvertime(Long employee, Long period, Calendar date, Integer hours) {
		String sql = "INSERT INTO tOvertime(cEmployee, cPeriod, cDate, cHours) VALUES(?,?,?,?)";
		List<Object> prms = new ArrayList<Object>();
		prms.add(employee);
		prms.add(period);
		prms.add(date);
		prms.add(hours);

		mysql.update(conn, sql, prms);

	}

	@Test
	public void test2() {
		List<Object> prms = new ArrayList<Object>();
		prms.add(2L);
		prms.add(2L);
		prms.add(period);

		mysql.callSingleSP(conn, "pSaveOvertime", prms);
		// assertTrue(expected == 1L);
		
		Double expected = getOvertime(conn, 2L);
		System.out.println(expected);
		Assert.assertTrue(expected == 2D);
	}

}
