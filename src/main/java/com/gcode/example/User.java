package com.gcode.example;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * user
 * Created by Seaven on 2017/3/18.
 */
@Setter
@Getter
public class User {
    private Long id;

    private String userName;

    private String info;
}
