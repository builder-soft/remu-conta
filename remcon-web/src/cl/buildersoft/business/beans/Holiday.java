package cl.buildersoft.business.beans;

import java.util.Date;

import cl.buildersoft.framework.beans.BSBean;

public class Holiday extends BSBean {
	private static final long serialVersionUID = 4180166895306245829L;
	private String TABLE = "tHoliday";
	private Long employee = null;
	private Date from = null;
	private Date to = null;

	public Long getEmployee() {
		return employee;
	}

	public void setEmployee(Long employee) {
		this.employee = employee;
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}
}
