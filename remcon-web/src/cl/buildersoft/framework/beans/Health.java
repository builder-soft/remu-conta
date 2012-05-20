package cl.buildersoft.framework.beans;

public class Health extends BSBean {
	private static final long serialVersionUID = -1094852332277262238L;
	private String TABLE = "tHealth";
	private String key = null;
	private String name = null;
	private Double factor = null;

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

	public Double getFactor() {
		return factor;
	}

	public void setFactor(Double factor) {
		this.factor = factor;
	}

	@Override
	public String toString() {
		return "Health [key=" + key + ", name=" + name + ", factor=" + factor
				+ "]";
	}
}
