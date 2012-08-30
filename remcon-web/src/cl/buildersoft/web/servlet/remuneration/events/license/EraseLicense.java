package cl.buildersoft.web.servlet.remuneration.events.license;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.buildersoft.business.beans.License;
import cl.buildersoft.business.service.EmployeeFileService;
import cl.buildersoft.business.service.impl.EmployeeFileServiceImpl;
import cl.buildersoft.framework.database.BSBeanUtils;
import cl.buildersoft.framework.database.BSmySQL;

@WebServlet("/servlet/remuneration/events/license/EraseLicense")
public class EraseLicense extends HttpServlet {
	private static final long serialVersionUID = -7291518468937714609L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BSmySQL mysql = new BSmySQL();
		Connection conn = mysql.getConnection(request);

		Long employeeId = Long.parseLong(request.getParameter("cId"));
		Long licenseId = Long.parseLong(request.getParameter("cLicense"));
		BSBeanUtils bu = new BSBeanUtils();

		License license = new License();
		license.setId(licenseId);
		bu.search(conn, license);

		Long fileId = license.getFile();
		bu.delete(conn, license);
		if (fileId != null) {
			EmployeeFileService efs = new EmployeeFileServiceImpl();
			efs.removeFile(conn, fileId);

		}
		mysql.closeConnection(conn);
		request.setAttribute("cId", employeeId);

		request.getRequestDispatcher("/servlet/remuneration/events/license/LicenseMain").forward(request, response);
	}

}
