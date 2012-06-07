package cl.buildersoft.business.service.impl;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import cl.buildersoft.business.service.EmployeeService;
import cl.buildersoft.framework.beans.Document;
import cl.buildersoft.framework.beans.Employee;
import cl.buildersoft.framework.database.BSBeanUtils;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.exception.BSDataBaseException;
import cl.buildersoft.framework.util.BSBeanUtilsSP;


public class EmployeeServiceImpl implements EmployeeService {

	public Employee getEmployee(Connection conn, BSBeanUtilsSP bu, Long id) {
		Employee out = new Employee();
		out.setId(id);
		bu.search(conn, out);
		return out;
	}

	@Override
	public void saveDocument(Document document, HttpServletRequest request) {
		
		BSmySQL mysql = new BSmySQL();
		Connection conn;

		conn = mysql.getConnection(request);

		BSBeanUtils bu = new BSBeanUtils();
		bu.insert(conn, document);		
	}

	@Override
	public void deleteDocumentById(Document document,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);
	
		ResultSet rs = mysql.callSingleSP(conn, "pListDocument", document.getEmployee());
		boolean bandera = false;
		String deleteFileName = null;
		try {
			while (rs.next() && bandera == false) {
				if(rs.getLong("cId") == document.getId())
				{
					bandera = true;
					deleteFileName = rs.getString("cFileRealName");
				}
			}
			
			File uploadedFile = new File("C:\\temporal\\" + deleteFileName);
			uploadedFile.delete(); //eliminacion de archivo fisico
			mysql.callSingleSP(conn, "pDelDocument", document.getId()); //Eliminacion de archivo desde BD
			request.setAttribute("cId", request.getParameter("cId"));				
		} catch (SQLException e) {
			throw new BSDataBaseException("", e.getMessage());
		}		
		
		
	}
	
}
