package cl.buildersoft.business.service;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import cl.buildersoft.business.beans.Holiday;
import cl.buildersoft.business.beans.HolidayDetail;
import cl.buildersoft.business.beans.HolidayDevelop;
import cl.buildersoft.framework.exception.BSException;

public interface HolidayService {
	public List<HolidayDevelop> listDevelop(Connection conn, Long employee) throws BSException;

	public void commitHoliday(Connection conn, Long employeeId, Date from, Integer normal, Integer creeping);

	public Holiday getHoliday(Connection conn, Long id);

	public Date getEndDate(Connection conn, Long holiday);

	public Date getEndDate(Connection conn, Holiday holiday);

	public List<HolidayDetail> getHolidayDetail(Connection conn, Long holidayId);

}
