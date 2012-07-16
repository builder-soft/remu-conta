package cl.buildersoft.business.service.impl;

import java.sql.Connection;
import java.util.Calendar;

import cl.buildersoft.business.beans.Period;
import cl.buildersoft.business.service.PeriodService;
import cl.buildersoft.framework.database.BSBeanUtils;
import cl.buildersoft.framework.database.BSmySQL;

public class PeriodServiceImpl implements PeriodService {

	@Override
	public Long insertPeriod(Connection conn, Calendar date) {
		BSmySQL mysql = new BSmySQL();
		return Long.parseLong(mysql.callFunction(conn, "fInsertPeriod", date));
	}

	@Override
	public void updatePeriod(Connection conn, Long periodId) {
		BSmySQL mysql = new BSmySQL();
		mysql.callSingleSP(conn, "pUpdatePeriod", periodId);
	}

	@Override
	public void openPeriod(Connection conn, Long periodId) {
		BSmySQL mysql = new BSmySQL();
		mysql.callSingleSP(conn, "pOpenPeriod", periodId);
	}

	@Override
	public void closePeriod(Connection conn, Long periodId) {
		BSmySQL mysql = new BSmySQL();
		mysql.callSingleSP(conn, "pClosePeriod", periodId);
	}

	@Override
	public Period getOpenedPeriod(Connection conn) {
		BSmySQL mysql = new BSmySQL();
		Period period = new Period();
		BSBeanUtils bu = new BSBeanUtils();

		Long opened = Long.parseLong(mysql.callFunction(conn, "fGetOpenedPeriod", null));
		period.setId(opened);
		bu.search(conn, period);

		return period;
	}

	@Override
	public Period getPeriod(Connection conn, Long periodId) {
		Period period = new Period();
		BSBeanUtils bu = new BSBeanUtils();

		period.setId(periodId);
		bu.search(conn, period);

		return period;
	}

}