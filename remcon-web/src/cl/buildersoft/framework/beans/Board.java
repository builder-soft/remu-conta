package cl.buildersoft.framework.beans;

public class Board extends BSBean {
	private static final long serialVersionUID = -2425456949675383733L;
	private String TABLE = "tBoard";
	private String type = null;
	private String key = null;
	private String value = null;
	private Boolean enable = null;
	private Integer order = null;
	private Boolean byDefault = null;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	@Override
	public String toString() {
		return "Board [type=" + type + ", key=" + key + ", value=" + value
				+ ", enable=" + enable + "]";
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Boolean getByDefault() {
		return byDefault;
	}

	public void setByDefault(Boolean byDefault) {
		this.byDefault = byDefault;
	}

}
