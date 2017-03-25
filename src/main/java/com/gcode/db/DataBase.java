package com.gcode.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * data base
 * Created by Seaven on 2017/3/25.
 */
public class DataBase {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String url;

    private String userName;

    private String password;

    private Connection connection;

    public DataBase(String url, String userName, String password) {
        this.url = url;
        this.userName = userName;
        this.password = password;

        try {
            connection = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public TableInfo getTableInfo(String tableName) {
        String sql = "select * from " + tableName + " where 1 = 0";
        TableInfo tableInfo = new TableInfo();
        try {
            tableInfo.setTableName(tableName);

            PreparedStatement statement = this.connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            ResultSetMetaData metaData = resultSet.getMetaData();

            DatabaseMetaData dbMetaData = this.connection.getMetaData();

            // primary key
            ResultSet mst = dbMetaData.getPrimaryKeys(null, null, tableName);
            while (mst.next()) {
                if ("PRIMARY".equals(mst.getString("PK_NAME"))) {
                    tableInfo.setPrimaryColumns(mst.getString("COLUMN_NAME"));
                }
            }

            for(int i = 1; i <= metaData.getColumnCount(); i++) {
                tableInfo.setColumns(metaData.getColumnName(i), metaData.getColumnTypeName(i));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tableInfo;
    }

    public static void main(String[] args) {
        DataBase db = new DataBase("jdbc:mysql://localhost:3306/test", "root", "123456");
        db.getTableInfo("hello");
    }
}
