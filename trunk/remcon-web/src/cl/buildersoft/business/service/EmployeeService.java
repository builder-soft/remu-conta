package cl.buildersoft.business.service;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import cl.buildersoft.framework.beans.Document;
import cl.buildersoft.framework.beans.Employee;
import cl.buildersoft.framework.util.BSBeanUtilsSP;


public interface EmployeeService {
	 
	public Employee getEmployee(Connection conn, BSBeanUtilsSP bu, Long id);
	public void saveDocument(Document document, HttpServletRequest request);
	public void deleteDocumentById(Document document, HttpServletRequest request);
}
