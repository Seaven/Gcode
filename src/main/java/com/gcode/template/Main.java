package com.gcode.template;

/**
 *
 * Created by Seaven on 2017/3/25.
 */
public class Main {

    public static void main(String[] args) {
        Gcode gcode = new Gcode("jdbc:mysql://localhost:3306/test", "root", "123456");

        gcode.genBeanCode("user", "com.seaven.test");
    }
}
