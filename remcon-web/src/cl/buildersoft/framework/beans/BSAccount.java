package cl.buildersoft.framework.beans;

public class BSAccount extends BSBean{
	private String key = null;
	private String name = null;
	private String value = null;
	private String cKeyInstitution = null;
	private String cKeyCurrency = null;
	private String cIdCurrency = null;
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
	public String getcKeyInstitution() {
		return cKeyInstitution;
	}
	public void setcKeyInstitution(String cKeyInstitution) {
		this.cKeyInstitution = cKeyInstitution;
	}
	public String getcKeyCurrency() {
		return cKeyCurrency;
	}
	public void setcKeyCurrency(String cKeyCurrency) {
		this.cKeyCurrency = cKeyCurrency;
	}
	public String getcIdCurrency() {
		return cIdCurrency;
	}
	public void setcIdCurrency(String cIdCurrency) {
		this.cIdCurrency = cIdCurrency;
	}
	
}
