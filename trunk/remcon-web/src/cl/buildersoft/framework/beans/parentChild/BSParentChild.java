package cl.buildersoft.framework.beans.parentChild;

import java.util.HashMap;
import java.util.Map;

import cl.buildersoft.framework.util.crud.BSAction;
import cl.buildersoft.framework.util.crud.BSField;

public class BSParentChild {
	private String dataBase = null;
	private String parentTable = null;
	private String childTable = null;
	private String[] parentFields = new String[0];
	private Map<String, BSField> parentFieldsMap = new HashMap<String, BSField>();
	private String[] childFields = new String[0];
	private Map<String, BSField> childFieldsMap = new HashMap<String, BSField>();
	private String title = null;
	private String titleChild = "Items";
	private String uri = null;
	private BSAction[] parentActions = new BSAction[0];
	private BSAction[] childActions = new BSAction[0];
	private BSField parentPK = null;
	private BSField childPK = null;

	public BSParentChild(String dataBase, String parentTable, String childTable) {
		super();
		this.dataBase = dataBase;
		this.parentTable = parentTable;
		this.childTable = childTable;
	}

	public String getDataBase() {
		return dataBase;
	}

	public void setDataBase(String dataBase) {
		this.dataBase = dataBase;
	}

	public String getParentTable() {
		return parentTable;
	}

	public void setParentTable(String parentTable) {
		this.parentTable = parentTable;
	}

	public String getChildTable() {
		return childTable;
	}

	public void setChildTable(String childTable) {
		this.childTable = childTable;
	}

	public Map<String, BSField> getParentFieldsMap() {
		return parentFieldsMap;
	}

	public void setParentFieldsMap(Map<String, BSField> parentFieldsMap) {
		this.parentFieldsMap = parentFieldsMap;
	}

	public Map<String, BSField> getChildFieldsMap() {
		return childFieldsMap;
	}

	public void setChildFieldsMap(Map<String, BSField> childFieldsMap) {
		this.childFieldsMap = childFieldsMap;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public BSAction[] getParentActions() {
		return parentActions;
	}

	public void setParentActions(BSAction[] parentActions) {
		this.parentActions = parentActions;
	}

	public BSAction[] getChildActions() {
		return childActions;
	}

	public void setChildActions(BSAction[] childActions) {
		this.childActions = childActions;
	}

	public String[] getParentFields() {
		return parentFields;
	}

	public void setParentFields(String[] parentFields) {
		this.parentFields = parentFields;
	}

	public String[] getChildFields() {
		return childFields;
	}

	public void setChildFields(String[] childFields) {
		this.childFields = childFields;
	}

	public BSField getParentPK() {
		return parentPK;
	}

	public void setParentPK(BSField parentPK) {
		this.parentPK = parentPK;
	}

	public BSField getChildPK() {
		return childPK;
	}

	public void setChildPK(BSField childPK) {
		this.childPK = childPK;
	}

	public String getTitleChild() {
		return titleChild;
	}

	public void setTitleChild(String titleChild) {
		this.titleChild = titleChild;
	}

}
