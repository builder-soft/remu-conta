package cl.buildersoft.framework.beans;

public class Bank extends BSBean {
	private static final long serialVersionUID = -8415739759902769313L;
	private String TABLE = "tBank";
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
		return "Bank [key=" + key + ", name=" + name + "]";
	}

}
