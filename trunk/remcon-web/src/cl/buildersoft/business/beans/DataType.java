package cl.buildersoft.business.beans;

import cl.buildersoft.framework.beans.BSBean;

public class DataType extends BSBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -800013110824424952L;
	private String TABLE="tDataType";
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

}
