package com.xlibaba.ayys.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author ChenWang
 * @className DButil
 * @date 2020/10/15 19:31
 * @since JDK 1.8
 */
public class DBUtil {
    /**
     *  当前类的单例对象
     *  说明：volatile关键字，这是为了防止指令重排序,单例模式设计中可能会出现这个bug
     *  单例模式可能会指向一个空的实例化对象（指向有极低概率先于初始化构造器）
     */
    private static volatile DBUtil dbUtil = null;
    private static final String DB_PROPERTIES_NAME = "jdbc.properties";
    private static DataSource ds = null;
    static{
        try {
            Properties properties = new Properties();
            //通过类加载器的相对路径加载对应的配置文件
            properties.load(DBUtil.class.getClassLoader().getResourceAsStream(DB_PROPERTIES_NAME));
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            System.out.println("创建数据库连接池失败");
        }
    }
    /**
     * 获取当前类的单例对象
     * @return      当前类的单例对象
     * @author ChenWang
     * @date 2020/10/19 15:54
     */
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
            conn = ds.getConnection();
        } catch (SQLException e) {
            System.out.println("创建数据库连接失败");
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
