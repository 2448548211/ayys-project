package com.xlibaba.ayys.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author ChenWang
 * @className DButil
 * @date 2020/10/15 19:31
 * @since JDK 1.8
 */
public class DBUtil {
    /*
    volatile关键字，这是为了防止指令重排序,单例模式设计中可能会出现这个bug
    单例模式可能会指向一个空的实例化对象（指向有极低概率先于初始化构造器）
    */
    private static volatile DBUtil dbUtil;
    private static String CLASSNAME = null;
    private static String URL = null;
    private static String USERNAME = null;
    private static String PASSWORD = null;
    static{
        try {
            Properties properties = new Properties();
            //通过类加载器的相对路径加载对应的配置文件
            properties.load(DBUtil.class.getClassLoader().getResourceAsStream("jdbc.properties"));
            CLASSNAME = properties.getProperty("classname");
            URL = properties.getProperty("url");
            USERNAME = properties.getProperty("username");
            PASSWORD = properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static DBUtil getDbUtil(){
        //单例模式的懒汉实现--线程安全
        //通过设置同步代码块，使用DCL双检查锁机制
        //使用双检查锁机制成功的解决了单例模式的懒汉实现的线程不安全问题和效率问题
        //DCL 也是大多数多线程结合单例模式使用的解决方案
        if(null==dbUtil){
            synchronized(DBUtil.class){
                if(null==dbUtil){
                    dbUtil = new DBUtil();
                }
            }
        }
        return dbUtil;
    }
    /**
     * 获取指定数据库连接
     * @return  数据库连接Connection
     * @author ChenWang
     * @date 2020/10/15 19:51
     */
    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(CLASSNAME);
            conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }
    /**
     * 释放资源
     * @param closeables    指定的需要关闭的资源
     * @author ChenWang
     * @date 2020/10/15 19:53
     */
    public void closeAll(AutoCloseable...closeables){
        for (AutoCloseable closeable:closeables){
            if(closeable!=null){
                try {
                    closeable.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
