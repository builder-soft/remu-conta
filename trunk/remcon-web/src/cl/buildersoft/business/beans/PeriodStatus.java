package cl.buildersoft.business.beans;

import java.util.Date;

import cl.buildersoft.framework.beans.BSBean;

public class PeriodStatus extends BSBean {
	private static final long serialVersionUID = 5448913358959026935L;
	private String TABLE = "tPeriodStatus";
	private String name = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
