package cl.buildersoft.framework.beans;

public class ExBoxSystem extends BSBean {
	private static final long serialVersionUID = 139863602362567698L;
	private String TABLE = "tExBoxSystem";
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
		return "ExBoxSystem [key=" + key + ", name=" + name + "]";
	}

}
