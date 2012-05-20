package cl.buildersoft.framework.beans;

public class PFM extends BSBean {
	private static final long serialVersionUID = 3712344053087746491L;
	private String TABLE = "tPFM";
	private String key = null;
	private String name = null;
	private Double factor = null;
	private Double sis = null;

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

	public Double getSis() {
		return this.sis;
	}

	public void setSis(Double sis) {
		this.sis = sis;
	}

	@Override
	public String toString() {
		return "PFM [key=" + key + ", name=" + name + ", factor=" + factor
				+ ", SIS=" + sis + "]";
	}
}
