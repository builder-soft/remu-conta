package cl.buildersoft.business.service;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.business.beans.Document;
import cl.buildersoft.business.beans.Employee;

public interface EmployeeService {
	public Employee getEmployee(Connection conn, Long id);

	public void saveDocument(Document document, HttpServletRequest request);

	public void deleteDocumentById(Document document, HttpServletRequest request);

	public void downloadDocument(Document document, HttpServletRequest request, HttpServletResponse response);
}
