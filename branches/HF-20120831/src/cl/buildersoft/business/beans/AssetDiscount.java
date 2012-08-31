package cl.buildersoft.business.beans;

import cl.buildersoft.framework.beans.BSBean;

public class AssetDiscount extends BSBean {
	private static final long serialVersionUID = -862651109424938148L;
	private String TABLE = "tAssetDiscount";

	private String name = null;
	private Boolean enable = null;
	private Double limit = null;
	private Integer order = null;
	private Integer index = null;
	private Long assetDiscountType = null;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getEnable() {
		return enable;
	}
	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
	public Double getLimit() {
		return limit;
	}
	public void setLimit(Double limit) {
		this.limit = limit;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public Long getAssetDiscountType() {
		return assetDiscountType;
	}
	public void setAssetDiscountType(Long assetDiscountType) {
		this.assetDiscountType = assetDiscountType;
	}
	
}
