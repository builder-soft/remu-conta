package cl.buildersoft.business.beans;

import cl.buildersoft.framework.beans.BSBean;

public class CostCenter extends BSBean {
	private static final long serialVersionUID = 5455419334818203066L;
	private String TABLE = "tCostCenter";
	private String name = null;
	private Long branch = null;
	private Long businessArea = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getBranch() {
		return branch;
	}

	public void setBranch(Long branch) {
		this.branch = branch;
	}

	public Long getBusinessArea() {
		return businessArea;
	}

	public void setBusinessArea(Long businessArea) {
		this.businessArea = businessArea;
	}

}
