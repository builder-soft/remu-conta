package cl.buildersoft.business.beans;

import cl.buildersoft.framework.beans.BSBean;

public class ChartAccount extends BSBean {
	private static final long serialVersionUID = -4286012013034598128L;
	private String TABLE = "tChartAccount";
	private String key = null;
	private String name = null;
	private Long parent = null;
	private Integer level = null;
	private Boolean enable = null;

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

	public Long getParent() {
		return parent;
	}

	public void setParent(Long parent) {
		this.parent = parent;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	@Override
	public String toString() {
		return "ChartAccount [key=" + key + ", name=" + name + ", parent=" + parent + ", level=" + level + ", enable=" + enable
				+ "]";
	}

}
