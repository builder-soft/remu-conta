package cl.buildersoft.business.beans;

import java.sql.Timestamp;
import java.util.Date;

import cl.buildersoft.framework.beans.BSBean;

public class Voucher extends BSBean {
	private static final long serialVersionUID = -4279691711828692252L;
	private String TABLE = "tVoucher";
	private Long voucherType = null;
	private Long voucherStatus = null;
	private Date accountingDate = null;
	private Timestamp creationTime = null;
	private Integer number = null;
	private Long user = null;

	public Long getVoucherType() {
		return voucherType;
	}

	public void setVoucherType(Long voucherType) {
		this.voucherType = voucherType;
	}

	public Long getVoucherStatus() {
		return voucherStatus;
	}

	public void setVoucherStatus(Long voucherStatus) {
		this.voucherStatus = voucherStatus;
	}

	public Date getAccountingDate() {
		return accountingDate;
	}

	public void setAccountingDate(Date accountingDate) {
		this.accountingDate = accountingDate;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Long getUser() {
		return user;
	}

	public void setUser(Long user) {
		this.user = user;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	@Override
	public String toString() {
		return "Voucher [voucherType=" + voucherType + ", voucherStatus=" + voucherStatus + ", accountingDate=" + accountingDate
				+ ", creationTime=" + creationTime + ", number=" + number + ", user=" + user + "]";
	}

}
