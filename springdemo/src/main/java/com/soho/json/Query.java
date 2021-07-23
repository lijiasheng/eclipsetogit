package com.soho.json;

import java.util.List;

import org.apache.commons.collections4.map.LinkedMap;

public class Query {
    private String id;
    private String key;
    private String tableName;
    private String className;
    private List<LinkedMap<String, Object>> column; 
    private List<Column> columnList;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public List<LinkedMap<String, Object>> getColumn() {
		return column;
	}
	public void setColumn(List<LinkedMap<String, Object>> column) {
		this.column = column;
	}
	public List<Column> getColumnList() {
		return columnList;
	}
	public void setColumnList(List<Column> columnList) {
		this.columnList = columnList;
	}
}