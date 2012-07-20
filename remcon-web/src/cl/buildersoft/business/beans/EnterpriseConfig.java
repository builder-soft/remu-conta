package cl.buildersoft.business.beans;

import cl.buildersoft.framework.beans.BSBean;

public class EnterpriseConfig extends BSBean {
	private static final long serialVersionUID = -7998293980754094636L;
	private String TABLE = "tEnterpriseConfig";
	private Long enterprise = null;
	private Boolean showDateUfUtm = null;
	private Boolean showProfile = null;
	private Boolean showCostCenter = null;
	private Boolean showDataAgreement = null;
	private Boolean showSalaryRoot = null;
	private Boolean showEmployerBonus = null;
	private Boolean showWorkDay = null;
	private Boolean showNetPaymentScope = null;
	private String textFootSalary = null;
	private String mailNotice = null;
	private Integer viewLastSettlements = null;
	
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
	public String getMailNotice() {
		return mailNotice;
	}
	public void setMailNotice(String mailNotice) {
		this.mailNotice = mailNotice;
	}
	public Integer getViewLastSettlements() {
		return viewLastSettlements;
	}
	public void setViewLastSettlements(Integer viewLastSettlements) {
		this.viewLastSettlements = viewLastSettlements;
	}
	@Override
	public String toString() {
		return "EnterpriseConfig [enterprise=" + enterprise + ", showDateUfUtm=" + showDateUfUtm + ", showProfile=" + showProfile
				+ ", showCostCenter=" + showCostCenter + ", showDataAgreement=" + showDataAgreement + ", showSalaryRoot="
				+ showSalaryRoot + ", showEmployerBonus=" + showEmployerBonus + ", showWorkDay=" + showWorkDay
				+ ", showNetPaymentScope=" + showNetPaymentScope + ", textFootSalary=" + textFootSalary + ", mailNotice="
				+ mailNotice + ", viewLastSettlements=" + viewLastSettlements + "]";
	}


}