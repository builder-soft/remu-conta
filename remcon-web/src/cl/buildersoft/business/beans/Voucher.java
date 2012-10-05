package cl.buildersoft.business.beans;

import java.util.Date;

import cl.buildersoft.framework.beans.BSBean;

public class Voucher extends BSBean {
	private static final long serialVersionUID = -4279691711828692252L;
	private String TABLE = "tVoucher";
	private Long voucherType = null;
	private Long voucherStatus = null;
	private Date accountingDate = null;
	private Integer number = null;
	private Long user = null;
	
	public Long getcVoucherType() {
		return voucherType;
	}
	public void setcVoucherType(Long cVoucherType) {
		this.voucherType = cVoucherType;
	}
	public Long getcVoucherStatus() {
		return voucherStatus;
	}
	public void setcVoucherStatus(Long cVoucherStatus) {
		this.voucherStatus = cVoucherStatus;
	}
	public Date getcAccountingDate() {
		return accountingDate;
	}
	public void setcAccountingDate(Date cAccountingDate) {
		this.accountingDate = cAccountingDate;
	}
	public Integer getcNumber() {
		return number;
	}
	public void setcNumber(Integer cNumber) {
		this.number = cNumber;
	}
	public Long getcUser() {
		return user;
	}
	public void setcUser(Long cUser) {
		this.user = cUser;
	}
	@Override
	public String toString() {
		return "Voucher [cVoucherType=" + voucherType + ", cVoucherStatus=" + voucherStatus + ", cAccountingDate="
				+ accountingDate + ", cNumber=" + number + ", cUser=" + user + "]";
	}
	 
}
