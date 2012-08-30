package cl.buildersoft.business.service;

import java.sql.Connection;
import java.util.Calendar;

import cl.buildersoft.business.beans.Period;

public interface PeriodService {
	public Long insertPeriod(Connection conn, Calendar date);

	public void updatePeriod(Connection conn, Long periodId);

	public void openPeriod(Connection conn, Long periodId);

	public void closePeriod(Connection conn, Long periodId);

	public Period getOpenedPeriod(Connection conn);

	public Period getPeriod(Connection conn, Long periodId);

	public Integer lastDayMonth(Period period);

	public String periodAsString(Period period);

	public String periodAsShortString(Period period);

	public String getStatusName(Connection conn, Period period);

}
