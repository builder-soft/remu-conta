package cl.buildersoft.framework.beans;

public class AccountType extends BSBean {
	private static final long serialVersionUID = -962677999789237378L;
	private String TABLE = "tAccountType";
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
		return "AccountType [key=" + key + ", name=" + name + "]";
	}

}
