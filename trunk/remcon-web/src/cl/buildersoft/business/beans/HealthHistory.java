package cl.buildersoft.business.beans;

import cl.buildersoft.framework.beans.BSBean;

public class HealthHistory extends BSBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1108672579899206208L;
	private String TABLE="tHealthHistory";
	private String period = null;
	private String key = null;
	private String name = null;
	private String factor = null;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFactor() {
		return factor;
	}
	public void setFactor(String factor) {
		this.factor = factor;
	}


}
