package cl.buildersoft.framework.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.util.Base64;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cl.buildersoft.business.beans.DatabaseFile;
import cl.buildersoft.framework.exception.BSSystemException;

public class FileUtil {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private String filesPath;
	public FileUtil(HttpServletRequest request,	HttpServletResponse response, String filesPath)
	{
		this.request = request;
		this.response = response;
		this.filesPath = filesPath;
	}
	
	public void uploadFile()
	{
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(new File(filesPath));
		ServletFileUpload upload = new ServletFileUpload(factory);

		List<FileItem> items = null;
		try {
			items = upload.parseRequest(request);
			PrintWriter w = response.getWriter();
			DatabaseFile file = new DatabaseFile();
			for (FileItem item : items) {
				if (item.isFormField()) {
					String name = item.getFieldName();
					String value = item.getString();
					w.println(name + "=" + value);

					file.setDesc(value);
				} else {
					w.println("size:" + (item.getSize() / 1024) + "kb");
					w.println("type:" + item.getContentType());
					w.println("name:" + item.getName());
					w.println("IsInMemory:" + item.isInMemory());

					file.setContent(Base64.encode(item.get()));
					file.setFileName(item.getName());

					file.setSize(item.getSize());

					w.print("\n\n");

				}

			}
			w.flush();
		} catch (FileUploadException e) {
			throw new BSSystemException("0201", e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}