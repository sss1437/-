package com.chatRoom.utils;

import java.sql.*;
import java.util.Properties;

/**
 * @Description:
 * @Author: zdd
 * @Date: 2019/8/8
 */
public class JDBCUtils1 {
    private static String driverName;
    private static String url;
    private static String userName;
    private static String password;

    //类加载时就可以获取
    static {
        Properties properties = CommUtils.loadProperties("db.properties");
        driverName = properties.getProperty("driverName");
        url = properties.getProperty("url");
        userName = properties.getProperty("userName");
        password = properties.getProperty("password");

        //1.加载驱动
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            System.err.println("加载数据库驱动出错");
        }
    }

    //获取数据库连接
    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(url,userName,password);
        } catch (SQLException e) {
            System.err.println("获取数据库连接出错");
        }
        return null;
    }

    //关闭资源
    public static void closeResources(Connection connection,Statement statement){
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void closeResources(Connection connection,Statement statement,ResultSet resultSet){
        closeResources(connection,statement);
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
