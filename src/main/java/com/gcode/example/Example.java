package com.gcode.example;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * test
 * Created by Seaven on 2017/3/22.
 */
public class Example {

    public static void main(String[] args) throws IOException {

        String resource = "mybatis-example.xml";

        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader(resource));


        SqlSession session = sqlSessionFactory.openSession();

        TestDao dao = session.getMapper(TestDao.class);

        User user = dao.getUser(1L);

        System.out.println(user);

        String str = "" + "";
    }

}
