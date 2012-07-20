package cl.buildersoft.business.service.impl;

import java.sql.Connection;

import cl.buildersoft.business.beans.Employee;
import cl.buildersoft.business.service.EmployeeService;
import cl.buildersoft.framework.database.BSBeanUtils;

public class EmployeeServiceImpl implements EmployeeService {

	public Employee getEmployee(Connection conn, Long id) {
		Employee out = new Employee();
		BSBeanUtils bu = new BSBeanUtils();
		out.setId(id);
		bu.search(conn, out);
		return out;
	}
	/**
	 * <code>
	
	public void deleteDocumentById(EmployeeFile document, HttpServletRequest request) {
		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);
		ResultSet rs = mysql.callSingleSP(conn, "pListDocument", document.getEmployee());

		document.findDocument(rs);
		File uploadedFile = new File("C:\\temporal\\" + document.getFileName());
		uploadedFile.delete(); // eliminacion de archivo fisico
		mysql.callSingleSP(conn, "pDelDocument", document.getId()); // Eliminacion
																	// de
																	// archivo
																	// desde BD
		request.setAttribute("cId", request.getParameter("cId"));

	}
	</code>
	 */
}
