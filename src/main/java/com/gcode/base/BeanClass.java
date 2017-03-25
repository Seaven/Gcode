package com.gcode.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.gcode.db.TableInfo;

/**
 * bean class
 * Created by Seaven on 2017/3/25.
 */
public class BeanClass {

    {
        TYPE_MAP.put("VARCHAR", "Long");
        TYPE_MAP.put("INT", "Long");
        TYPE_MAP.put("BIGINT", "Long");
        TYPE_MAP.put("BIGINT", "Long");
        PACKAGE_MAP.put("DATE", "Date");
        PACKAGE_MAP.put("TIMESTAMP", "Date");

        PACKAGE_MAP.put("DATE", "java.util.Date");
        PACKAGE_MAP.put("TIMESTAMP", "java.util.Date");
    }

    private final static Map<String, String> TYPE_MAP = new HashMap();

    private final static Map<String, String> PACKAGE_MAP = new HashMap();

    private String tableName;

    private String className;

    private String packageName;

    private Set<String> importers = new HashSet<String>();

    private List<Parameter> parameters = new ArrayList<Parameter>();

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Set<String> getImporters() {
        return importers;
    }

    public void setImporters(String importers) {
        if (null != importers) {
            this.importers.add(importers);
        }
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(Parameter parameter) {
        if (null != parameter) {
            this.parameters.add(parameter);
        }
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getToParamStr() {
        StringBuilder sb = new StringBuilder();
        sb.append("\"").append(className).append("[\"\n");
        for (Parameter param : parameters) {
            sb.append("            ")
                    .append("+ \"").append(param.getName())
                    .append("=").append("\"")
                    .append(" + ").append("this.")
                    .append(param.getName()).append("\n");

        }
        sb.append("            + \"]\";");

        return sb.toString();
    }

    public void conventTableInfo(TableInfo tableInfo) {
        setClassName(conventUpperName(tableInfo.getTableName()));
        setTableName(tableInfo.getTableName());

        for (int i = 0; i < tableInfo.getColumns().size(); i++) {
            String columnName = tableInfo.getColumns().get(i);

            Parameter p = new Parameter();
            p.setName(conventLowerName(columnName));
            p.setUpperName(conventUpperName(columnName));
            p.setType(conventType(tableInfo.getColumnsType().get(i)));
            p.setColumnName(columnName);

            this.setParameters(p);

            this.setImporters(conventImporter(tableInfo.getColumnsType().get(i)));

            for (int k = 0; k < tableInfo.getPrimaryColumns().size(); k++) {
                if (columnName.equals(tableInfo.getPrimaryColumns().get(k))) {
                    p.setPrimaryKey(true);
                }
            }
        }

    }

    private String conventLowerName(String name) {
        String lower = conventUpperName(name);

        return lower.substring(0, 1).toLowerCase() + lower.substring(1);
    }

    private String conventUpperName(String name) {
        StringBuilder sb = new StringBuilder();
        if (name.contains("_")) {
            String[] nms = name.split("_");
            for (String nm : nms) {
                sb.append(nm.substring(0, 1).toUpperCase());
                sb.append(nm.substring(1).toLowerCase());
            }
            return sb.toString();
        }

        sb.append(name.substring(0, 1).toUpperCase());
        sb.append(name.substring(1).toLowerCase());

        return sb.toString();
    }

    private String conventImporter(String type) {
        return PACKAGE_MAP.get(type.toUpperCase());
    }

    private String conventType(String type) {
        return TYPE_MAP.get(type.toUpperCase());
    }

}
