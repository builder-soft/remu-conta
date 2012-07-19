package cl.buildersoft.business.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.business.beans.EmployeeFile;
import cl.buildersoft.business.beans.Employee;
import cl.buildersoft.business.service.EmployeeService;
import cl.buildersoft.framework.database.BSBeanUtils;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.exception.BSProgrammerException;
import cl.buildersoft.framework.util.BSConfig;

public class EmployeeServiceImpl implements EmployeeService {

	public Employee getEmployee(Connection conn, Long id) {
		Employee out = new Employee();
		BSBeanUtils bu = new BSBeanUtils();
		out.setId(id);
		bu.search(conn, out);
		return out;
	}

	@Override
	public void saveDocument(EmployeeFile document, HttpServletRequest request) {

		BSmySQL mysql = new BSmySQL();
		Connection conn;

		conn = mysql.getConnection(request);

		BSBeanUtils bu = new BSBeanUtils();
		bu.insert(conn, document);
	}

	@Override
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

	public void downloadDocument(EmployeeFile document, HttpServletRequest request, HttpServletResponse response) {
		try {
			BSmySQL mysql = new BSmySQL();
			Connection conn = mysql.getConnection(request);
			ResultSet rs = mysql.callSingleSP(conn, "pListDocument", document.getEmployee());
			BSConfig config = new BSConfig();
			String filesPath = config.getString(conn, "EMPLOYEE_FILES");
			document.findDocument(rs);
			ServletOutputStream fos = response.getOutputStream();
			response.setContentType(document.getContentType());
			String disposition = "attachment; fileName=" + URLEncoder.encode(document.getFileName(), "UTF8") + "";
			response.setHeader("Content-Disposition", disposition);
			File file = new File(filesPath + document.getFileRealName());
			byte[] readData = new byte[1024];
			FileInputStream fis = new FileInputStream(file);
			int i = fis.read(readData);

			while (i != -1) {
				fos.write(readData, 0, i);
				i = fis.read(readData);
			}
			fos.flush();
			fis.close();
			fos.close();

		} catch (IOException e) {
			throw new BSProgrammerException(e);
		}
	}

}
