package cl.buildersoft.framework.beans;

public class RagreementAPV {
	private Long agreement = null;
	private Long apv = null;
	private Long currency = null;
	private Double amount = null;

	public Long getAgreement() {
		return agreement;
	}

	public void setAgreement(Long agreement) {
		this.agreement = agreement;
	}

	public Long getApv() {
		return apv;
	}

	public void setApv(Long apv) {
		this.apv = apv;
	}

	public Long getCurrency() {
		return currency;
	}

	public void setCurrency(Long currency) {
		this.currency = currency;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "RagreementAPV [Agreement=" + agreement + ", apv=" + apv
				+ ", Currency=" + currency + ", Amount=" + amount + "]";
	}
}
