package cl.buildersoft.business.beans;

import cl.buildersoft.framework.beans.BSBean;

public class DocumentType extends BSBean {
	private static final long serialVersionUID = -2008560612054705505L;
	private String TABLE = "tDocumentType";
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
