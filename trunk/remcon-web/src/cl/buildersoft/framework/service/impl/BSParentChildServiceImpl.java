package cl.buildersoft.framework.service.impl;

import java.sql.Connection;
import java.util.List;

import cl.buildersoft.framework.beans.BSAction;
import cl.buildersoft.framework.beans.BSField;
import cl.buildersoft.framework.beans.parentChild.BSParentChild;
import cl.buildersoft.framework.database.BSmySQL;
import cl.buildersoft.framework.service.BSParentChildService;
import cl.buildersoft.framework.type.BSActionType;

public class BSParentChildServiceImpl implements BSParentChildService {
	@Override
	public BSParentChild init(Connection conn, String dataBase, String parentTable, String childName) {
		
		return null;
	}

	@Override
	public List<BSField> listParentFields(BSParentChild parentChild) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BSField> listChildFields(BSParentChild parentChild) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addParentAction(BSParentChild parentChild, BSAction action) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addParentField(BSParentChild parentChild, BSField field) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addChildAction(BSParentChild parentChild, BSAction action) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addChildField(BSParentChild parentChild, BSField field) {
		// TODO Auto-generated method stub

	}

	@Override
	public void configParentFields(Connection conn, BSParentChild parentChild, BSmySQL mysql) {
		// TODO Auto-generated method stub

	}

	@Override
	public void configChildFields(Connection conn, BSParentChild parentChild, BSmySQL mysql) {
		// TODO Auto-generated method stub

	}

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
		// TODO Auto-generated method stub
		return null;
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
