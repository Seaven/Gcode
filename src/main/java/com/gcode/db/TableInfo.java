package com.gcode.db;

import java.util.ArrayList;
import java.util.List;

/**
 * test
 * Created by Seaven on 2017/3/25.
 */
public class TableInfo {

    private String tableName;

    private List<String> columns = new ArrayList<String>();

    private List<String> columnsType = new ArrayList<String>();

    private List<String> primaryColumns = new ArrayList<String>();

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(String columns, String columnType) {
        this.columns.add(columns);
        this.columnsType.add(columnType);
    }

    public List<String> getColumnsType() {
        return columnsType;
    }

    public List<String> getPrimaryColumns() {
        return primaryColumns;
    }

    public void setPrimaryColumns(String primaryColumns) {
        this.primaryColumns.add(primaryColumns);
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
