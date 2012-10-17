package cl.buildersoft.business.beans;

import cl.buildersoft.framework.beans.BSBean;

public class BusinessArea extends BSBean {
	private static final long serialVersionUID = -9068617911550521513L;
	private String TABLE = "tBusinessArea";

	private String name = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
