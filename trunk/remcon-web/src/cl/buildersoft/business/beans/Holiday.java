package cl.buildersoft.business.beans;

import java.util.Date;

import cl.buildersoft.framework.beans.BSBean;

public class Holiday extends BSBean {
	private static final long serialVersionUID = 4180166895306245829L;
	private String TABLE = "tHoliday";
	private Long employee = null;
	private Date creation = null;
	private Date from = null;
	private Integer normal = null;
	private Integer creeping = null;

	public Long getEmployee() {
		return employee;
	}

	public void setEmployee(Long employee) {
		this.employee = employee;
	}

	public Date getCreation() {
		return creation;
	}

	public void setCreation(Date creation) {
		this.creation = creation;
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Integer getNormal() {
		return normal;
	}

	public void setNormal(Integer normal) {
		this.normal = normal;
	}

	public Integer getCreeping() {
		return creeping;
	}

	public void setCreeping(Integer creeping) {
		this.creeping = creeping;
	}

}
