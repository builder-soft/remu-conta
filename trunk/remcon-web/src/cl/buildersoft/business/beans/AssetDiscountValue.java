package cl.buildersoft.business.beans;

import cl.buildersoft.framework.beans.BSBean;

public class AssetDiscountValue extends BSBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8549221650900239177L;
	private String TABLE="tAssetDiscountValue";
	private String assetdiscount = null;
	private String book = null;
	private String value = null;
	public String getAssetdiscount() {
		return assetdiscount;
	}
	public void setAssetdiscount(String assetdiscount) {
		this.assetdiscount = assetdiscount;
	}
	public String getBook() {
		return book;
	}
	public void setBook(String book) {
		this.book = book;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

}
