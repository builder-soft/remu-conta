package cl.buildersoft.framework.service.impl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cl.buildersoft.framework.beans.parentChild.BSParentChild;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.exception.BSDataBaseException;
import cl.buildersoft.framework.exception.BSProgrammerException;
import cl.buildersoft.framework.service.BSParentChildService;
import cl.buildersoft.framework.util.BSGenericModelUtils;
import cl.buildersoft.framework.util.BSUtils;
import cl.buildersoft.framework.util.crud.BSAction;
import cl.buildersoft.framework.util.crud.BSActionType;
import cl.buildersoft.framework.util.crud.BSField;

public class BSParentChildServiceImpl extends BSGenericModelUtils implements BSParentChildService {
	@Override
	public void init(Connection conn, BSParentChild parentChild) {
		configParentFields(conn, parentChild);

	}

	private void configParentFields(Connection conn, BSParentChild parentChild) {
		String sql = getSQLForReadStruct(parentChild);
		BSmySQL mysql = new BSmySQL();
		ResultSet resultSet = mysql.queryResultSet(conn, sql, null);
		configBasic(conn, parentChild, mysql, resultSet);

	}

	protected void configBasic(Connection conn, BSParentChild parentChild, BSmySQL mysql, ResultSet resultSet) {
		String[] fields = parentChild.getParentFields();

		ResultSetMetaData metaData;
		try {
			metaData = resultSet.getMetaData();
		} catch (SQLException e) {
			throw new BSDataBaseException(e);
		}

		String name = null;

		if (fields == null || fields.length == 0) {
			Integer n;
			try {
				n = metaData.getColumnCount();
			} catch (SQLException e) {
				throw new BSDataBaseException(e);
			}

			BSField field = null;
			Boolean hasPK = Boolean.FALSE;
			n++;
			for (Integer i = 1; i < n; i++) {
				try {
					name = metaData.getColumnName(i);
				} catch (SQLException e) {
					throw new BSDataBaseException("300", e.getMessage());
				}
				field = new BSField(name, name.substring(1));
				this.addParentField(parentChild, field);
				// addField(parentChild, field);
				configField(conn, parentChild, metaData, name, i, field);
				if (field.isPK() && !hasPK) {
					hasPK = Boolean.TRUE;
				}
			}

			if (!hasPK) {
				throw new BSProgrammerException("0104");
			}
		} else {
			Integer i = 1;

			for (String field : fields) {
				configField(conn, parentChild, metaData, name, i, this.getParentField(parentChild, field));

				i++;
			}
			/**
			 * <code>
			</code>
			 */
		}
	}

	protected void configField(Connection conn, BSParentChild parentChild, ResultSetMetaData metaData, String name, Integer i,
			BSField field) {
		try {
			if (field.getType() == null) {
				this.setRealType(metaData, i, field);
			}
			if (field.isPK() == null) {
				BSField pk = getPKField(conn, parentChild);

				if (pk != null) {
					field.setPK(pk.getName().equals(name));
				} else {
					field.setPK(Boolean.FALSE);
				}
			}
			if (field.getLength() == null) {
				field.setLength(metaData.getColumnDisplaySize(i));
			}
		} catch (SQLException e) {
			throw new BSDataBaseException(e);
		}

	}

	private BSField getPKField(Connection conn, BSParentChild parentChild) {
		String fieldName = null;
		BSField out = parentChild.getParentPK();
		if (out == null) {
			DatabaseMetaData dbmd;
			try {
				dbmd = (DatabaseMetaData) conn.getMetaData();

				ResultSet rs = dbmd.getPrimaryKeys(parentChild.getDataBase(), null, parentChild.getParentTable());
				while (rs.next()) {
					fieldName = rs.getString("COLUMN_NAME");
				}
				rs.close();
			} catch (SQLException e) {
				throw new BSDataBaseException(e);
			}
			out = getParentField(parentChild, fieldName);
			parentChild.setParentPK(out);
		}

		return out;
	}

	private String getSQLForReadStruct(BSParentChild parentChild) {
		String out = "SELECT ";

		// BSField[] fields = parentChild.getParentFields();
		String[] names = parentChild.getParentFields();

		if (names == null || names.length == 0) {
			out += "*";
		} else {
			out += BSUtils.unSplitString(names, ",");
		}

		out += " FROM " + parentChild.getDataBase() + "." + parentChild.getParentTable();
		out += " LIMIT 0,1";
		return out;
	}

	/**
	 * <code>
	private String[] fieldsToNames(BSField[] fields) {
		String[] out = new String[fields.length];
		int i = 0;
		for (BSField field : fields) {
			out[i] = field.getName();
			i++;
		}
		return out;
	}
</code>
	 */
	@Override
	public List<BSField> listParentFields(BSParentChild parentChild) {

		return null;
	}

	@Override
	public List<BSField> listChildFields(BSParentChild parentChild) {

		return null;
	}

	@Override
	public void addParentAction(BSParentChild parentChild, BSAction action) {

	}

	@Override
	public void addParentField(BSParentChild parentChild, BSField field) {
		Map<String, BSField> parentFieldsMap = parentChild.getParentFieldsMap();
		String[] parentFields = parentChild.getParentFields();
		parentFieldsMap.put(field.getName(), field);

		String[] target = new String[parentFields.length + 1];
		System.arraycopy(parentFields, 0, target, 0, parentFields.length);
		target[target.length - 1] = field.getName();
		parentChild.setParentFields(target);

	}

	@Override
	public void addChildAction(BSParentChild parentChild, BSAction action) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addChildField(BSParentChild parentChild, BSField field) {
		// TODO Auto-generated method stub

	}

	/*
	 * @Override public void configParentFields(Connection conn, BSParentChild
	 * parentChild, BSmySQL mysql) { // TODO Auto-generated method stub }
	 * 
	 * @Override public void configChildFields(Connection conn, BSParentChild
	 * parentChild, BSmySQL mysql) { // TODO Auto-generated method stub }
	 */
	@Override
	public List<BSField> removeIdField(BSParentChild parentChild) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BSAction> getParentActionsByType(BSParentChild parentChild, BSActionType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BSAction> getChildActionsByType(BSParentChild parentChild, BSActionType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BSField getParentField(BSParentChild parentChild, String name) {
		return parentChild.getParentFieldsMap().get(name);
	}

	@Override
	public void removeParentAction(BSParentChild parentChild, String code) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeChildAction(BSParentChild parentChild, String code) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeParentField(BSParentChild parentChild, String code) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeChildField(BSParentChild parentChild, String code) {
		// TODO Auto-generated method stub

	}
}
