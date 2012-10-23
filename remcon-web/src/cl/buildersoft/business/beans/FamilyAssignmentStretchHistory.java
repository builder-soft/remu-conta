package cl.buildersoft.business.beans;

import cl.buildersoft.framework.beans.BSBean;

public class FamilyAssignmentStretchHistory extends BSBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3304384055564296810L;
	private String TABLE="tFamilyAssignmentStretchHistory";
	private String period = null;
	private String key = null;
	private String amount = null;

	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}

}
