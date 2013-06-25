package cl.buildersoft.framework.beans.parentChild;

import java.util.Map;

import cl.buildersoft.framework.beans.BSAction;
import cl.buildersoft.framework.beans.BSField;

public class BSParentChild {
	private String dataBase = null;
	private String parentTable = null;
	private String childTable = null;
	private String[] parentFields = null;
	private Map<String, BSField> parentFieldsMap = null;
	private String[] childFields = null;
	private Map<String, BSField> childFieldsMap = null;
	private String title = null;
	private String uri = null;
	private BSAction[] parentActions = null;
	private BSAction[] childActions = null;

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

 
}
