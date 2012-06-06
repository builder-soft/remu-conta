package cl.buildersoft.business.service.impl;

import java.sql.Connection;

import cl.buildersoft.business.service.EmployeeService;
import cl.buildersoft.framework.beans.Employee;
import cl.buildersoft.framework.util.BSBeanUtilsSP;


public class EmployeeServiceImpl implements EmployeeService {

	public Employee getEmployee(Connection conn, BSBeanUtilsSP bu, Long id) {
		Employee out = new Employee();
		out.setId(id);
		bu.search(conn, out);
		return out;
	}
	
}
