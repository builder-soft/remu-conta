package cl.buildersoft.business.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cl.buildersoft.business.beans.Agreement;
import cl.buildersoft.business.beans.HolidayDevelop;
import cl.buildersoft.business.service.AgreementService;
import cl.buildersoft.business.service.HolidayService;
import cl.buildersoft.framework.exception.BSException;
import cl.buildersoft.framework.util.BSConfig;
import cl.buildersoft.framework.util.BSUtils;

public class HolidayServiceImpl implements HolidayService {

	private static final int MILLISECONDS_ON_DAY = 1000 * 60 * 60 * 24;

	@Override
	public List<HolidayDevelop> listDevelop(Connection conn, Long employee) throws BSException {
		List<HolidayDevelop> out = new ArrayList<HolidayDevelop>();
		HolidayDevelop holidayDevelop = null;
		Agreement agreement = getAgreement(conn, employee);
		Calendar startContract = BSUtils.date2Calendar(agreement.getStartContract());
		Calendar endContract, startPeriod, endPeriod = null;
		Long id = 1L;

		if (agreement.getContractType().equals(1L)) {
			endContract = BSUtils.date2Calendar(new Date());
		} else {
			endContract = BSUtils.date2Calendar(agreement.getEndContract());
		}

		Integer startYear = startContract.get(Calendar.YEAR);
		Integer endYear = endContract.get(Calendar.YEAR);

		Integer index = startYear;
		Calendar firstDayOfYear = null;
		Calendar lastDayOfYear = null;
		Double progressiveDays = 0D;

		while (index <= endYear) {
			firstDayOfYear = BSUtils.string2Calendar(index + "-01-01", "yyyy-MM-dd");
			lastDayOfYear = BSUtils.string2Calendar(index + "-12-31", "yyyy-MM-dd");

			startPeriod = getLastDate(startContract, firstDayOfYear);
			endPeriod = getLessDate(endContract, lastDayOfYear);

			Double diffDays = getProportionalDays(conn, startPeriod, endPeriod);

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
			
			out.add(holidayDevelop);

			index++;
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

		/**
		 * <code>
CREATE FUNCTION fGetProportionalDays(vFrom DATE, vTo DATE) RETURNS DOUBLE
BEGIN
	DECLARE vOut DOUBLE DEFAULT 0;
	DECLARE vDaysForYear, vDaysDiff INTEGER DEFAULT 15;
	DECLARE vDaysOfYear INTEGER DEFAULT 364;
	
	SELECT	cValue
	INTO	vDaysForYear
	FROM	tParameter
	WHERE	cKey = 'DAYS_FOR_YEAR';	
	
	SET		vDaysDiff = DateDiff(vTo, vFrom);
	
	IF(YEAR(vFrom) = YEAR(vTo)) THEN
		IF(YEAR(vFrom) MOD 4 = 0) THEN
			SET vDaysOfYear = 365;
		END IF;
	END IF;
	
	SET	vOut = (vDaysDiff * vDaysForYear) / vDaysOfYear;
	
	RETURN vOut;
END$$ 
		 </code>
		 */

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
		/**
		 * System.out.println("The 21st century (up to " + today + ") is " +
		 * (diff / (1000 * 60 * 60 * 24)) + " days old.");
		 */
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
		/**
		 * <code>
	CREATE FUNCTION fGetProgresiveDays(vMonthsQuoted INTEGER, vCurrentYear INTEGER, vStartYear INTEGER, vProgresive INTEGER) RETURNS DOUBLE
	BEGIN
		DECLARE vOut DOUBLE DEFAULT '0';
		DECLARE vContracted INTEGER DEFAULT '0';
		
		SET vOut = vProgresive; 
		
		IF(vMonthsQuoted >= 120) THEN
			SET vContracted = vCurrentYear - vStartYear;
			
			IF(vContracted >= 3) THEN
				IF(vContracted MOD 3 = 0) THEN
					SET vOut = vProgresive + 1;
				END IF;
			END IF;
			
		END IF;

		RETURN vOut;
	END$$
</code>
		 */

	}
}
