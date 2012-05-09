package cl.buildersoft.framework.beans;

public class ContractType extends BSBean {
	private static final long serialVersionUID = -4586243792442002702L;
	private String TABLE = "tContractType";
	private String name = null;
	private String body = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "ContractType [name=" + name + ", body=" + body + "]";
	}
}
