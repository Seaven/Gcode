package com.gcode.example;

import org.apache.ibatis.annotations.Select;

/**
 *
 * Created by Seaven on 2017/3/18.
 */
public interface UserDao {

    @Select("select * from user u where u.id = #{id}")
    public User getUser(Long id);

    public void saveUser(User user);

}
