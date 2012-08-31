package cl.buildersoft.business.beans;

import cl.buildersoft.framework.beans.BSBean;

public class ContractType extends BSBean {
	private static final long serialVersionUID = -4586243792442002702L;
	private String TABLE = "tContractType";
	private String key = null;
	private String name = null;
	private Double insuranceFactorEmployee = null;
	private Double insuranceFactorEnterprise = null;
	private String body = null;
	
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
	public Double getInsuranceFactorEmployee() {
		return insuranceFactorEmployee;
	}
	public void setInsuranceFactorEmployee(Double insuranceFactorEmployee) {
		this.insuranceFactorEmployee = insuranceFactorEmployee;
	}
	public Double getInsuranceFactorEnterprise() {
		return insuranceFactorEnterprise;
	}
	public void setInsuranceFactorEnterprise(Double insuranceFactorEnterprise) {
		this.insuranceFactorEnterprise = insuranceFactorEnterprise;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}

}
