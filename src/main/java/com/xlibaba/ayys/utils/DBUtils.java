package com.xlibaba.ayys.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @program: ajax
 * @author: hzx
 * @since: JDK 1.8
 * @create: 2020-10-13 17:15
 **/
public class DBUtils {
    private static volatile DBUtils instance = null;
    private static DataSource ds;
    private final static String DB_PROPERTIES_NAME = "jdbc.properties";

    /**
     * 创建druid连接池
     */
    static {
        try {
            InputStream isRas = DBUtils.class.getClassLoader().getResourceAsStream(DB_PROPERTIES_NAME);
            Properties properties = new Properties();
            properties.load(isRas);
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 單例模式
     * @return
     */
    public static DBUtils getInstance() {
        if (instance == null) {
            synchronized (DBUtils.class) {
                if (instance == null) {
                    instance = new DBUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 获取连接对象
     * @return
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            //加载驱动
            conn = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭
     * @param closeables
     */
    public static void closeAll(AutoCloseable... closeables) {
        for (AutoCloseable ac : closeables) {
            if (ac != null) {
                try {
                    ac.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
