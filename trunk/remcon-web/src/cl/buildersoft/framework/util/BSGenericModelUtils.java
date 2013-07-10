package cl.buildersoft.framework.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

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
	
	protected void configFKFields(Connection conn, Map<String, BSField> fields, String tableName) {
		String databaseFK = null;
		String tableFK = null;
		String fieldFK = null;
		BSField field =null;
		
		for (Entry<String, BSField> entry : fields.entrySet()) {
			/**
		    System.out.println("Key [" + entry.getKey() + "]");
		    System.out.println("Value [" + entry.getValue() + "]");
		    */
			 field = entry.getValue();
			String[] fkTableInfo = getFKTableInfo(conn, field, tableName);
			if (fkTableInfo != null) {
				field.setFK  (fkTableInfo[0], fkTableInfo[1], fkTableInfo[2]);
			}
			
		}		
		/**<code>
		for (BSField field : fields) {
			String[] pkTableInfo = getFKTableInfo(conn, field);

			if (pkTableInfo != null) {
				field.setFK(pkTableInfo[0], pkTableInfo[1], pkTableInfo[2]);

				databaseFK = field.getFKDatabase();
				tableFK = field.getFKTable();
				fieldFK = field.getFKField();

				if (tableFK != null && fieldFK != null) {
					String sql = "SELECT cId,cName ";
					sql += "FROM " + databaseFK + ".";
					sql += field2Table(conn, field.getName());
					sql += " ORDER BY cName";

					field.setFkData(mySQL.resultSet2Matrix(mySQL.queryResultSet(conn, sql, null)));
				}
			}
			
		}</code>*/
	}
	private String[] getFKTableInfo(Connection conn, BSField field, String tableName) {
		/**
		 * <code>
		 * PKTABLE_CAT=bscommon 
		 * PKTABLE_SCHEM=null 
		 * PKTABLE_NAME=tboard
		 * PKCOLUMN_NAME=cId
		 * </code>
		 */
		DatabaseMetaData dbmd;
		ResultSet rs;
		String[] out = null;

		
		Map<String, String[]> fkInfo = field.getFkInfo();
		if (fkInfo== null) {
			fkInfo = new HashMap<String, String[]>();
			try {
				dbmd = (DatabaseMetaData) conn.getMetaData();
				rs = dbmd.getImportedKeys(null, null, tableName);

				while (rs.next()) {
					String code = rs.getString("FKCOLUMN_NAME");
					String database = rs.getString("PKTABLE_CAT");
					String table = rs.getString("PKTABLE_NAME");
					String column = rs.getString("PKCOLUMN_NAME");

					String[] info = new String[3];
					info[0] = database;
					info[1] = table;
					info[2] = column;

					fkInfo.put(code, info);
				}

			} catch (SQLException e) {
				throw new BSDataBaseException(e);
			}
		}

		out = fkInfo.get(field.getName());

		return out;
	}
}
