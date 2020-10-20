package com.xlibaba.ayys.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
    private static DataSource ds = null;
    private static final String DB_PROPERTIES_NAME = "jdbc.properties";
    private static final String EMPTY_STRING = "";
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

    /**
     *
     * @param sql
     * @param params
     * @return
     */
    public int executeUpdate(String sql, Object... params){
        int count = 0;
        Connection conn = getConnection();
        PreparedStatement ps = null;
        try {
            //设置成自动提交事务,将该连接的所有 SQL语句都转成一个事务进行提交
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            setParams(ps, params);
            //使用Statement对象发送SQL语句
            count = ps.executeUpdate();
            getConnection().commit();
        } catch (SQLException e) {
            try {
                //事务回滚并且释放数据库资源
                conn.rollback();
            } catch (SQLException throwables) {
                System.out.println("回滚，释放资源失败");
            }
        } finally {
            closeAll(ps, conn);
        }
        return count;
    }
    private void setParams(PreparedStatement ps, Object... params) throws SQLException {
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i+1, params[i]);
            }
        }
    }

    /**
     *
     * @param sql
     * @param clazz
     * @param params
     * @param <T>
     * @return
     */
    public <T> List<T> excuteQuery(String sql,Class<T> clazz, Object...params){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rSet = null;
        List<T> list = null;
        try{
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            setParams(ps,params);
            rSet = ps.executeQuery();
            Field[] fields = clazz.getDeclaredFields();
            T t = null;
            list = new ArrayList<>();
            while(rSet.next()){
                t = clazz.newInstance();
                for (Field field:fields){
                    field.setAccessible(true);
                    field.set(t,rSet.getObject(getColName(field)));
                }
                list.add(t);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException throwables) {
            throwables.printStackTrace();
            System.out.println("网络延迟，请稍后再试");
        }finally {
            closeAll(rSet,ps,conn);
        }
        return list;
    }

    /**
     *
     * @param field
     * @return
     */
    private String getColName(Field field){
        FieldColName annotation = field.getDeclaredAnnotation(FieldColName.class);
        return (annotation==null||EMPTY_STRING.equals(annotation.value()))?field.getName(): annotation.value();
    }

    /**
     * 获取包含所有字段名的字符串 (字符串前后包含空格)
     * @param clz   实体类反射对象
     * @param <T>
     * @return
     */
    public <T>  String getColNameClass(Class<T> clz){
        //拼接sql语句
        StringBuffer sql_Name = new StringBuffer(" ");
        //通过反射对象获取所有属性对象
        Field[] fields = clz.getDeclaredFields();
        String name = null;
        for (Field f : fields) {
            FieldColName ann = f.getAnnotation(FieldColName.class);
            if (ann == null || ann.value().equals("")){
                name = f.getName();
            } else {
                name = ann.value();
            }
            sql_Name = sql_Name.append(name).append(",");
        }
        sql_Name.deleteCharAt(sql_Name.lastIndexOf(","));
        return sql_Name.toString();
    }
}
