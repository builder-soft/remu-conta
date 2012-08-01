package cl.buildersoft.business.beans;

import cl.buildersoft.framework.beans.BSBean;

public class LicenseCause extends BSBean {
	private static final long serialVersionUID = 7719069412369373610L;
	private String TABLE = "tLicenseCause";
	private String key = null;
	private String name = null;
	private Long fileCategory = null;

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

	public Long getFileCategory() {
		return fileCategory;
	}

	public void setFileCategory(Long fileCategory) {
		this.fileCategory = fileCategory;
	}

	@Override
	public String toString() {
		return "LicenseCause [key=" + key + ", name=" + name + ", fileCategory=" + fileCategory + "]";
	}
}
