package cl.buildersoft.business.beans;

import cl.buildersoft.framework.beans.BSBean;

public class Branch extends BSBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5172927823502989900L;
	private String TABLE="tBranch";
	private String name = null;
	private String enterprise = null;
	private String address = null;
	private String comuna = null;
	private String phone = null;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEnterprise() {
		return enterprise;
	}
	public void setEnterprise(String enterprise) {
		this.enterprise = enterprise;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getComuna() {
		return comuna;
	}
	public void setComuna(String comuna) {
		this.comuna = comuna;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

}
