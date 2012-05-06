package cl.buildersoft.framework.beans;

public class BSAccount extends BSBean{
	private String key = null;
	private String name = null;
	private String value = null;
	private String TABLE = "tAccount";
	
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
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getTABLE() {
		return TABLE;
	}
	public void setTABLE(String tABLE) {
		TABLE = tABLE;
	}
}
