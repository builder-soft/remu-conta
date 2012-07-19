package cl.buildersoft.business.service;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.business.beans.EmployeeFile;
import cl.buildersoft.business.beans.Employee;

public interface EmployeeService {
	public Employee getEmployee(Connection conn, Long id);

	public void saveDocument(EmployeeFile document, HttpServletRequest request);

	public void deleteDocumentById(EmployeeFile document, HttpServletRequest request);

	public void downloadDocument(EmployeeFile document, HttpServletRequest request, HttpServletResponse response);
}
