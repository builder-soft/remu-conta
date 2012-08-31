package cl.buildersoft.business.test.period;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cl.buildersoft.business.beans.Period;
import cl.buildersoft.business.service.PeriodService;
import cl.buildersoft.business.service.impl.PeriodServiceImpl;
import cl.buildersoft.business.test.AbstractTestUtil;
import cl.buildersoft.framework.database.BSBeanUtils;
import cl.buildersoft.framework.database.BSmySQL;

public class TestPeriod extends AbstractTestUtil {
	private static final long INIT = 1L;
	private static final long OPEN = 2L;
	private static final long CLOSED = 3L;
	Connection conn = null;
	BSmySQL mysql = new BSmySQL();
	Long periodId = null;
	PeriodService periodService = null;

	@Before
	public void setUp() throws Exception {
		conn = getConnection(mysql);
		periodId = Long.parseLong(mysql.callFunction(conn, "fInsertPeriod", Calendar.getInstance()));
		mysql.closeSQL();
		periodService = new PeriodServiceImpl();
	}

	@After
	public void tearDown() throws Exception {
		mysql.update(conn, "SET @@foreign_key_checks = 0;", null);
		mysql.update(conn, "TRUNCATE tUniqueTax", null);
		mysql.update(conn, "TRUNCATE tPeriod", null);
		mysql.update(conn, "UPDATE tParameter SET cValue = '36862' WHERE cKey = 'UTM';", null);
		mysql.closeConnection(conn);
	}

	@Test
	public void insert1() {
		Boolean success = Boolean.FALSE;
		BSBeanUtils bu = new BSBeanUtils();
		Period period = new Period();
		period.setId(periodId);
		success = bu.search(conn, period);
		assertTrue(success);
	}

	@Test
	public void insert2() {
		Long periodId2 = Long.parseLong(mysql.callFunction(conn, "fInsertPeriod", Calendar.getInstance()));

		// Boolean success = Boolean.FALSE;
		BSBeanUtils bu = new BSBeanUtils();
		Period period = new Period();
		period.setId(periodId);
		bu.search(conn, period);

		assertTrue(period.getId().equals(periodId2));
	}

	@Test
	public void insert3() {
		Calendar date = Calendar.getInstance();
		date.add(Calendar.MONTH, -1);
		Long newPeriod = periodService.insertPeriod(conn, date);
		assertTrue(!newPeriod.equals(periodId));
	}

	@Test
	public void insert4() {
		Calendar date = Calendar.getInstance();
		date.add(Calendar.MONTH, -1);
		Long newPeriod = periodService.insertPeriod(conn, date);
		Period period = periodService.getPeriod(conn, newPeriod);
		assertTrue(period.getPeriodStatus().equals(INIT));
	}
	
	@Test
	public void update1() {
		Double oldUtm, newUtm = null;
		BSBeanUtils bu = new BSBeanUtils();
		Period period = new Period();
		period.setId(periodId);
		bu.search(conn, period);

		oldUtm = period.getUtm();

		mysql.update(conn, "UPDATE tParameter SET cValue = '37000' WHERE cKey = 'UTM';", null);

		mysql.callSingleSP(conn, "pUpdatePeriod", periodId);

		period.setId(periodId);
		bu.search(conn, period);
		newUtm = period.getUtm();

		assertTrue("Son iguales", !oldUtm.equals(newUtm));
	}

	@Test
	public void update2() {
		Double oldUtm, newUtm = null;
		BSBeanUtils bu = new BSBeanUtils();
		Period period = new Period();
		period.setId(periodId);
		bu.search(conn, period);

		oldUtm = period.getUtm();

		mysql.callSingleSP(conn, "pClosePeriod", periodId);
		mysql.update(conn, "UPDATE tParameter SET cValue = '37000' WHERE cKey = 'UTM';", null);

		mysql.callSingleSP(conn, "pUpdatePeriod", periodId);

		period.setId(periodId);
		bu.search(conn, period);
		newUtm = period.getUtm();

		assertTrue("Son distintos", oldUtm.equals(newUtm));
	}

	@Test
	public void update3() {
		Double oldUtm, newUtm = null;
		Period period = periodService.getPeriod(conn, periodId);
		oldUtm = period.getUtm();

		mysql.update(conn, "UPDATE tParameter SET cValue = '37000' WHERE cKey = 'UTM';", null);
		periodService.updatePeriod(conn, periodId);

		period = periodService.getPeriod(conn, periodId);
		newUtm = period.getUtm();

		assertTrue("Son distintos", !oldUtm.equals(newUtm));
	}

	@Test
	public void closePeriod1() {
		Long status = null;
		BSBeanUtils bu = new BSBeanUtils();
		Period period = new Period();

		mysql.callSingleSP(conn, "pClosePeriod", periodId);

		period.setId(periodId);
		bu.search(conn, period);
		status = period.getPeriodStatus();

		assertTrue("No esta cerrado", status.equals(CLOSED));
	}

	@Test
	public void closePeriod2() {
		Long status = null;

		mysql.callSingleSP(conn, "pClosePeriod", periodId);

		Period period = periodService.getPeriod(conn, periodId);
		status = period.getPeriodStatus();

		assertTrue("No esta cerrado", status.equals(CLOSED));
	}

	@Test
	public void openPeriod1() {
		Long status = null;
		periodService.openPeriod(conn, periodId);

		Period period = periodService.getPeriod(conn, periodId);
		status = period.getPeriodStatus();

		assertTrue("No esta cerrado", status.equals(OPEN));
	}

	@Test
	public void openPeriod2() {
		Long status = null;
		BSBeanUtils bu = new BSBeanUtils();
		Period period = new Period();

		mysql.callSingleSP(conn, "pOpenPeriod", periodId);

		period.setId(periodId);
		bu.search(conn, period);
		status = period.getPeriodStatus();

		assertTrue("No esta cerrado", status.equals(OPEN));
	}

	@Test
	public void getOpenedPeriod1() {
		Calendar date = Calendar.getInstance();
		date.add(Calendar.MONTH, 1);
		Long.parseLong(mysql.callFunction(conn, "fInsertPeriod", date));

		Long opened = Long.parseLong(mysql.callFunction(conn, "fGetOpenedPeriod", null));

		assertTrue("No corresponde periodo", opened.equals(0L));
	}

	@Test
	public void getOpenedPeriod2() {
		Calendar date = Calendar.getInstance();
		date.add(Calendar.MONTH, 1);
		Long.parseLong(mysql.callFunction(conn, "fInsertPeriod", date));

		periodService.openPeriod(conn, periodId);

		BSBeanUtils bu = new BSBeanUtils();
		Period period = new Period();

		period.setId(periodId);
		bu.search(conn, period);
		Long status = period.getPeriodStatus();

		assertTrue("No corresponde periodo", status.equals(2L));
	}

	@Test
	public void getOpenedPeriod3() {
		Calendar date = Calendar.getInstance();
		date.add(Calendar.MONTH, 1);

		periodService.insertPeriod(conn, date);

		Period opened = periodService.getOpenedPeriod(conn);

		assertTrue("No corresponde periodo", opened.getPeriodStatus() == null);
	}
}
