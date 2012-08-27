package cl.buildersoft.business.beans;

import cl.buildersoft.framework.beans.BSBean;

public class GratificationType extends BSBean {
	private static final long serialVersionUID = 3874374388821483765L;
	private String TABLE = "tGratificationType";
	private String name = null;
	private String polynomial = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPolynomial() {
		return polynomial;
	}

	public void setPolynomial(String polynomial) {
		this.polynomial = polynomial;
	}

	@Override
	public String toString() {
		return "GratificationType [name=" + name + ", polynomial=" + polynomial
				+ "]";
	}

}
