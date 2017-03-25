package com.gcode.code;

import java.util.HashMap;
import java.util.Map;

import com.gcode.base.BeanClass;
import com.gcode.base.Parameter;
import com.gcode.db.TableInfo;

/**
 * java
 * Created by Seaven on 2017/3/26.
 */
public class JavaCodeHandler extends CodeHandler {
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


    public String conventLowerName(String name) {
        String lower = conventUpperName(name);

        return lower.substring(0, 1).toLowerCase() + lower.substring(1);
    }

    public String conventUpperName(String name) {
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

    public String conventImporter(String type) {
        return PACKAGE_MAP.get(type.toUpperCase());
    }

    public String conventType(String type) {
        return TYPE_MAP.get(type.toUpperCase());
    }
}
