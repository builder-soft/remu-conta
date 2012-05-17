package cl.buildersoft.framework.beans;

public class PaymentType extends BSBean {
	private static final long serialVersionUID = -4560608021242821822L;
	private String TABLE = "tPaymentType";
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
		return "PaymentType [key=" + key + ", name=" + name + "]";
	}

}
