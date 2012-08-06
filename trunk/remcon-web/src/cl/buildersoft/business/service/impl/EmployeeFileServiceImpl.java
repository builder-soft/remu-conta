package cl.buildersoft.business.service.impl;

import java.io.File;
import java.sql.Connection;

import cl.buildersoft.business.beans.EmployeeFile;
import cl.buildersoft.business.service.EmployeeFileService;
import cl.buildersoft.framework.database.BSBeanUtils;
import cl.buildersoft.framework.exception.BSConfigurationException;
import cl.buildersoft.framework.exception.BSException;
import cl.buildersoft.framework.util.BSConfig;

public class EmployeeFileServiceImpl implements EmployeeFileService {
	@Override
	public void removeFile(Connection conn, Long fileId) throws BSException {
		BSBeanUtils bu = new BSBeanUtils();
		EmployeeFile employeeFile = new EmployeeFile();
		employeeFile.setId(fileId);

		if (bu.search(conn, employeeFile)) {
			BSConfig config = new BSConfig();
			String path = config.getFilePath(conn);
			Long category = employeeFile.getFileCategory();

			String realName = employeeFile.getFileRealName();

			File file = new File(path + category + BSConfig.getFileSeparator() + realName);
			file.delete();
			bu.delete(conn, employeeFile);
		} else {
			throw new BSConfigurationException("No se encuentra el registro para el archivo " + fileId);
		}

	}
}
