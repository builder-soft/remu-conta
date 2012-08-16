package cl.buildersoft.business.service;

import java.sql.Connection;
import java.util.List;

import cl.buildersoft.business.beans.HolidayDevelop;
import cl.buildersoft.framework.exception.BSException;

public interface HolidayService {
	public List<HolidayDevelop> listDevelop(Connection conn, Long employee ) throws BSException;
	
}
