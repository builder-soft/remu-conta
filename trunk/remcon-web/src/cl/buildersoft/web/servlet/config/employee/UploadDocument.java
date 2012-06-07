package cl.buildersoft.web.servlet.config.employee;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.util.Base64;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cl.buildersoft.business.service.EmployeeService;
import cl.buildersoft.business.service.impl.EmployeeServiceImpl;
import cl.buildersoft.framework.beans.DatabaseFile;
import cl.buildersoft.framework.beans.Document;
import cl.buildersoft.framework.exception.BSSystemException;

@WebServlet("/servlet/config/employee/UploadDocument")
public class UploadDocument extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UploadDocument() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/common/no-access.jsp")
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// String desc = request.getParameter("desc");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		EmployeeService service = new EmployeeServiceImpl();
		Document document = new Document();
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e) {
			throw new BSSystemException("0201", e.getMessage());
		}
		String name = "";
		String value = "";
		PrintWriter w = response.getWriter();
		for (FileItem item : items) {
			if (item.isFormField()) {
				if(item.getFieldName().equalsIgnoreCase("cIdEmployee"))
				{
					request.setAttribute("cId", item.getString());
					document.setEmployee(Long.valueOf(item.getString()));
				}else
				{
					name = item.getFieldName();
					value = item.getString();
				}
			} else {
				String fileName = item.getName().lastIndexOf(".") != -1 ? item.getName().substring(0,item.getName().indexOf(".")) : "";
                String extencion = item.getName().lastIndexOf(".") != -1 ? item.getName().substring(item.getName().lastIndexOf(".")+1) : "";
                String realName = fileName + "_" +new Date().getTime() + "." + extencion;
				File uploadedFile = new File("C:\\temporal\\" + realName);
                try {
					item.write(uploadedFile);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				document.setDateTime(new Date());
				document.setSize((item.getSize() / 1024));
				document.setFileName(item.getName());
				document.setFileRealName(realName);
				document.setDesc("Descripcion de prueba");
				document.setFileCategory(new Long(1));
				service.saveDocument(document,request);
				
				request.getRequestDispatcher("/servlet/config/employee/DocumentEmployee?Method=listDocuments").forward(
						request, response);
			}

			
			
		}
		w.flush();
	}
}
