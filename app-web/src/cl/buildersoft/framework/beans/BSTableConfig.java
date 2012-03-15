package cl.buildersoft.framework.beans;

public class BSTableConfig {
	private String tableName = null;
	private BSField[] fields = null;
	private String title = null;
	private Boolean canInsert = Boolean.TRUE;
	private Boolean canEdit = Boolean.TRUE;
	private Boolean canDelete = Boolean.TRUE;

	public BSTableConfig(String tableName) {
		this.fields = new BSField[0];
		this.tableName = tableName;
		this.title = tableName;
	}

	public String getTableName() {
		return this.tableName;
	}

	public BSField[] getFields() {
		return fields;
	}

	public void setFields(BSField[] fields) {
		this.fields = fields;
	}

	public void addField(BSField field) {
		BSField[] target = new BSField[this.fields.length + 1];
		System.arraycopy(this.fields, 0, target, 0, this.fields.length);
		target[target.length - 1] = field;
		this.fields = target;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean isCanInsert() {
		return canInsert;
	}

	public void setCanInsert(Boolean insertButton) {
		this.canInsert = insertButton;
	}

	public Boolean isCanEdit() {
		return canEdit;
	}

	public void setCanEdit(Boolean canEdit) {
		this.canEdit = canEdit;
	}

	public Boolean isCanDelete() {
		return canDelete;
	}

	public void setCanDelete(Boolean canDelete) {
		this.canDelete = canDelete;
	}

}
