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

import org.apache.catalina.connector.Request;

import cl.buildersoft.business.service.EmployeeService;
import cl.buildersoft.business.service.impl.EmployeeServiceImpl;
import cl.buildersoft.framework.beans.Document;
import cl.buildersoft.framework.beans.Employee;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.exception.BSDataBaseException;
import cl.buildersoft.framework.util.BSBeanUtilsSP;
import cl.buildersoft.web.servlet.table.AbstractServletUtil;

@WebServlet("/servlet/config/employee/DocumentEmployee")
public class DocumentEmployee extends AbstractServletUtil {
	private static final long serialVersionUID = 5316369008384063620L;

	public void listDocuments(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Long employeeId = Long.parseLong(request.getParameter("cId"));
		EmployeeService service = new EmployeeServiceImpl();
		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);
		BSmySQL bu = new BSmySQL();
		Employee employee = service.getEmployee(conn, new BSBeanUtilsSP(), employeeId);
		List<Document> listadoArchivos = listDocumentoPorEmployee(conn, bu, employeeId);
		
		request.setAttribute("listadoArchivos", listadoArchivos);
		request.setAttribute("Employee", employee);
		request.getRequestDispatcher("/WEB-INF/jsp/table/documentEmployee.jsp").forward(
		request, response);
	}
	
	public void delete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String documentId = request.getParameter("idDocument");
		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);
		mysql.callSingleSP(conn, "pDelDocument", documentId);
	}
	
	private List<Document> listDocumentoPorEmployee(Connection conn,
			BSmySQL mysql, Long employeeId) {
		ResultSet rs = mysql.callSingleSP(conn, "pListDocument",
				array2List(employeeId));

		// List<RagreementAPV> listadoApvEmp = (List<RagreementAPV>)
		// bu.list(conn,
		// new RagreementAPV(), "pListAPVForEmployee", parameter);
		List<Document> out = new ArrayList<Document>();
		try {
			while (rs.next()) {
				Document documentEmp = new Document();
				documentEmp.setId(rs.getLong("cId"));
				documentEmp.setcDesc(rs.getString("cDesc"));
				documentEmp.setcFileName(rs.getString("cFileName"));
				documentEmp.setcFileRealName(rs.getString("cFileRealName"));
				documentEmp.setcSize(rs.getLong("cSize"));
				out.add(documentEmp);
			}
		} catch (SQLException e) {
			throw new BSDataBaseException("", e.getMessage());
		}

		return out;
	}	
	
/**<code>
	private Account2 getAccountByEmployee(Connection conn, BSBeanUtilsSP bu,
			Long idEmployee) {
		return null;
	}
</code>*/
}