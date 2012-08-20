package cl.buildersoft.business.service.impl;

import static cl.buildersoft.framework.util.BSDateTimeUtil.date2Calendar;
import static cl.buildersoft.framework.util.BSDateTimeUtil.string2Calendar;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cl.buildersoft.business.beans.Agreement;
import cl.buildersoft.business.beans.HolidayDevelop;
import cl.buildersoft.business.service.AgreementService;
import cl.buildersoft.business.service.HolidayService;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.exception.BSException;
import cl.buildersoft.framework.exception.BSProgrammerException;
import cl.buildersoft.framework.util.BSConfig;

public class HolidayServiceImpl implements HolidayService {

	private static final int MILLISECONDS_ON_DAY = 1000 * 60 * 60 * 24;

	@Override
	public List<HolidayDevelop> listDevelop(Connection conn, Long employee) throws BSException {
		List<HolidayDevelop> out = new ArrayList<HolidayDevelop>();
		HolidayDevelop holidayDevelop = null;
		Agreement agreement = getAgreement(conn, employee);

		Calendar startContract = date2Calendar(agreement.getStartContract());
		Calendar endContract = getEndContract(agreement);

		Integer startYear = startContract.get(Calendar.YEAR);
		Integer endYear = endContract.get(Calendar.YEAR);

		Double[] holidays = getHolidaysOfEmployee(conn, employee);
		Double balanceNormalTaken = holidays[0];
		Double balanceCreepingTaken = holidays[1];

//		System.out.println("balanceNormalTaken:" + balanceNormalTaken + "\nbalanceCreepingTaken:" + balanceCreepingTaken);

		Integer year = startYear;
		// Calendar firstDayOfYear = null;
		// Calendar lastDayOfYear = null;
		Double ratioDays = null;
		Calendar startPeriod = null;
		Calendar endPeriod = null;
		Double creepingDays = 0D;
		Double normalPastBalace = 0D;
		Double creepingPastBalace = 0D;
		Long id = 1L;
		while (year <= endYear) {
			startPeriod = getStartPeriod(startContract, year);
			endPeriod = getEndPeriod(endContract, year);

			ratioDays = getRatioDays(conn, endPeriod, startPeriod);
			creepingDays = getProgresiveDays(agreement.getMonthsQuoted(), year, startYear, creepingDays);

			holidayDevelop = getHolidayDevelopInstance(year, id);

			balanceNormalTaken = calculateNormalValues(holidayDevelop, balanceNormalTaken, ratioDays, normalPastBalace);
			normalPastBalace = holidayDevelop.getNormalBalance();

			balanceCreepingTaken = calculateCreepingValues(holidayDevelop, balanceCreepingTaken, creepingDays, creepingPastBalace);
			creepingPastBalace = holidayDevelop.getCreepingBalance();

			holidayDevelop.setTotalBalance(holidayDevelop.getNormalBalance() + holidayDevelop.getCreepingBalance());

			out.add(holidayDevelop);
			year++;
			id++;
		}

		return out;
	}

	private HolidayDevelop getHolidayDevelopInstance(Integer year, Long id) {
		HolidayDevelop holidayDevelop;
		holidayDevelop = new HolidayDevelop();
		holidayDevelop.setId(id);
		holidayDevelop.setYear(year);
		return holidayDevelop;
	}

	private Double calculateNormalValues(HolidayDevelop holidayDevelop, Double balanceNormalTaken, Double ratioDays,
			Double normalPastBalace) {
		holidayDevelop.setNormalRatio(ratioDays);
		if (balanceNormalTaken >= ratioDays) {
			holidayDevelop.setNormalTaken(ratioDays);
			balanceNormalTaken -= ratioDays;
		} else {
			holidayDevelop.setNormalTaken(balanceNormalTaken);
			balanceNormalTaken -= balanceNormalTaken;
		}
		holidayDevelop.setNormalBalance(ratioDays - holidayDevelop.getNormalTaken() + normalPastBalace);
		return balanceNormalTaken;
	}

	private Double calculateCreepingValues(HolidayDevelop holidayDevelop, Double balanceCreepeingTaken, Double ratioDays,
			Double creepingPastBalace) {
		holidayDevelop.setCreepingRatio(ratioDays);

		if (balanceCreepeingTaken >= ratioDays) {
			holidayDevelop.setCreepingTaken(ratioDays);
			balanceCreepeingTaken -= ratioDays;
		} else {
			holidayDevelop.setCreepingTaken(balanceCreepeingTaken);
			balanceCreepeingTaken -= balanceCreepeingTaken;
		}
		holidayDevelop.setCreepingBalance(ratioDays - holidayDevelop.getCreepingTaken() + creepingPastBalace);

		return balanceCreepeingTaken;
	}

	private Calendar getEndPeriod(Calendar endContract, Integer year) {
		Calendar endPeriod;
		Calendar lastDayOfYear;
		lastDayOfYear = string2Calendar(year + "-12-31", "yyyy-MM-dd");
		endPeriod = getLessDate(endContract, lastDayOfYear);
		return endPeriod;
	}

	private Calendar getStartPeriod(Calendar startContract, Integer year) {
		Calendar startPeriod;
		Calendar firstDayOfYear;
		firstDayOfYear = string2Calendar(year + "-01-01", "yyyy-MM-dd");
		startPeriod = getLastDate(startContract, firstDayOfYear);
		return startPeriod;
	}

	private Calendar getEndContract(Agreement agreement) {
		Calendar endContract;
		if (agreement.getContractType().equals(1L)) {
			endContract = date2Calendar(new Date());
		} else {
			endContract = date2Calendar(agreement.getEndContract());
		}
		return endContract;
	}
/**<code>
	private Double getProgressiveTaken(List<Holiday> holidays, Integer year, Calendar firstDayOfYear, Calendar lastDayOfYear) {
		return getTaken(holidays, year, firstDayOfYear, lastDayOfYear, false);
	}

	private Double getNormalTaken(List<Holiday> holidays, Integer year, Calendar firstDayOfYear, Calendar lastDayOfYear) {
		return getTaken(holidays, year, firstDayOfYear, lastDayOfYear, true);
	}

	private Double getTaken(List<Holiday> holidays, Integer year, Calendar firstDayOfYear, Calendar lastDayOfYear,
			Boolean isNormal) {
		Calendar from, to = null;
		Integer yearFrom, yearTo = null;
		Double out = 0D;
		Long firstDay = firstDayOfYear.getTimeInMillis();
		Long lastDay = lastDayOfYear.getTimeInMillis();

		for (Holiday holiday : holidays) {
			from = date2Calendar(holiday.getFrom());
			to = date2Calendar(holiday.getTo());
			yearFrom = from.get(Calendar.YEAR);
			yearTo = to.get(Calendar.YEAR);

			// vacaciones en el año en curso
			if (yearFrom.equals(year) || yearTo.equals(year)) {
				// Doble año
				if (yearFrom.equals(yearTo)) {
					out += Double.parseDouble("" + (isNormal ? holiday.getNormal() : holiday.getProgressive()));
				} else {
					Calendar currentDate = from;

					while (currentDate.getTimeInMillis() <= to.getTimeInMillis()) {
						Long current = currentDate.getTimeInMillis();

						if (current >= firstDay && current <= lastDay) {
							out++;
						}
						currentDate.add(Calendar.DAY_OF_MONTH, 1);
					}
				}
			}
		}

		return out;
	}
</code>*/
	private Double[] getHolidaysOfEmployee(Connection conn, Long employee) {
		Double[] out = new Double[2];
		out[0] = 0D;
		out[1] = 0D;

		BSmySQL mysql = new BSmySQL();
		ResultSet holidays = mysql.callSingleSP(conn, "pGetHolidayByEmployee", employee);
		try {
			if (holidays.next()) {
				out[0] = holidays.getDouble("cTakenNormal");
				out[1] = holidays.getDouble("cTakenCreeping");
			}
		} catch (SQLException e) {
			throw new BSProgrammerException(e);
		}

		return out;
	}
/**<code>
	private Double getPrevioBalance(List<HolidayDevelop> list, Integer id) {
		Double out = 0D;
		if (!id.equals(2)) {
			HolidayDevelop hd = list.get(id - 3);
			// out = hd.getBalance();
		}
		return out;
	}
</code>*/
	
	private Double getRatioDays(Connection conn, Calendar startPeriod, Calendar endPeriod) {
		BSConfig config = new BSConfig();
		Integer holidaysForYear = config.getInteger(conn, "DAYS_FOR_YEAR");
		Integer daysOfYear = 364;

		Long daysDiff = dateDiff(startPeriod, endPeriod);
		Integer startYear = startPeriod.get(Calendar.YEAR);
		if (startYear == endPeriod.get(Calendar.YEAR)) {
			if (startYear % 4 == 0) {
				daysOfYear = 365;
			}
		}
		return (double) (daysDiff * holidaysForYear) / daysOfYear;
	}

	private Agreement getAgreement(Connection conn, Long employee) {
		AgreementService as = new AgreementServiceImpl();
		return as.getAgreementByEmployee(conn, employee);
	}

	private Calendar getLastDate(Calendar date1, Calendar date2) {
		Calendar out = null;
		if (date1.after(date2)) {
			out = date1;
		} else {
			out = date2;
		}

		return out;
	}

	private Calendar getLessDate(Calendar date1, Calendar date2) {
		Calendar out = null;
		if (date1.before(date2)) {
			out = date1;
		} else {
			out = date2;
		}
		return out;
	}

	private Long dateDiff(Calendar prevDate, Calendar postDate) {
		Long diff = prevDate.getTimeInMillis() - postDate.getTimeInMillis();
		return (diff / MILLISECONDS_ON_DAY);
	}

	private Double getProgresiveDays(Integer monthsQuoted, Integer currentYear, Integer startYear, Double progresive) {
		Double out = progresive;
		Integer contracted = null;
		if (monthsQuoted >= 120) {
			contracted = currentYear - startYear;

			if (contracted >= 3) {
				if (contracted % 3 == 0) {
					out = progresive + 1;
				}
			}
		}
		return out;
	}
}
