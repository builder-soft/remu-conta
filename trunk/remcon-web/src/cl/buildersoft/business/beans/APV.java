package cl.buildersoft.business.beans;

import cl.buildersoft.framework.beans.BSBean;

public class APV extends BSBean {
	private static final long serialVersionUID = -7998293980754094636L;
	private String TABLE = "tAPV";
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
		return "APV [key=" + key + ", name=" + name + "]";
	}
}
