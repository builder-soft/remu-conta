package cl.buildersoft.business.beans;

import cl.buildersoft.framework.beans.BSBean;

public class HolidayDetail extends BSBean {
	private static final long serialVersionUID = -1132416724203404810L;
	private String TABLE = "tHolidayDetail";
	private Long holiday = null;
	private Double days = null;
	private Integer year = null;
	private Long holidayDetailType = null;
	
	public Long getHoliday() {
		return holiday;
	}
	public void setHoliday(Long holiday) {
		this.holiday = holiday;
	}
	public Double getDays() {
		return days;
	}
	public void setDays(Double days) {
		this.days = days;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Long getHolidayDetailType() {
		return holidayDetailType;
	}
	public void setHolidayDetailType(Long holidayDetailType) {
		this.holidayDetailType = holidayDetailType;
	}

}
