package cl.buildersoft.business.beans;

import cl.buildersoft.framework.beans.BSBean;

public class VoucherType extends BSBean {
	private static final long serialVersionUID = -6432641751070079354L;
	private String TABLE = "tVoucherType";
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
		return "VoucherType [key=" + key + ", name=" + name + "]";
	}
}
