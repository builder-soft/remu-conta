package cl.buildersoft.business.beans;

import cl.buildersoft.framework.beans.BSBean;

public class Horary extends BSBean {
	private String TABLE = "tHorary";
	private String name = null;
	private String detail = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Override
	public String toString() {
		return "Horary [name=" + name + ", detail=" + detail + "]";
	}

}
