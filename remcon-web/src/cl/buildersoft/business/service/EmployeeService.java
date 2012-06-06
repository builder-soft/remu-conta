package cl.buildersoft.business.service;

import java.sql.Connection;

import cl.buildersoft.framework.beans.Employee;
import cl.buildersoft.framework.util.BSBeanUtilsSP;


public interface EmployeeService {
	 
	public Employee getEmployee(Connection conn, BSBeanUtilsSP bu, Long id);
}
