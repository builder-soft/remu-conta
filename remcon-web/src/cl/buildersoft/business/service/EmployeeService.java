package cl.buildersoft.business.service;

import java.sql.Connection;

import cl.buildersoft.business.beans.Employee;

public interface EmployeeService {
	public Employee getEmployee(Connection conn, Long id);

	 

 
}
