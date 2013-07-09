package cl.buildersoft.framework.util;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import cl.buildersoft.framework.dataType.BSDataType;
import cl.buildersoft.framework.dataType.BSDataTypeUtil;
import cl.buildersoft.framework.exception.BSDataBaseException;
import cl.buildersoft.framework.exception.BSProgrammerException;
import cl.buildersoft.framework.util.crud.BSField;

public abstract class BSGenericModelUtils {
	protected void setRealType(ResultSetMetaData metaData, Integer i, BSField field) {
		/**
		 * <code>
		System.out.println( name + " "+ metaData.getColumnTypeName(i));
		cId BIGINT
		cName VARCHAR
		cBorn DATE
		cLastLogin TIMESTAMP
		cSalary DOUBLE
		</code>
		 */
		String typeName;
		try {
			typeName = metaData.getColumnTypeName(i);
		} catch (SQLException e) {
			throw new BSDataBaseException(e);
		}

		if (typeName.equals("BIGINT")) {
			field.setType(BSDataTypeUtil.create(BSDataType.LONG));
		} else if (typeName.equals("VARCHAR") || typeName.equals("CHAR")) {
			field.setType(BSDataTypeUtil.create(BSDataType.STRING));
		} else if (typeName.equals("DATE")) {
			field.setType(BSDataTypeUtil.create(BSDataType.DATE));
		} else if (typeName.equals("TIMESTAMP")) {
			field.setType(BSDataTypeUtil.create(BSDataType.TIMESTAMP));
		} else if (typeName.equals("DOUBLE")) {
			field.setType(BSDataTypeUtil.create(BSDataType.DOUBLE));
		}else if (typeName.equals("DECIMAL")){
			field.setType(BSDataTypeUtil.create(BSDataType.DECIMAL));
		} else if (typeName.equals("BIT")) {
			field.setType(BSDataTypeUtil.create(BSDataType.BOOLEAN));
		} else if (typeName.equals("INT")) {
			field.setType(BSDataTypeUtil.create(BSDataType.INTEGER));
		} else {
			throw new BSProgrammerException("0110", "No está catalogado el tipo " + typeName + ", verifique método "
					+ getClass().getName() + ".setRealType()");
		}
	}
	protected String getSQLForReadStruct(String databaseName, String[] names, String tableName) {
		String out = "SELECT ";

		if (names == null || names.length == 0) {
			out += "*";
		} else {
			out += BSUtils.unSplitString(names, ",");
		}

		out += " FROM " + databaseName + "." + tableName;// parentChild.getParentTable();
		out += " LIMIT 0,1";
		return out;
	}
}
