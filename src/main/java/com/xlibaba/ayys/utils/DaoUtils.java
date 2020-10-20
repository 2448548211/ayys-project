package com.xlibaba.ayys.utils;

import com.xlibaba.ayys.utils.DBUtil;
import com.xlibaba.ayys.utils.FieldColName;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: web-movise
 * @author: hzx
 * @since: JDK 1.8
 * @create: 2020-10-16 15:00
 **/
public class DaoUtils {

    private static DBUtil dbUtil = DBUtil.getDbUtil();

    /**
     * 查询所有数据
     * @param clz       数据表对应的实体类反射对象
     * @param tabName   要查询的表名
     * @param <T>       数据表对应的实体类
     * @return
     */
    public static <T> List<T> selectAll(Class<T> clz,String tabName) {
        //拼接sql语句
        StringBuffer sql_select = new StringBuffer("SELECT ");
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
            sql_select = sql_select.append(name).append(",");
        }
        sql_select.deleteCharAt(sql_select.lastIndexOf(",")).append(" FROM "+tabName);
        System.out.println(sql_select);
        //查询数据
        Connection conn = dbUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<T> list = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql_select.toString());
            rs = ps.executeQuery();
            while (rs.next()) {
                try {
                    T t = clz.newInstance();
                    for (int i = 0; i < fields.length; i++) {
                        //取消访问权限
                        fields[i].setAccessible(true);
                        //获取字段数据
                        Object value = rs.getObject(i+1);
                        //给对象中的属性赋值
                        fields[i].set(t,value);
                    }
                    list.add(t);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            dbUtil.closeAll(conn,ps,rs);
        }
        return list;
    }

    public <T> int insertList(Class<T> clz,String sql) {

        return 0;
    }

    /**
     * 通过ID查询数据
     * @param clz       数据表对应的实体类反射对象
     * @param tabName   要查询的表名
     * @param <T>       数据表对应的实体类
     * @return
     */
    public static <T> T selectByID(Class<T> clz,String tabName,String idName,String id) {
        String sql_select = "SELECT ";
        sql_select = sql_select.concat(getSqlSelect(clz)).concat(" FROM "+tabName+" WHERE is_delete=1 AND "+idName+"='"+id+"'");
        System.out.println(sql_select);
        Connection conn = dbUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        //通过反射对象获取所有属性对象
        Field[] fields = clz.getDeclaredFields();
        T t = null;
        try {
            ps = conn.prepareStatement(sql_select.toString());
            rs = ps.executeQuery();
            if (rs.next()) {
                try {
                    t = clz.newInstance();
                    for (int i = 0; i < fields.length; i++) {
                        //取消访问权限
                        fields[i].setAccessible(true);
                        //获取字段数据
                        Object value = rs.getObject(i+1);
                        //给对象中的属性赋值
                        fields[i].set(t,value);
                    }
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            dbUtil.closeAll(conn,ps,rs);
        }
        return t;
    }

    /**
     * 获取实体类对应数据表的所有属性名
     * @param clz
     * @param <T>
     * @return
     */
    private static <T> String getSqlSelect(Class<T> clz) {
        //拼接sql语句
        StringBuffer sql_select = new StringBuffer("");
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
            sql_select = sql_select.append(name).append(",");
        }
        sql_select.deleteCharAt(sql_select.lastIndexOf(","));
        return sql_select.toString();
    }
}
