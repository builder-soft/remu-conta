package cl.buildersoft.business.beans;

import java.math.BigDecimal;

import cl.buildersoft.framework.beans.BSBean;

public class VoucherDetail extends BSBean {
	private static final long serialVersionUID = 7163321241903717624L;
	private String TABLE = "tVoucherDetail";
	private Long voucher = null;
	private String rut = null;
	private Long documentType = null;
	private Integer documentNumber = null;
	private BigDecimal netAmount = null;
	private BigDecimal tax = null;
	private Long costCenter = null;
	private Long chartAccount = null;

	public Long getVoucher() {
		return voucher;
	}

	public void setVoucher(Long voucher) {
		this.voucher = voucher;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
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

	public BigDecimal getTax() {
		return tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public Long getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(Long costCenter) {
		this.costCenter = costCenter;
	}

	public Long getChartAccount() {
		return chartAccount;
	}

	public void setChartAccount(Long chartAccount) {
		this.chartAccount = chartAccount;
	}

	@Override
	public String toString() {
		return "VoucherDetail [voucher=" + voucher + ", rut=" + rut + ", documentType=" + documentType + ", documentNumber="
				+ documentNumber + ", netAmount=" + netAmount + ", tax=" + tax + ", costCenter=" + costCenter + ", chartAccount="
				+ chartAccount + "]";
	}
}
