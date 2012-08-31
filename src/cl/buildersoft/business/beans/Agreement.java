package cl.buildersoft.business.beans;

import java.util.Date;

import cl.buildersoft.framework.beans.BSBean;

public class Agreement extends BSBean {
	private static final long serialVersionUID = -5201220655674962338L;
	private String TABLE = "tAgreement";
	private Long employee = null;
	private Long contractType = null;
	private Date startContract = null;
	private Date endContract = null;
	private Long profile = null;
	private Double salaryRoot = null;
	private Long pfm = null;
	private Integer monthsQuoted = null;
	private Long health = null;
	private Long gratificationType = null;
	private Long paymentType = null;

	private Long bank = null;
	private Long accountType = null;
	private String accountNumber = null;
	private Long currencyAccount2 = null;
	private Double amountAccount2 = null;

	private Long horary = null;
	private Double mobilization = null;
	private Double feeding = null;
	private Long exBoxSystem = null;
	private Double exBoxSystemPRC = null;
	private Double healthAmount = null;
	private Long healthCurrency = null;

	private Double additionalPFMAmount = null;
	private Long additionalPFMCurrency = null;

	private Integer simpleLoads = null;
	private Integer disabilityBurdens = null;
	private Integer maternalLoads = null;

	private Long familyAssignmentStretch = null;
	private Boolean pensionary = null;

	public Long getEmployee() {
		return employee;
	}

	public void setEmployee(Long employee) {
		this.employee = employee;
	}

	public Long getContractType() {
		return contractType;
	}

	public void setContractType(Long contractType) {
		this.contractType = contractType;
	}

	public Date getStartContract() {
		return startContract;
	}

	public void setStartContract(Date startContract) {
		this.startContract = startContract;
	}

	public Date getEndContract() {
		return endContract;
	}

	public void setEndContract(Date endContract) {
		this.endContract = endContract;
	}

	public Long getProfile() {
		return profile;
	}

	public void setProfile(Long profile) {
		this.profile = profile;
	}

	public Double getSalaryRoot() {
		return salaryRoot;
	}

	public void setSalaryRoot(Double salaryRoot) {
		this.salaryRoot = salaryRoot;
	}

	public Long getPfm() {
		return pfm;
	}

	public void setPfm(Long pfm) {
		this.pfm = pfm;
	}

	public Long getHealth() {
		return health;
	}

	public void setHealth(Long health) {
		this.health = health;
	}

	public Long getGratificationType() {
		return gratificationType;
	}

	public void setGratificationType(Long gratificationType) {
		this.gratificationType = gratificationType;
	}

	public Long getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(Long paymentType) {
		this.paymentType = paymentType;
	}

	public Long getHorary() {
		return horary;
	}

	public void setHorary(Long horary) {
		this.horary = horary;
	}

	public Double getMobilization() {
		return mobilization;
	}

	public void setMobilization(Double mobilization) {
		this.mobilization = mobilization;
	}

	public Double getFeeding() {
		return feeding;
	}

	public void setFeeding(Double feeding) {
		this.feeding = feeding;
	}

	public Long getExBoxSystem() {
		return exBoxSystem;
	}

	public void setExBoxSystem(Long exBoxSystem) {
		this.exBoxSystem = exBoxSystem;
	}

	public Double getExBoxSystemPRC() {
		return exBoxSystemPRC;
	}

	public void setExBoxSystemPRC(Double exBoxSystemPRC) {
		this.exBoxSystemPRC = exBoxSystemPRC;
	}

	public Integer getSimpleLoads() {
		return simpleLoads;
	}

	public void setSimpleLoads(Integer simpleLoads) {
		this.simpleLoads = simpleLoads;
	}

	public Integer getDisabilityBurdens() {
		return disabilityBurdens;
	}

	public void setDisabilityBurdens(Integer disabilityBurdens) {
		this.disabilityBurdens = disabilityBurdens;
	}

	public Integer getMaternalLoads() {
		return maternalLoads;
	}

	public void setMaternalLoads(Integer maternalLoads) {
		this.maternalLoads = maternalLoads;
	}

	public Long getBank() {
		return bank;
	}

	public void setBank(Long bank) {
		this.bank = bank;
	}

	public Long getAccountType() {
		return accountType;
	}

	public void setAccountType(Long accountType) {
		this.accountType = accountType;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Long getCurrencyAccount2() {
		return currencyAccount2;
	}

	public void setCurrencyAccount2(Long currencyAccount2) {
		this.currencyAccount2 = currencyAccount2;
	}

	public Double getAmountAccount2() {
		return amountAccount2;
	}

	public void setAmountAccount2(Double amountAccount2) {
		this.amountAccount2 = amountAccount2;
	}

	public Boolean getPensionary() {
		return pensionary;
	}

	public void setPensionary(Boolean pensionary) {
		this.pensionary = pensionary;
	}

	public Long getFamilyAssignmentStretch() {
		return familyAssignmentStretch;
	}

	public void setFamilyAssignmentStretch(Long familyAssignmentStretch) {
		this.familyAssignmentStretch = familyAssignmentStretch;
	}

	public Double getHealthAmount() {
		return healthAmount;
	}

	public void setHealthAmount(Double healthAmount) {
		this.healthAmount = healthAmount;
	}

	public Long getHealthCurrency() {
		return healthCurrency;
	}

	public void setHealthCurrency(Long healthCurrency) {
		this.healthCurrency = healthCurrency;
	}

	@Override
	public String toString() {
		return "Agreement [employee=" + employee + ", contractType=" + contractType + ", startContract=" + startContract
				+ ", endContract=" + endContract + ", profile=" + profile + ", salaryRoot=" + salaryRoot + ", pfm=" + pfm
				+ ", health=" + health + ", gratificationType=" + gratificationType + ", paymentType=" + paymentType + ", bank="
				+ bank + ", accountType=" + accountType + ", accountNumber=" + accountNumber + ", currencyAccount2="
				+ currencyAccount2 + ", amountAccount2=" + amountAccount2 + ", horary=" + horary + ", mobilization="
				+ mobilization + ", feeding=" + feeding + ", exBoxSystem=" + exBoxSystem + ", exBoxSystemPRC=" + exBoxSystemPRC
				+ ", healthAmount=" + healthAmount + ", healthCurrency=" + healthCurrency + ", simpleLoads=" + simpleLoads
				+ ", disabilityBurdens=" + disabilityBurdens + ", maternalLoads=" + maternalLoads + ", familyAssignmentStretch="
				+ familyAssignmentStretch + ", pensionary=" + pensionary + "]";
	}

	public Long getAdditionalPFMCurrency() {
		return additionalPFMCurrency;
	}

	public void setAdditionalPFMCurrency(Long additionalPFMCurrency) {
		this.additionalPFMCurrency = additionalPFMCurrency;
	}

	public Double getAdditionalPFMAmount() {
		return additionalPFMAmount;
	}

	public void setAdditionalPFMAmount(Double additionalPFMAmount) {
		this.additionalPFMAmount = additionalPFMAmount;
	}

	public Integer getMonthsQuoted() {
		return monthsQuoted;
	}

	public void setMonthsQuoted(Integer monthsQuoted) {
		this.monthsQuoted = monthsQuoted;
	}

}
