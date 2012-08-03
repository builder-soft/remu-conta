package cl.buildersoft.business.service;

import java.sql.Connection;

import cl.buildersoft.framework.exception.BSException;

public interface EmployeeFileService {
	public void removeFile (Connection conn, Long fileId) throws BSException;
	

}
