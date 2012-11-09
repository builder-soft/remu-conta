package cl.buildersoft.business.beans;

import java.math.BigDecimal;

import cl.buildersoft.framework.beans.BSBean;

public class VoucherDetail extends BSBean {
	private static final long serialVersionUID = 7163321241903717624L;
	private String TABLE = "tVoucherDetail";
	private Long voucher = null;
	private Long chartAccount = null;
	private Long documentType = null;
	private Integer documentNumber = null;
	private BigDecimal netAmount = null;
	private Long costCenter = null;
	
	
	public Long getVoucher() {
		return voucher;
	}
	public void setVoucher(Long voucher) {
		this.voucher = voucher;
	}
	public Long getChartAccount() {
		return chartAccount;
	}
	public void setChartAccount(Long chartAccount) {
		this.chartAccount = chartAccount;
	}
	public Long getDocumentType() {
		return documentType;
	}
	public void setDocumentType(Long documentType) {
		this.documentType = documentType;
	}
	public Integer getDocumentNumber() {
		return documentNumber;
	}
	public void setDocumentNumber(Integer documentNumber) {
		this.documentNumber = documentNumber;
	}
	public BigDecimal getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(BigDecimal netAmount) {
		this.netAmount = netAmount;
	}
	public Long getCostCenter() {
		return costCenter;
	}
	public void setCostCenter(Long costCenter) {
		this.costCenter = costCenter;
	}
	@Override
	public String toString() {
		return "VoucherDetail [voucher=" + voucher + ", chartAccount=" + chartAccount + ", documentType=" + documentType
				+ ", documentNumber=" + documentNumber + ", netAmount=" + netAmount + ", costCenter=" + costCenter + ", getId()="
				+ getId() + "]";
	}
	
 
}
