package com.gcode.template;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.HashMap;

import com.gcode.base.BeanClass;
import com.gcode.db.DataBase;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

/**
 * gcode
 * Created by Seaven on 2017/3/25.
 */
public class Gcode {

    private final Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);

    private DataBase db;

    public Gcode(String url, String userName, String password) {
        db = new DataBase(url, userName, password);

        try {
            cfg.setDirectoryForTemplateLoading(new File("src/main/resources/com/gcode/template"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setDateFormat("yyyy-MM-dd");
    }

    public void genBeanCode(String table, String packageName) {
        BeanClass beanClass = new BeanClass();

        beanClass.conventTableInfo(db.getTableInfo(table));

        beanClass.setPackageName(packageName);
        String out = getPackageDir(packageName) + "/" + beanClass.getClassName() + ".java";

        try {
            genCode("bean.ftl", beanClass, new OutputStreamWriter(new FileOutputStream(out)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void genCode(String templateName, Object data, OutputStreamWriter osw) throws IOException,
            TemplateException {
        HashMap<String, Object> root = new HashMap<String, Object>();
        root.put("data", data);

        root.put("createDate", new Date());

        Template template = cfg.getTemplate(templateName);

        template.process(root, osw);

        System.out.println("generate code complete");
    }

    private String getPackageDir(String packageDir) {
        packageDir = packageDir.replace(".", "/");

        File file = new File(packageDir);
        if (!file.exists()) {
            file.mkdirs();
        }

        return packageDir;
    }
}
