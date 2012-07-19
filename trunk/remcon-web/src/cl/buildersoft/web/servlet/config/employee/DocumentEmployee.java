package cl.buildersoft.web.servlet.config.employee;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.business.beans.EmployeeFile;
import cl.buildersoft.business.beans.Employee;
import cl.buildersoft.business.beans.FileCategory;
import cl.buildersoft.business.service.EmployeeService;
import cl.buildersoft.business.service.impl.EmployeeServiceImpl;
import cl.buildersoft.framework.database.BSBeanUtils;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.exception.BSConfigurationException;
import cl.buildersoft.framework.exception.BSDataBaseException;
import cl.buildersoft.framework.util.BSConfig;
import cl.buildersoft.framework.util.BSFileUtil;
import cl.buildersoft.web.servlet.table.AbstractServletUtil;

@WebServlet("/servlet/config/employee/DocumentEmployee")
public class DocumentEmployee extends AbstractServletUtil {
	private static final long serialVersionUID = 5316369008384063620L;

	public void listDocuments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cId = (String) (request.getParameter("cId") != null ? request.getParameter("cId") : request.getAttribute("cId"));
		Long employeeId = Long.parseLong(cId);
		EmployeeService service = new EmployeeServiceImpl();
		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);
		BSBeanUtils bu = new BSBeanUtils();

		Employee employee = service.getEmployee(conn, employeeId);
		List<EmployeeFile> files = listDocumentsByEmployee(conn, mysql, employeeId);
		List<FileCategory> category = (List<FileCategory>) bu.listAll(conn, new FileCategory());

		request.setAttribute("filesEmployee", files);
		request.setAttribute("Employee", employee);
		request.setAttribute("Category", category);
		request.getRequestDispatcher("/WEB-INF/jsp/config/employee/documentEmployee.jsp").forward(request, response);
	}

	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long documentId = Long.valueOf(request.getParameter("idDocument"));
		Long employeeId = Long.valueOf(request.getParameter("cId"));
		EmployeeService service = new EmployeeServiceImpl();
		EmployeeFile document = new EmployeeFile();
		document.setId(documentId);
		document.setEmployee(employeeId);
		service.deleteDocumentById(document, request);
		request.getRequestDispatcher("/servlet/config/employee/DocumentEmployee?Method=listDocuments").forward(request, response);
	}

	public void downloadFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long documentId = Long.valueOf(request.getParameter("idDocument"));
		Long employeeId = Long.valueOf(request.getParameter("cId"));
		EmployeeService service = new EmployeeServiceImpl();
		EmployeeFile document = new EmployeeFile();
		document.setId(documentId);
		document.setEmployee(employeeId);
		service.downloadDocument(document, request, response);
	}

	public void uploadFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);
		BSFileUtil fileUtil = new BSFileUtil();

		BSConfig config = new BSConfig();
		String tempPath = fixPath(config.getString(conn, "EMPLOYEE_FILES"));

		String tempFileName = "" + System.currentTimeMillis();
		Map<String, String> values = (HashMap<String, String>) fileUtil.uploadFile(request, tempPath, tempFileName);

		Long idEmployee = Long.parseLong(values.get("cIdEmployee"));
		Long idCategory = Long.parseLong(values.get("cCategory"));

		EmployeeService employeeService = new EmployeeServiceImpl();
		Employee employee = employeeService.getEmployee(conn, idEmployee);

		String newPath = getPath(tempPath, idCategory);
		String newFileName = getFileName(employee, values.get("file.fileName"));

		if (!fileUtil.renameFile(tempPath, tempFileName, fixPath(newPath), newFileName)) {
			throw new BSConfigurationException("Can't move file from " + tempPath + " to " + newPath);
		}
		saveFileToDatabase(conn, values, newFileName);

		request.getRequestDispatcher("/servlet/config/employee/DocumentEmployee?Method=listDocuments&cId=" + idEmployee).forward(
				request, response);
	}

	private void saveFileToDatabase(Connection conn, Map<String, String> values, String newFileName) {
		EmployeeFile employeeFile = new EmployeeFile();
		employeeFile.setContentType(values.get("file.contentType"));
		employeeFile.setDateTime(new Timestamp(System.currentTimeMillis()));
		employeeFile.setDesc(values.get("desc"));
		employeeFile.setEmployee(Long.parseLong(values.get("cIdEmployee")));
		employeeFile.setFileCategory(Long.parseLong(values.get("cCategory")));
		employeeFile.setFileName(values.get("file.fileName"));
		employeeFile.setFileRealName(newFileName);

		Long size = Math.round(Double.parseDouble("" + (Long.parseLong(values.get("file.size")) / 1024)));
		employeeFile.setSize(size);
		BSBeanUtils bu = new BSBeanUtils();
		bu.insert(conn, employeeFile);

	}

	private String fixPath(String path) {
		String fileSeparator = BSConfig.getFileSeparator();
		if (path.lastIndexOf(fileSeparator) < path.length()) {
			path += fileSeparator;
		}
		return path;
	}

	private String getPath(String path, Long category) {
		String fullPath = path + category;
		File folder = new File(fullPath);

		if (!folder.exists()) {
			folder.mkdirs();
		}

		return fullPath;
	}

	private String getFileName(Employee employee, String fileName) {
		/**
		 * <code>
		 * Ejemplo de archivo a cargar RUT FechaHora como Long
		 * 128706682-12345678901234567890.docx
		 * 
		 * C:\archivos\ -> Configuracion \1 -> (id de categoria) Categoria Curriculums 
		 * 								 \2 -> Fotos \128706682-12345678901234567890.png -> Foto carnet del empleado 12870668-2
		 * </code>
		 */
		String rut = employee.getRut().replaceAll("-", "");
		String out = rut + "-" + System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."));
		return out;
	}

	private List<EmployeeFile> listDocumentsByEmployee(Connection conn, BSmySQL mysql, Long employeeId) {
		ResultSet rs = mysql.callSingleSP(conn, "pListDocument", array2List(employeeId));

		List<EmployeeFile> out = new ArrayList<EmployeeFile>();
		try {
			while (rs.next()) {
				EmployeeFile documentEmp = new EmployeeFile();
				documentEmp.setId(rs.getLong("cId"));
				documentEmp.setDesc(rs.getString("cDesc"));
				documentEmp.setFileName(rs.getString("cFileName"));
				documentEmp.setFileRealName(rs.getString("cFileRealName"));
				documentEmp.setSize(rs.getLong("cSize"));
				documentEmp.setDateTime(rs.getTimestamp("cDateTime"));
				out.add(documentEmp);
			}
		} catch (SQLException e) {
			throw new BSDataBaseException(e);
		}

		return out;
	}

}