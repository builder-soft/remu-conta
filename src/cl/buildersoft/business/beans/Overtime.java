package cl.buildersoft.business.beans;

import java.util.Date;

import cl.buildersoft.framework.beans.BSBean;

public class Overtime extends BSBean {
	private static final long serialVersionUID = 1996373562461461379L;
	private String TABLE = "tOvertime";
	private Long employee = null;
	private Long period = null;
	private Date date = null;
	private Integer percent = null;
	private Integer amount = null;

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getPercent() {
		return percent;
	}

	public void setPercent(Integer percent) {
		this.percent = percent;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Overtime [employee=" + employee + ", period=" + period + ", date=" + date + ", percent=" + percent + ", amount="
				+ amount + "]";
	}

}
