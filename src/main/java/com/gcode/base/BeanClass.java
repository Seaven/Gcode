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

}
