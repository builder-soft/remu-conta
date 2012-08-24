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
import cl.buildersoft.business.beans.Holiday;
import cl.buildersoft.business.beans.HolidayDetail;
import cl.buildersoft.business.beans.HolidayDevelop;
import cl.buildersoft.business.service.AgreementService;
import cl.buildersoft.business.service.HolidayService;
import cl.buildersoft.framework.database.BSBeanUtils;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.exception.BSException;
import cl.buildersoft.framework.exception.BSProgrammerException;
import cl.buildersoft.framework.util.BSConfig;
import cl.buildersoft.framework.util.BSDateTimeUtil;
import cl.buildersoft.framework.util.BSUtils;

public class HolidayServiceImpl implements HolidayService {
	private static final int MILLISECONDS_ON_DAY = 1000 * 60 * 60 * 24;

	@Override
	public Holiday getHoliday(Connection conn, Long id) {
		BSBeanUtils bu = new BSBeanUtils();
		Holiday holiday = new Holiday();
		holiday.setId(id);
		bu.search(conn, holiday);
		return holiday;
	}

	@Override
	public void commitHoliday(Connection conn, Long employeeId, Date from, Integer normal, Integer creeping) {
		Holiday holiday = new Holiday();
		holiday.setCreation(new Date());
		holiday.setEmployee(employeeId);
		holiday.setFrom(from);
		holiday.setNormal(normal);
		holiday.setCreeping(creeping);

		BSBeanUtils bu = new BSBeanUtils();

		List<HolidayDevelop> preCommit = listDevelop(conn, holiday.getEmployee());
		// showDevelop(preCommit);

		bu.save(conn, holiday);

		List<HolidayDevelop> postCommit = listDevelop(conn, holiday.getEmployee());
		// showDevelop(postCommit);

		saveDetail(conn, preCommit, postCommit, holiday);

	}

	/**
	 * <code>
	 
	private void showDevelop(List<HolidayDevelop> preDevelopList) {
		Integer index = 0;
		Integer size = preDevelopList.size();
		for (index = 0; index < size; index++) {
			System.out.println(preDevelopList.get(index).toString());
		}
	}
	 * </code>
	 */

	private void saveDetail(Connection conn, List<HolidayDevelop> preDevelopList, List<HolidayDevelop> postDevelopList,
			Holiday holiday) {
//		System.out.println(holiday);
		Integer index = 0;
		Integer size = preDevelopList.size();

		HolidayDevelop pre = null;
		HolidayDevelop post = null;

		HolidayDetail holidayDetail = null;
		BSBeanUtils bu = new BSBeanUtils();
		Double days = 0D;
		Boolean doSave;

		for (index = 0; index < size; index++) {
			doSave = true;
			pre = preDevelopList.get(index);
			post = postDevelopList.get(index);

			holidayDetail = new HolidayDetail();

			if (ignored(pre, post)) {
				doSave = false;
			} else {
				holidayDetail.setHoliday(holiday.getId());
				holidayDetail.setYear(pre.getYear());
				if (discountedTaken(pre, post)) {
					// Save as taken
					holidayDetail.setHolidayDetailType(1L);
					Double normalTaken = post.getNormalTaken() - pre.getNormalTaken();
					Double creepingTaken = post.getCreepingTaken() - pre.getCreepingTaken();
					days = normalTaken + creepingTaken;
					holidayDetail.setDays(days);

				} else {
					// Save as balance
					holidayDetail.setHolidayDetailType(2L);
					days = post.getNormalRatio() + post.getCreepingRatio();
					holidayDetail.setDays(days);
				}
			}

			if (doSave) {
				bu.save(conn, holidayDetail);
			}
		}
	}

	private boolean ignored(HolidayDevelop pre, HolidayDevelop post) {
		return pre.getTotalBalance().equals(0D) && post.getTotalBalance().equals(0D);
	}

	private boolean discountedTaken(HolidayDevelop pre, HolidayDevelop post) {
		Double preTaken = pre.getNormalTaken() + pre.getCreepingTaken();
		Double postTaken = post.getNormalTaken() + post.getCreepingTaken();
		return !preTaken.equals(postTaken);
	}

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

		Integer year = startYear;
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

		// Valida que si se puede descontar.
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

		// Valida que si se puede descontar.
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
		AgreementService as = new AgreementServiceImpl();
		return as.getEndContract(agreement);
	}

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

	@Override
	public Date getEndDate(Connection conn, Long holidayId) {
		Holiday holiday = new Holiday();
		BSBeanUtils bu = new BSBeanUtils();
		holiday.setId(holidayId);
		bu.search(conn, holiday);
		return getEndDate(conn, holiday);
	}

	@Override
	public Date getEndDate(Connection conn, Holiday holiday) {
		BSmySQL mysql = new BSmySQL();
		List<Object> params = BSUtils.array2List(holiday.getFrom(), holiday.getNormal() + holiday.getCreeping());
		String to = mysql.callFunction(conn, "fBusinessDate", params);
		return BSDateTimeUtil.calendar2Date(BSDateTimeUtil.string2Calendar(to, BSDateTimeUtil.SQL_FORMAT));
	}

	@Override
	public List<HolidayDetail> getHolidayDetail(Connection conn, Long holidayId) {
		BSBeanUtils bu = new BSBeanUtils();
		return (List<HolidayDetail>) bu.list(conn, new HolidayDetail(), "cHoliday=?", holidayId);
	}
}
