package cl.buildersoft.business.beans;

public class HolidayDevelop {
	private Long id = null;
	private Integer year = null;
	private Double normalRatio = null;
	private Double normalTaken = null;
	private Double normalBalance = null;
	private Double creepingRatio = null;
	private Double creepingTaken = null;
	private Double creepingBalance = null;
	private Double totalBalance = null;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Double getNormalRatio() {
		return normalRatio;
	}
	public void setNormalRatio(Double normalRatio) {
		this.normalRatio = normalRatio;
	}
	public Double getNormalTaken() {
		return normalTaken;
	}
	public void setNormalTaken(Double normalTaken) {
		this.normalTaken = normalTaken;
	}
	public Double getNormalBalance() {
		return normalBalance;
	}
	public void setNormalBalance(Double normalBalance) {
		this.normalBalance = normalBalance;
	}
	public Double getCreepingRatio() {
		return creepingRatio;
	}
	public void setCreepingRatio(Double creepingRatio) {
		this.creepingRatio = creepingRatio;
	}
	public Double getCreepingTaken() {
		return creepingTaken;
	}
	public void setCreepingTaken(Double creepingTaken) {
		this.creepingTaken = creepingTaken;
	}
	public Double getCreepingBalance() {
		return creepingBalance;
	}
	public void setCreepingBalance(Double creepingBalance) {
		this.creepingBalance = creepingBalance;
	}
	public Double getTotalBalance() {
		return totalBalance;
	}
	public void setTotalBalance(Double totalBalance) {
		this.totalBalance = totalBalance;
	}
	@Override
	public String toString() {
		return "HolidayDevelop [id=" + id + ", year=" + year + ", normalRatio=" + normalRatio + ", normalTaken=" + normalTaken
				+ ", normalBalance=" + normalBalance + ", creepingRatio=" + creepingRatio + ", creepingTaken=" + creepingTaken
				+ ", creepingBalance=" + creepingBalance + ", totalBalance=" + totalBalance + "]";
	}

}
