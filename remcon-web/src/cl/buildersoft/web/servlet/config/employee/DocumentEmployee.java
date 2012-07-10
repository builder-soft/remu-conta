package cl.buildersoft.web.servlet.config.employee;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.business.beans.Document;
import cl.buildersoft.business.beans.Employee;
import cl.buildersoft.business.service.EmployeeService;
import cl.buildersoft.business.service.impl.EmployeeServiceImpl;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.exception.BSDataBaseException;
import cl.buildersoft.framework.util.BSBeanUtilsSP;
import cl.buildersoft.framework.util.BSConfig;
import cl.buildersoft.framework.util.FileUtil;
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
		// BSmySQL bu = new BSmySQL();
		Employee employee = service.getEmployee(conn, new BSBeanUtilsSP(), employeeId);
		List<Document> listadoArchivos = listDocumentoPorEmployee(conn, mysql, employeeId);

		request.setAttribute("listadoArchivos", listadoArchivos);
		request.setAttribute("Employee", employee);
		request.getRequestDispatcher("/WEB-INF/jsp/config/employee/documentEmployee.jsp").forward(request, response);
	}

	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long documentId = Long.valueOf(request.getParameter("idDocument"));
		Long employeeId = Long.valueOf(request.getParameter("cId"));
		EmployeeService service = new EmployeeServiceImpl();
		Document document = new Document();
		document.setId(documentId);
		document.setEmployee(employeeId);
		service.deleteDocumentById(document, request);
		request.getRequestDispatcher("/servlet/config/employee/DocumentEmployee?Method=listDocuments").forward(request, response);
	}

	public void downloadFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long documentId = Long.valueOf(request.getParameter("idDocument"));
		Long employeeId = Long.valueOf(request.getParameter("cId"));
		EmployeeService service = new EmployeeServiceImpl();
		Document document = new Document();
		document.setId(documentId);
		document.setEmployee(employeeId);
		service.downloadDocument(document, request, response);
	}

	public void uploadFile(HttpServletRequest request, HttpServletResponse response) {
		BSConfig config = new BSConfig();
		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);
		String filesPath = config.getString(conn, "EMPLOYEE_FILES");
		FileUtil fileUtil = new FileUtil(request, response, filesPath);
		fileUtil.uploadFile();
	}

	private List<Document> listDocumentoPorEmployee(Connection conn, BSmySQL mysql, Long employeeId) {
		ResultSet rs = mysql.callSingleSP(conn, "pListDocument", array2List(employeeId));

		// List<RagreementAPV> listadoApvEmp = (List<RagreementAPV>)
		// bu.list(conn,
		// new RagreementAPV(), "pListAPVForEmployee", parameter);
		List<Document> out = new ArrayList<Document>();
		try {
			while (rs.next()) {
				Document documentEmp = new Document();
				documentEmp.setId(rs.getLong("cId"));
				documentEmp.setDesc(rs.getString("cDesc"));
				documentEmp.setFileName(rs.getString("cFileName"));
				documentEmp.setFileRealName(rs.getString("cFileRealName"));
				documentEmp.setSize(rs.getLong("cSize"));
				documentEmp.setDateTime(rs.getTimestamp("cDateTime"));
				out.add(documentEmp);
			}
		} catch (SQLException e) {
			throw new BSDataBaseException("", e.getMessage());
		}

		return out;
	}

	/**
	 * <code>
	private Account2 getAccountByEmployee(Connection conn, BSBeanUtilsSP bu,
			Long idEmployee) {
		return null;
	}
</code>
	 */
}