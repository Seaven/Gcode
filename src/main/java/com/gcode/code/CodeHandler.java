package com.gcode.code;

import com.gcode.base.BeanClass;
import com.gcode.base.Parameter;
import com.gcode.db.TableInfo;

/**
 * code handler
 * Created by Seaven on 2017/3/26.
 */
public abstract class CodeHandler {

    public abstract String conventLowerName(String name);

    public abstract String conventUpperName(String name);

    public abstract String conventImporter(String type);

    public abstract String conventType(String type);

    public BeanClass conventTableInfo(TableInfo tableInfo) {

        BeanClass beanClass = new BeanClass();
        beanClass.setClassName(conventUpperName(tableInfo.getTableName()));
        beanClass.setTableName(tableInfo.getTableName());

        for (int i = 0; i < tableInfo.getColumns().size(); i++) {
            String columnName = tableInfo.getColumns().get(i);

            Parameter p = new Parameter();
            p.setName(conventLowerName(columnName));
            p.setUpperName(conventUpperName(columnName));
            p.setType(conventType(tableInfo.getColumnsType().get(i)));
            p.setColumnName(columnName);

            beanClass.setParameters(p);

            beanClass.setImporters(conventImporter(tableInfo.getColumnsType().get(i)));

            for (int k = 0; k < tableInfo.getPrimaryColumns().size(); k++) {
                if (columnName.equals(tableInfo.getPrimaryColumns().get(k))) {
                    p.setPrimaryKey(true);
                }
            }
        }

        return beanClass;
    }
}
