package cl.buildersoft.business.beans;

import java.util.Date;

import cl.buildersoft.framework.beans.BSBean;

public class Period extends BSBean {
	private static final long serialVersionUID = 3169200450775104460L;
	private String TABLE = "tPeriod";
	private Date date = null;
	private Long periodStatus = null;
	private Double uf = null;
	private Double overtimeFactor = null;
	private Double minSalary = null;
	private Double limitGratification = null;
	private Double gratificationFactor = null;
	private Double limitIPS = null;
	private Double limitInsurance = null;
	private Double limitHealth = null;
	private Double utm = null;
	private Integer daysForYear = null;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Long getPeriodStatus() {
		return periodStatus;
	}
	public void setPeriodStatus(Long periodStatus) {
		this.periodStatus = periodStatus;
	}
	public Double getUf() {
		return uf;
	}
	public void setUf(Double uf) {
		this.uf = uf;
	}
	public Double getOvertimeFactor() {
		return overtimeFactor;
	}
	public void setOvertimeFactor(Double overtimeFactor) {
		this.overtimeFactor = overtimeFactor;
	}
	public Double getMinSalary() {
		return minSalary;
	}
	public void setMinSalary(Double minSalary) {
		this.minSalary = minSalary;
	}
	public Double getLimitGratification() {
		return limitGratification;
	}
	public void setLimitGratification(Double limitGratification) {
		this.limitGratification = limitGratification;
	}
	public Double getGratificationFactor() {
		return gratificationFactor;
	}
	public void setGratificationFactor(Double gratificationFactor) {
		this.gratificationFactor = gratificationFactor;
	}
	public Double getLimitIPS() {
		return limitIPS;
	}
	public void setLimitIPS(Double limitIPS) {
		this.limitIPS = limitIPS;
	}
	public Double getLimitInsurance() {
		return limitInsurance;
	}
	public void setLimitInsurance(Double limitInsurance) {
		this.limitInsurance = limitInsurance;
	}
	public Double getLimitHealth() {
		return limitHealth;
	}
	public void setLimitHealth(Double limitHealth) {
		this.limitHealth = limitHealth;
	}
	public Double getUtm() {
		return utm;
	}
	public void setUtm(Double utm) {
		this.utm = utm;
	}
	public Integer getDaysForYear() {
		return daysForYear;
	}
	public void setDaysForYear(Integer daysForYear) {
		this.daysForYear = daysForYear;
	}
	@Override
	public String toString() {
		return "Period [TABLE=" + TABLE + ", date=" + date + ", periodStatus=" + periodStatus + ", uf=" + uf
				+ ", overtimeFactor=" + overtimeFactor + ", minSalary=" + minSalary + ", limitGratification="
				+ limitGratification + ", gratificationFactor=" + gratificationFactor + ", limitIPS=" + limitIPS
				+ ", limitInsurance=" + limitInsurance + ", limitHealth=" + limitHealth + ", utm=" + utm + ", daysForYear="
				+ daysForYear + "]";
	}

}
