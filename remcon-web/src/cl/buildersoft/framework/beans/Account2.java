package cl.buildersoft.framework.beans;

public class Account2 extends BSBean {
	private static final long serialVersionUID = 6359929311399955184L;
	private String TABLE = "tAccount";
	private Long employee = null;
	private Long institution = null;
	private Long accountType = null;
	private String number = null;
	private Long currency = null;
	private Double amount = null;

	public Long getEmployee() {
		return employee;
	}

	public void setEmployee(Long employee) {
		this.employee = employee;
	}

	public Long getInstitution() {
		return institution;
	}

	public void setInstitution(Long institution) {
		this.institution = institution;
	}

	public Long getAccountType() {
		return accountType;
	}

	public void setAccountType(Long accountType) {
		this.accountType = accountType;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
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
		return "Account2 [employee=" + employee + ", institution="
				+ institution + ", accountType=" + accountType + ", number="
				+ number + ", currency=" + currency + ", amount=" + amount
				+ "]";
	}
}
