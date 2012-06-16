package cl.buildersoft.business.beans;

import java.sql.Timestamp;

import cl.buildersoft.framework.beans.BSBean;

public class Document extends BSBean {

	private static final long serialVersionUID = 7991912578572439406L;
	private String TABLE = "tFile";
	
	private Long employee;
	private String desc;
	private String fileName;
	private String fileRealName;
	private Long size;
	private Long fileCategory;
	private Timestamp dateTime;
	public Long getEmployee() {
		return employee;
	}
	public void setEmployee(Long employee) {
		this.employee = employee;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileRealName() {
		return fileRealName;
	}
	public void setFileRealName(String fileRealName) {
		this.fileRealName = fileRealName;
	}
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	public Long getFileCategory() {
		return fileCategory;
	}
	public void setFileCategory(Long fileCategory) {
		this.fileCategory = fileCategory;
	}
	public Timestamp getDateTime() {
		return dateTime;
	}
	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}

}
