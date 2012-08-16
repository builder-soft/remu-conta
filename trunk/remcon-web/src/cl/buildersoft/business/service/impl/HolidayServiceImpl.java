package cl.buildersoft.business.service.impl;

import static cl.buildersoft.framework.util.BSUtils.date2Calendar;
import static cl.buildersoft.framework.util.BSUtils.string2Calendar;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cl.buildersoft.business.beans.Agreement;
import cl.buildersoft.business.beans.Holiday;
import cl.buildersoft.business.beans.HolidayDevelop;
import cl.buildersoft.business.service.AgreementService;
import cl.buildersoft.business.service.HolidayService;
import cl.buildersoft.framework.database.BSBeanUtils;
import cl.buildersoft.framework.exception.BSException;
import cl.buildersoft.framework.type.BSCalendar;
import cl.buildersoft.framework.util.BSConfig;
import cl.buildersoft.framework.util.BSUtils;

public class HolidayServiceImpl implements HolidayService {

	private static final int MILLISECONDS_ON_DAY = 1000 * 60 * 60 * 24;

	@Override
	public List<HolidayDevelop> listDevelop(Connection conn, Long employee) throws BSException {
		List<HolidayDevelop> out = new ArrayList<HolidayDevelop>();
		HolidayDevelop holidayDevelop = null;
		Agreement agreement = getAgreement(conn, employee);
		Calendar startContract = date2Calendar(agreement.getStartContract());
		Calendar endContract, startPeriod, endPeriod = null;
		Integer id = 1;

		if (agreement.getContractType().equals(1L)) {
			endContract = date2Calendar(new Date());
		} else {
			endContract = date2Calendar(agreement.getEndContract());
		}

		Integer startYear = startContract.get(Calendar.YEAR);
		Integer endYear = endContract.get(Calendar.YEAR);
		List<Holiday> holidays = getHolidayOfEmployee(conn, employee);

		Integer index = startYear;
		Calendar firstDayOfYear = null;
		Calendar lastDayOfYear = null;
		Double progressiveDays = 0D;

		while (index <= endYear) {
			firstDayOfYear = string2Calendar(index + "-01-01", "yyyy-MM-dd");
			lastDayOfYear = string2Calendar(index + "-12-31", "yyyy-MM-dd");

			startPeriod = getLastDate(startContract, firstDayOfYear);
			endPeriod = getLessDate(endContract, lastDayOfYear);

			Double diffDays = getProportionalDays(conn, endPeriod, startPeriod);

			progressiveDays = getProgresiveDays(agreement.getMonthsQuoted(), index, startYear, progressiveDays);

			/**
			 * <code>

			SET vNormalTaken = fGetTakenDays(vEmployee, vFirstDayYear, vLastDayYear, TRUE);
			SET vProgressiveTaken = fGetTakenDays(vEmployee, vFirstDayYear, vLastDayYear, FALSE);
			
			SET vLastBalance = vLastBalance + vDiffDays+vProgressiveDays;
			
			INSERT INTO tempTable(	cYear, cNormal, 
									cProgressive, cSum, cBalance, 
									cNormalTaken, cProgressiveTaken, cBalanceTaken) 
			VALUES(					i, vDiffDays, 
									vProgressiveDays, vDiffDays+vProgressiveDays, vLastBalance, 
									vNormalTaken, vProgressiveTaken, NULL);
			</code>
			 */
			holidayDevelop = new HolidayDevelop();
			holidayDevelop.setId(id++);
			holidayDevelop.setYear(index);
			holidayDevelop.setNormal(diffDays);
			holidayDevelop.setProgressive(progressiveDays);
			holidayDevelop.setSum(diffDays + progressiveDays);
			holidayDevelop.setBalance(holidayDevelop.getSum() + getPrevioBalance(out, id));
			holidayDevelop.setNormalTaken(getNormalTaken(holidays, index, startPeriod, endPeriod));

			out.add(holidayDevelop);

			index++;
		}

		return out;
	}

	private Double getNormalTaken(List<Holiday> holidays, Integer year, Calendar firstDayOfYear, Calendar lastDayOfYear) {
		Calendar from, to = null;
		Integer yearFrom, yearTo = null;
		Double out = 0D;
		Long firstDay = firstDayOfYear.getTimeInMillis();
		Long lastDay = lastDayOfYear.getTimeInMillis();

		BSCalendar cal = new BSCalendar();

		for (Holiday holiday : holidays) {
			from = date2Calendar(holiday.getFrom());
			to = date2Calendar(holiday.getTo());
			yearFrom = from.get(Calendar.YEAR);
			yearTo = to.get(Calendar.YEAR);

			// vacaciones en el año en curso
			if (yearFrom.equals(year) || yearTo.equals(year)) {
				// Doble año
				if (yearFrom.equals(yearTo)) {
					out += Double.parseDouble("" + holiday.getNormal());
				} else {
					// Integer currentYear = yearFrom;
					Calendar currentDate = from;

					while (currentDate.getTimeInMillis() <= to.getTimeInMillis()) {
						// while (currentYear.equals(yearFrom)) {
//						System.out.println(BSUtils.calendar2String(currentDate));
						currentDate.add(Calendar.DAY_OF_MONTH, 1);
						// System.out.println(BSUtils.calendar2String(currentDate));
						Long current = currentDate.getTimeInMillis();

						if (current > firstDay && current < lastDay) {
							out++;
						}
						// currentYear = currentDate.get(Calendar.YEAR);
					}
				}
			}
		}

		// Holiday x = holidays.get(1);

		/**
		 * <code>
ver si hay vacaciones tomadas en el año
si tiene vacaciones tomadas

	recorrer las vacaciones tomadas	
		ver si el periodo de vacaciones tomadas son de uno o dos años.
		si son de 1 año -> entonces
			contar los días tomados por el rango de fechas
		si no
			recorrer los días seleccionados y contar cuantos son del año en curso.
		fin
	fin recorrido
fin
		  <code>
		 */

		return out;
	}

	private List<Holiday> getHolidayOfEmployee(Connection conn, Long employee) {
		BSBeanUtils bu = new BSBeanUtils();
		List<Holiday> out = (List<Holiday>) bu.list(conn, new Holiday(), "cEmployee=?", employee);
		return out;
	}

	private Double getPrevioBalance(List<HolidayDevelop> list, Integer id) {
		Double out = 0D;
		if (!id.equals(2)) {
			HolidayDevelop hd = list.get(id - 3);
			out = hd.getBalance();
		}
		return out;
	}

	private Double getProportionalDays(Connection conn, Calendar startPeriod, Calendar endPeriod) {
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
