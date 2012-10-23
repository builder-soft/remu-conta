package cl.buildersoft.business.beans;

import cl.buildersoft.framework.beans.BSBean;

public class Enterprise extends BSBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5184337577166159157L;
	private String TABLE="tEnterprise";
	private String rut = null;
	private String name = null;
	private String legalrep = null;
	private String rutlegalrep = null;
	private String category = null;
	private String address = null;
	private String comuna = null;
	private String phone = null;
	private String mutual = null;
	private String mutualfactor = null;
	private String compensationfund = null;
	
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLegalrep() {
		return legalrep;
	}
	public void setLegalrep(String legalrep) {
		this.legalrep = legalrep;
	}
	public String getRutlegalrep() {
		return rutlegalrep;
	}
	public void setRutlegalrep(String rutlegalrep) {
		this.rutlegalrep = rutlegalrep;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
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
	public String getMutual() {
		return mutual;
	}
	public void setMutual(String mutual) {
		this.mutual = mutual;
	}
	public String getMutualfactor() {
		return mutualfactor;
	}
	public void setMutualfactor(String mutualfactor) {
		this.mutualfactor = mutualfactor;
	}
	public String getCompensationfund() {
		return compensationfund;
	}
	public void setCompensationfund(String compensationfund) {
		this.compensationfund = compensationfund;
	}


}
