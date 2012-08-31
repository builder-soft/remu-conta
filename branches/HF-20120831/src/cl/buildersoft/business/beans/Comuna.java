package cl.buildersoft.business.beans;

import cl.buildersoft.framework.beans.BSBean;

public class Comuna extends BSBean {
	private static final long serialVersionUID = -7282204912910560105L;
	private String TABLE = "tComuna";
	private String name = null;
	private Integer region = null;
	private Integer priority = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRegion() {
		return region;
	}

	public void setRegion(Integer region) {
		this.region = region;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	@Override
	public String toString() {
		return "Comuna [name=" + name + ", region=" + region + ", priority=" + priority + "]";
	}

}
