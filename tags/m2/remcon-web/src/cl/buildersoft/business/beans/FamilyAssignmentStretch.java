package cl.buildersoft.business.beans;

import cl.buildersoft.framework.beans.BSBean;

public class FamilyAssignmentStretch extends BSBean {
	private static final long serialVersionUID = -7755642947037923294L;
	private String TABLE = "tFamilyAssignmentStretch ";
	private String key = null;
	private Double amount = null;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

}
