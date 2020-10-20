package com.xlibaba.ayys.utils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ran
 * @since JDK 1.8
 *
 *     Dao实现层的工具类
 */
public class DbManager {
    /**
     *  对更新数据库的 dao 实现层操作方法进行封装处理
     * @param sql   sql语句
     * @param obj   给占位符赋值的参数
     * @return      受影响行数
     */
    public static Integer commonUpdate(String sql,Object ... obj){
        Connection connection = null;
        PreparedStatement ps = null;
        int rSet = 0;
        try {
            connection = DBUtils.getConnection();
            ps = connection.prepareStatement(sql);
            //给占位符赋值
            for (int i = 0;i < obj.length;i++){
                ps.setObject(i+1,obj[i]);
            }
            //执行sql语句返回影响行数
            rSet = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeAll(ps,connection);
        }
        return rSet;
    }

    /**
     *  查询数据总行数
     * @param sql   sql语句
     * @return      总行数
     */
    public static Integer commonCount(String sql){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rSet = null;
        int count = 0;
        try {
            connection = DBUtils.getConnection();
            ps = connection.prepareStatement(sql);
            rSet = ps.executeQuery();
            if(rSet.next()){
                count = rSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(rSet,ps,connection);
        }
        return count;
    }

    /**
     *  公平的查询方法
     * @param sql       sql语句
     * @param clazz     需要创建实例的反射对象
     * @param obj       给占位符赋值的参数
     * @param <T>       泛型
     * @return          集合
     */
    public static <T> List<T> commonQuery(String sql, Class<T> clazz, Object ... obj) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rSet = null;
        List<T> list = new ArrayList<>();
        try {
            connection = DBUtils.getConnection();
            ps = connection.prepareStatement(sql);
            //给占位符赋值
            if(obj != null){
                for (int i = 0;i < obj.length;i++){
                   ps.setObject(i+1,obj[i]);
                }
            }
            rSet = ps.executeQuery();
            while(rSet.next()){
                //创建对象
                T t = clazz.newInstance();
                //1.通过反射对象获取所有属性对象
                Field[] fields = clazz.getDeclaredFields();
                for (Field field :fields){
                    //2.取消访问权限
                    field.setAccessible(true);
                    //3.获取属性名
                    String name = field.getName();
                    //4.通过属性名当成字段名获取字段数据
                    Object value = rSet.getObject(name);
                    //5.给对象中的属性赋值 -- 参数为指定对象和修改的值
                    field.set(t,value);
                }
                list.add(t);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeAll(rSet,ps,connection);
        }
        return list;
    }
}
