package cl.buildersoft.framework.util;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cl.buildersoft.framework.exception.BSSystemException;

public class BSFileUtil {
	// private HttpServletRequest request;
	// private HttpServletResponse response;
	// private String filesPath;
	//
	// public BSFileUtil(HttpServletRequest request, HttpServletResponse
	// response, String filesPath) {
	// this.request = request;
	// this.response = response;
	// this.filesPath = filesPath;
	// }

	public Map<String, String> uploadFile(HttpServletRequest request, String filesPath, String fileName) {
		Map<String, String> out = new HashMap<String, String>();
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// factory.setRepository(new File(filesPath));
		ServletFileUpload upload = new ServletFileUpload(factory);

		List<FileItem> items = null;
		try {
			items = upload.parseRequest(request);

			// DatabaseFile databaseFile = new DatabaseFile();
			for (FileItem item : items) {
				if (item.isFormField()) {
					out.put(item.getFieldName(), item.getString());

				} else {
					out.put("file.fieldName", item.getFieldName());
					out.put("file.fileName", item.getName());
					out.put("file.contentType", item.getContentType());
					out.put("file.size", "" + item.getSize());

					File file = new File(filesPath + fileName);
					item.write(file);
				}
			}
		} catch (Exception e) {
			throw new BSSystemException(e);
		}
		return out;
	}

	public Boolean renameFile(String oldPath, String oldName, String newPath, String newName) {
		File oldFile = new File(oldPath + oldName);
		File newFile = new File(newPath + newName);
		Boolean success = oldFile.renameTo(newFile);
		return success;
	}
}
