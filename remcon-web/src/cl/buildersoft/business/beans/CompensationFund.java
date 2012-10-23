package cl.buildersoft.business.beans;

import cl.buildersoft.framework.beans.BSBean;

public class CompensationFund extends BSBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6071838513938754075L;
	private String TABLE="tCompensationFund";
	private String name = null;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
