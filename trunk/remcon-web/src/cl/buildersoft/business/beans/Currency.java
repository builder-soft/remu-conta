package cl.buildersoft.business.beans;

import cl.buildersoft.framework.beans.BSBean;

public class Currency extends BSBean {
	private static final long serialVersionUID = -6575629925342560802L;
	private String TABLE = "tCurrency";
	private String key = null;
	private String name = null;

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

	@Override
	public String toString() {
		return "Currency [key=" + key + ", name=" + name + "]";
	}

}
