package cl.buildersoft.framework.beans;

import java.util.Date;

public class Document extends BSBean {

	private static final long serialVersionUID = 7991912578572439406L;
	private String TABLE = "tFile";
	
	private Long cEmployee;
	private String cDesc;
	private String cFileName;
	private String cFileRealName;
	private Long cSize;
	private Long cFileCategory;
	private Date cDateTime;
	public Date getcDateTime() {
		return cDateTime;
	}
	public void setcDateTime(Date cDateTime) {
		this.cDateTime = cDateTime;
	}
	public Long getcEmployee() {
		return cEmployee;
	}
	public void setcEmployee(Long cEmployee) {
		this.cEmployee = cEmployee;
	}
	public String getcDesc() {
		return cDesc;
	}
	public void setcDesc(String cDesc) {
		this.cDesc = cDesc;
	}
	public String getcFileName() {
		return cFileName;
	}
	public void setcFileName(String cFileName) {
		this.cFileName = cFileName;
	}
	public String getcFileRealName() {
		return cFileRealName;
	}
	public void setcFileRealName(String cFileRealName) {
		this.cFileRealName = cFileRealName;
	}
	public Long getcSize() {
		return cSize;
	}
	public void setcSize(Long cSize) {
		this.cSize = cSize;
	}
	public Long getcFileCategory() {
		return cFileCategory;
	}
	public void setcFileCategory(Long cFileCategory) {
		this.cFileCategory = cFileCategory;
	}
}
