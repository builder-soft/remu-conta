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
import cl.buildersoft.framework.util.crud.BSAction;
import cl.buildersoft.framework.util.crud.BSActionType;
import cl.buildersoft.framework.util.crud.BSField;

public class BSParentChildServiceImpl extends BSGenericModelUtils implements BSParentChildService {
	@Override
	public void init(Connection conn, BSParentChild parentChild) {
		configParentFields(conn, parentChild);
		configChildFields(conn, parentChild);
		
		BSAction action = new BSAction("NEW", BSActionType.Table);
		action.setLabel("Nuevo");
		addParentAction(parentChild, action);
		
		
		action = new BSAction("SEARCH", BSActionType.Table);
		action.setLabel("Buscar");
		addParentAction(parentChild, action);
		
	}

	private void configChildFields(Connection conn, BSParentChild parentChild) {
		String sql = getSQLForReadStruct(parentChild.getDataBase(), parentChild.getChildFields(), parentChild.getChildTable());
		BSmySQL mysql = new BSmySQL();
		ResultSet resultSet = mysql.queryResultSet(conn, sql, null);
		configBasic(conn, parentChild, parentChild.getChildFields(), mysql, resultSet, false);
		mysql.closeSQL(resultSet);
	}

	private void configParentFields(Connection conn, BSParentChild parentChild) {
		String sql = getSQLForReadStruct(parentChild.getDataBase(), parentChild.getParentFields(), parentChild.getParentTable());
		BSmySQL mysql = new BSmySQL();
		ResultSet resultSet = mysql.queryResultSet(conn, sql, null);
		configBasic(conn, parentChild, parentChild.getParentFields(), mysql, resultSet, true);
		mysql.closeSQL(resultSet);
	}

	protected void configBasic(Connection conn, BSParentChild parentChild, BSmySQL mysql, ResultSet resultSet) {
		configBasic(conn, parentChild, parentChild.getParentFields(), mysql, resultSet, true);
	}

	protected void configBasic(Connection conn, BSParentChild parentChild, String[] names, BSmySQL mysql, ResultSet resultSet,
			Boolean isParent) {
		ResultSetMetaData metaData;
		try {
			metaData = resultSet.getMetaData();
		} catch (SQLException e) {
			throw new BSDataBaseException(e);
		}

		String name = null;
		if (names == null || names.length == 0) {
			Integer n;
			try {
				n = metaData.getColumnCount();
			} catch (SQLException e) {
				throw new BSDataBaseException(e);
			}

			BSField field = null;
			Boolean hasPK = false;
			n++;
			for (Integer i = 1; i < n; i++) {
				try {
					name = metaData.getColumnName(i);
				} catch (SQLException e) {
					throw new BSDataBaseException(e);
				}
				field = new BSField(name, name.substring(1));
				if (isParent) {
					this.addParentField(parentChild, field);
				} else {
					this.addChildField(parentChild, field);
				}

				configField(conn, parentChild, metaData, name, i, field, isParent);

				if (field.isPK() && !hasPK) {
					hasPK = Boolean.TRUE;
				}
			}

			if (!hasPK) {
				throw new BSProgrammerException("0104");
			}
		} else {
			Integer i = 1;

			for (String field : names) {
				configField(conn, parentChild, metaData, name, i, this.getParentField(parentChild, field), isParent);

				i++;
			}
		}
	}

	protected void configField(Connection conn, BSParentChild parentChild, ResultSetMetaData metaData, String name, Integer i,
			BSField field, Boolean isParent) {
		try {
			if (field.getType() == null) {
				this.setRealType(metaData, i, field);
			}
			if (field.isPK() == null) {
				BSField pk = getPKField(conn, parentChild, isParent);

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

	private BSField getPKField(Connection conn, BSParentChild parentChild, Boolean isParent) {
		String fieldName = null;
		BSField out = isParent ? parentChild.getParentPK() : parentChild.getChildPK();
		if (out == null) {
			DatabaseMetaData dbmd;
			try {
				dbmd = (DatabaseMetaData) conn.getMetaData();

				ResultSet rs = dbmd.getPrimaryKeys(parentChild.getDataBase(), null, isParent ? parentChild.getParentTable()
						: parentChild.getChildTable());
				while (rs.next()) {
					fieldName = rs.getString("COLUMN_NAME");
				}
				rs.close();
			} catch (SQLException e) {
				throw new BSDataBaseException(e);
			}

			if (isParent) {
				out = getParentField(parentChild, fieldName);
				parentChild.setParentPK(out);

			} else {
				out = getChildField(parentChild, fieldName);
				parentChild.setChildPK(out);
			}
		}

		return out;
	}

	private BSField getChildField(BSParentChild parentChild, String fieldName) {
		return parentChild.getChildFieldsMap().get(fieldName);
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
		BSAction[] actions = parentChild.getParentActions();
		actions = addActionToArray(actions, action);
		parentChild.setParentActions(actions);
	}

	@Override
	public void addChildAction(BSParentChild parentChild, BSAction action) {

	}

	private BSAction[] addActionToArray(BSAction[] actions, BSAction action) {
		BSAction[] target = new BSAction[actions.length + 1];
		System.arraycopy(actions, 0, target, 0, actions.length);
		target[target.length - 1] = action;
//		actions = target;
		return target;
	}

	@Override
	public void addParentField(BSParentChild parentChild, BSField field) {
		Map<String, BSField> parentFieldsMap = parentChild.getParentFieldsMap();
		String[] parentFields = parentChild.getParentFields();
		String[] target = addFieldToArray(field, parentFieldsMap, parentFields);
		parentChild.setParentFields(target);
	}

	@Override
	public void addChildField(BSParentChild parentChild, BSField field) {
		Map<String, BSField> childFieldsMap = parentChild.getChildFieldsMap();
		String[] childFields = parentChild.getChildFields();
		String[] target = addFieldToArray(field, childFieldsMap, childFields);
		parentChild.setChildFields(target);

	}

	private String[] addFieldToArray(BSField field, Map<String, BSField> fieldsMap, String[] fields) {
		fieldsMap.put(field.getName(), field);
		String[] target = new String[fields.length + 1];
		System.arraycopy(fields, 0, target, 0, fields.length);
		target[target.length - 1] = field.getName();
		return target;
	}

	/**
	 * @Override public void configParentFields(Connection conn, BSParentChild
	 *           parentChild, BSmySQL mysql) { // TODO Auto-generated method
	 *           stub }
	 * 
	 * @Override public void configChildFields(Connection conn, BSParentChild
	 *           parentChild, BSmySQL mysql) { // TODO Auto-generated method
	 *           stub }
	 */
	@Override
	public List<BSField> removeIdField(BSParentChild parentChild) {

		return null;
	}

	@Override
	public List<BSAction> getParentActionsByType(BSParentChild parentChild, BSActionType type) {

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
