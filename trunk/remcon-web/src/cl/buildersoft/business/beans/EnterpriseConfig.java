package cl.buildersoft.business.beans;

import cl.buildersoft.framework.beans.BSBean;

public class EnterpriseConfig extends BSBean {

	private static final long serialVersionUID = -7998293980754094636L;
	private String TABLE = "tEnterpriseConfig";
	private Long enterprise;
	private Boolean showDateUfUtm;
	private Boolean showProfile;
	private Boolean showCostCenter;
	private Boolean showDataAgreement;
	private Boolean showSalaryRoot;
	private Boolean showEmployerBonus;
	private Boolean showWorkDay;
	private Boolean showNetPaymentScope;
	private String textFootSalary;
	private String email = "" ;

	public Long getEnterprise() {
		return enterprise;
	}
	public void setEnterprise(Long enterprise) {
		this.enterprise = enterprise;
	}
	public Boolean getShowDateUfUtm() {
		return showDateUfUtm;
	}
	public void setShowDateUfUtm(Boolean showDateUfUtm) {
		this.showDateUfUtm = showDateUfUtm;
	}
	public Boolean getShowProfile() {
		return showProfile;
	}
	public void setShowProfile(Boolean showProfile) {
		this.showProfile = showProfile;
	}
	public Boolean getShowCostCenter() {
		return showCostCenter;
	}
	public void setShowCostCenter(Boolean showCostCenter) {
		this.showCostCenter = showCostCenter;
	}
	public Boolean getShowDataAgreement() {
		return showDataAgreement;
	}
	public void setShowDataAgreement(Boolean showDataAgreement) {
		this.showDataAgreement = showDataAgreement;
	}
	public Boolean getShowSalaryRoot() {
		return showSalaryRoot;
	}
	public void setShowSalaryRoot(Boolean showSalaryRoot) {
		this.showSalaryRoot = showSalaryRoot;
	}
	public Boolean getShowEmployerBonus() {
		return showEmployerBonus;
	}
	public void setShowEmployerBonus(Boolean showEmployerBonus) {
		this.showEmployerBonus = showEmployerBonus;
	}
	public Boolean getShowWorkDay() {
		return showWorkDay;
	}
	public void setShowWorkDay(Boolean showWorkDay) {
		this.showWorkDay = showWorkDay;
	}
	public Boolean getShowNetPaymentScope() {
		return showNetPaymentScope;
	}
	public void setShowNetPaymentScope(Boolean showNetPaymentScope) {
		this.showNetPaymentScope = showNetPaymentScope;
	}
	public String getTextFootSalary() {
		return textFootSalary;
	}
	public void setTextFootSalary(String textFootSalary) {
		this.textFootSalary = textFootSalary;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}