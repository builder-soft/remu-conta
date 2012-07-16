package cl.buildersoft.framework.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

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
	private String contentType;
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
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public void findDocument(ResultSet rs)
	{
		try {
			while (rs.next()) {
				if(rs.getLong("cId") == this.getId())
				{					
					this.setFileRealName(rs.getString("cFileRealName"));
					this.setDateTime(rs.getTimestamp("cDateTime"));
					this.setDesc(rs.getString("cDesc"));
					this.setFileName(rs.getString("cFileName"));
					this.setFileCategory(rs.getLong("cFileCategory"));
					this.setContentType(rs.getString("cContentType"));					
					return;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}