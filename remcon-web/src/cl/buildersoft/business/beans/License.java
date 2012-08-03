package cl.buildersoft.business.beans;

import cl.buildersoft.framework.beans.BSBean;

public class License extends BSBean {
	private static final long serialVersionUID = -6392117713422751650L;
	private String TABLE = "tLicense";
	private Long employee = null;
	private Long period = null;
	private Integer from = null;
	private Integer to = null;
	private Long licenseCause = null;
	private Long file = null;

	public Long getEmployee() {
		return employee;
	}

	public void setEmployee(Long employee) {
		this.employee = employee;
	}

	public Long getPeriod() {
		return period;
	}

	public void setPeriod(Long period) {
		this.period = period;
	}

	public Integer getFrom() {
		return from;
	}

	public void setFrom(Integer from) {
		this.from = from;
	}

	public Integer getTo() {
		return to;
	}

	public void setTo(Integer to) {
		this.to = to;
	}

	public Long getLicenseCause() {
		return licenseCause;
	}

	public void setLicenseCause(Long licenseCause) {
		this.licenseCause = licenseCause;
	}

	public Long getFile() {
		return file;
	}

	public void setFile(Long file) {
		this.file = file;
	}

}
