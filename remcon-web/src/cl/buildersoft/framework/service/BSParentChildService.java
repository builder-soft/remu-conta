package cl.buildersoft.framework.service;

import java.sql.Connection;
import java.util.List;

import cl.buildersoft.framework.beans.BSAction;
import cl.buildersoft.framework.beans.BSField;
import cl.buildersoft.framework.beans.parentChild.BSParentChild;
import cl.buildersoft.framework.type.BSActionType;

public interface BSParentChildService {
	public void init(Connection conn, BSParentChild parentChild);

	public List<BSField> listParentFields(BSParentChild parentChild);

	public List<BSField> listChildFields(BSParentChild parentChild);

	public void addParentAction(BSParentChild parentChild, BSAction action);

	public void addParentField(BSParentChild parentChild, BSField field);

	public void addChildAction(BSParentChild parentChild, BSAction action);

	public void addChildField(BSParentChild parentChild, BSField field);
/*
	public void configParentFields(Connection conn, BSParentChild parentChild, BSmySQL mysql);

	public void configChildFields(Connection conn, BSParentChild parentChild, BSmySQL mysql);
*/
	public List<BSField> removeIdField(BSParentChild parentChild);

	public List<BSAction> getParentActionsByType(BSParentChild parentChild, BSActionType type);

	public List<BSAction> getChildActionsByType(BSParentChild parentChild, BSActionType type);

	public BSField getParentField(BSParentChild parentChild, String name);

	public void removeParentAction(BSParentChild parentChild, String code);

	public void removeChildAction(BSParentChild parentChild, String code);

	public void removeParentField(BSParentChild parentChild, String code);

	public void removeChildField(BSParentChild parentChild, String code);

}
