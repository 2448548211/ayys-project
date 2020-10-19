package com.xlibaba.ayys.dao.impl;

import com.xlibaba.ayys.dao.IBaseDAO;
import com.xlibaba.ayys.utils.ClassTableName;
import com.xlibaba.ayys.utils.DBUtil;
import com.xlibaba.ayys.utils.FieldColName;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ChenWang
 * @className BaseDAOImpl
 * @date 2020/10/15 20:04
 * @since JDK 1.8
 */
public class BaseDAOImpl<T> implements IBaseDAO<T> {
    private static volatile boolean[] ALL_TRUE = null;
    private static final String SELECT = " SELECT ";
    private static final String EMPTY_STRING = "";
    private static final int ONE = 1;
    private static final String INSERT_INTO = " INSERT_INTO ";
    private static final String DELETE = " DELETE ";
    private static final String LEFT_PARENTHESIS = "(";
    private static final String RIGHT_PARENTHESIS = ")";
    private static final String VALUES = " VALUES ";
    private static final String SPACE = " ";
    private static final String FROM = " FROM ";
    private static final String WHERE = " WHERE ";
    private static final String EQUAL_SIGN = "=? ";
    private static final String QUE_MARK = "?";
    private static final String COMMA = ",";
    private static final String AND = " AND ";
    private static final int FIRST = 0;
    private static final String UPDATE = " UPDATE ";
    private static final String SET = " SET ";
    public int delete(Class<T> tClass,T t){
        return commonUpdate(DELETE,tClass,null,null);
    }
    public int deleteById(Class<T> tClass,String id){
        boolean[] terms = new boolean[tClass.getDeclaredFields().length];
        terms[0] = true;
        for (int i = 1; i < terms.length; i++) {
            terms[i] = false;
        }
        return commonUpdate(DELETE,tClass,null,terms,id);
    }
    public int delete(Class<T> tClass,boolean[] terms,Object...objs){
        return commonUpdate(DELETE,tClass,null,terms,objs);
    }
    /**
     * 修改数据
     * @param tClass    指定表
	 * @param t         传入的修改数据
     * @return          修改的行数
     * @author ChenWang
     * @date 2020/10/17 16:36
     */
    @Override
    public int update(Class<T> tClass,T t) {
        Field[] fields = tClass.getDeclaredFields();
        int len = fields.length;
        boolean[] terms = new boolean[len];
        Object[] objs = new Object[len+1];
        terms[0] = true;
        try {
            fields[0].setAccessible(true);
            objs[0] = fields[0].get(t);
            objs[len] = fields[0].get(t);
            for (int i = 1; i < len; i++) {
                terms[i] = false;
                fields[i].setAccessible(true);
                objs[i] = fields[i].get(t);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return update(tClass,null,terms,objs);
    }
    /**
     * 修改数据
     * @param tClass    指定表
	 * @param cols      指定列
	 * @param terms     指定条件
	 * @param objs      条件参数
     * @return          改变行数
     * @author ChenWang
     * @date 2020/10/17 16:36
     */
    @Override
    public int update(Class<T> tClass,boolean[] cols,boolean[] terms,Object... objs) {
        return commonUpdate(UPDATE,tClass,cols,terms,objs);
    }
    /**
     * 全列插入
     * @param t 插入的实例对象
     * @return  影响的函数
     * @author ChenWang
     * @date 2020/10/17 14:50
     */
    @Override
    public int insertSingle(Class<T> tClass, T t){
        Field[] fields = tClass.getDeclaredFields();
        int len = fields.length;
        Object[] objs = new Object[len];
        try {
            for (int i = 0; i < len; i++) {
                fields[i].setAccessible(true);
                objs[i] = fields[i].get(t);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return commonUpdate(INSERT_INTO,tClass,null,null,objs);
    }
    @Override
    public int commonUpdate(String mul, Class<T> tClass, boolean[] cols, boolean[] terms, Object... objs){
        int rSet = 0;
        StringBuffer sql=null;
        Connection conn = null;
        PreparedStatement ps = null;
        switch(mul){
            case INSERT_INTO: insertSql(sql,tClass,cols,ONE);
            case UPDATE: updateSql(sql,tClass,cols,terms);
            default: deleteSql(sql,tClass,terms);
        }
        try {
            conn = DBUtil.getDbUtil().getConnection();
            if (null==sql){
                System.err.println("sql error");
                System.exit(0);
            }
            ps = conn.prepareStatement(sql.toString());
            for (int i = 0; i < objs.length; i++) {
                ps.setObject(i+1,objs[i]);
            }
            rSet = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.getDbUtil().closeAll(ps,conn);
        }
        return rSet;
    }
    /**
     * 依据ID查询数据库
     * @param tClass    指定表对应的实体类的类对象
     * @param id    指定的id
     * @return      返回第一个查询到的数据
     * @author ChenWang
     * @date 2020/10/15 20:47
     */
    @Override
    public T selectById(Class<T> tClass, String id) {
        boolean[] terms = new boolean[tClass.getDeclaredFields().length];
        terms[0] = true;
        for (int i = 1; i < terms.length; i++) {
            terms[i] = false;
        }
        return selectExecute(tClass,true,null,terms,id).get(FIRST);
    }
    /**
     *  查询全表
     * @param tClass        表对应的实体类的类对象
     * @return              行数据对应的实体对象集合
     * @author ChenWang
     * @date 2020/10/17 09:20
     */
    @Override
    public List<T> selectAll(Class<T> tClass) {
        return selectExecute(tClass,false,null,null);
    }
    /**
     * 根据条件查询全表
     * @param tClass    指定的表对应的实体类的类对象
	 * @param terms     指定条件
	 * @param objs      指定条件参数
     * @return          返回结果集合
     * @author ChenWang
     * @date 2020/10/17 11:40
     */
    @Override
    public List<T> selectAll(Class<T> tClass, boolean[] terms, Object...objs) {
        return selectExecute(tClass,false,null,terms,objs);
    }
    @Override
    public List<T> selectExecute(Class<T> tClass, boolean getSingle, boolean[] cols, boolean[] terms, Object...objs) {
        List<T> list = null;
        StringBuffer sql = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rSet = null;
        try{
            list = new ArrayList<>();
            conn = DBUtil.getDbUtil().getConnection();
            Field[] fields = tClass.getFields();
            T t = null;
            //当objs为空时，len为0，避免直接使用objs.length作为判定从而出现空指针异常
            int len = selectSql(sql,tClass,cols,terms)==0?0: objs.length;
            //sql为空时，可能有未知错误
            if(null==sql){
                System.out.println("网络延迟，请稍后再试");
                System.err.println("select系统内存出现错误");
                System.exit(0);
            }
            ps = conn.prepareStatement(sql.toString());
            for (int i=0;i<len;i++){
                ps.setObject(i+1,objs[i]);
            }
            rSet = ps.executeQuery();
            FieldColName annotation;
            String colName;
            while(rSet.next()){
                t = tClass.newInstance();
                for (Field f:fields){
                    annotation = f.getAnnotation(FieldColName.class);
                    colName = (annotation==null||"".equals(annotation.value())) ? f.getName():annotation.value();
                    f.set(t,rSet.getObject(colName));
                }
                list.add(t);
                if(getSingle){
                    break;
                }
            }
        } catch (SQLException | InstantiationException | IllegalAccessException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.getDbUtil().closeAll(rSet,ps,conn);
        }
        return list;
    }
    private static <E> void deleteSql(StringBuffer sql, Class<E> eClass, boolean[] terms) {
        sql = new StringBuffer(DELETE);
        sql.append(FROM);
        sql.append(eClass.getDeclaredAnnotation(ClassTableName.class).value());
        if(isChoose(terms)==0){
            return;
        }
        sql.append(WHERE);
        Field[] fields = eClass.getDeclaredFields();
        colContact(sql,terms,fields,EQUAL_SIGN,AND);
    }
    private static <E> void updateSql(StringBuffer sql,Class<E> eClass,boolean[] cols,boolean[]terms){
        sql = new StringBuffer(UPDATE);
        sql.append(eClass.getDeclaredAnnotation(ClassTableName.class).value());
        sql.append(SET);
        Field[] fields = eClass.getDeclaredFields();
        cols = isChoose(cols)!=0?cols:getAllTrue(eClass);
        /*if(!isChoose(cols)){
            //如果没有选取展示的字段名，则默认全选
            boolean[] allCol = new boolean[fields.length];
            Arrays.fill(allCol,true);
            colContact(sql,allCol,fields,SPACE,COMMA);
        }else{
            colContact(sql,cols,fields,EQUAL_SIGN,COMMA);
        }*/
        colContact(sql,cols,fields,SPACE,COMMA);
        sql.append(WHERE);
        terms = isChoose(terms)!=0?terms:getAllTrue(eClass);
        colContact(sql,terms,fields,EQUAL_SIGN,COMMA);
    }
    /**
     * 全列名插入sql
     * @param sql   指定的sql语句
	 * @param eClass    指定的要插入的表对应的实体类的类对象
     * @author ChenWang
     * @date 2020/10/17 14:24
     */
    private static <E> void insertSql(StringBuffer sql,Class<E> eClass){
       /* sql = new StringBuffer(INSERT_INTO);
        sql.append(eClass.getAnnotation(ClassTableName.class).value());
        sql.append(LEFT_PARENTHESIS);
        Field[] fields = eClass.getDeclaredFields();
        colContact(sql, getAllTrue(eClass), fields, SPACE, COMMA);
        sql.append(RIGHT_PARENTHESIS);
        sql.append(VALUES);
        sql.append(LEFT_PARENTHESIS);
        FieldColName annotation = null;
        String colName = null;
        for (Field f:fields) {
            sql.append(QUE_MARK).append(COMMA);
        }
        sql.deleteCharAt(sql.lastIndexOf(COMMA));
        sql.append(RIGHT_PARENTHESIS);
        sql.trimToSize();*/
        insertSql(sql,eClass,null,ONE);
    }
    /**
     * 一次性插入指定数目个数据，通过sql语句实现一次性多插入
     * 还可以通过多次调用插入单个数据的方法来实现多次插入--》效率低下
     * @param sql       指定的插入语句
	 * @param eClass    对应的表
	 * @param cols  选择插入的列
	 * @param num   表示插入的数目
     * @author ChenWang
     * @date 2020/10/17 17:23
     */
    private static <E> void insertSql(StringBuffer sql,Class<E> eClass,boolean[] cols,int num){
        sql = new StringBuffer(INSERT_INTO);
        sql.append(eClass.getAnnotation(ClassTableName.class).value());
        sql.append(LEFT_PARENTHESIS);
        Field[] fields = eClass.getDeclaredFields();
        int choose = isChoose(cols);
        cols = choose!=0?cols:getAllTrue(eClass);
        colContact(sql, cols, fields, SPACE, COMMA);
        sql.append(RIGHT_PARENTHESIS);
        sql.append(VALUES);
        for(int i=0;i<num;i++){
            sql.append(LEFT_PARENTHESIS);
            for (int j=0;j<choose;j++) {
                sql.append(QUE_MARK).append(COMMA);
            }
            sql.deleteCharAt(sql.lastIndexOf(COMMA));
            sql.append(RIGHT_PARENTHESIS);
            sql.append(COMMA);
        }
        sql.deleteCharAt(sql.lastIndexOf(COMMA));
        sql.trimToSize();
    }
    /**
     * select 字段名 from TABLE (TABLE取自tClass对应值) 的优化-->select 所有字段名 比 select * 更快
     * @param sql       指定的sql语句
     * @param eClass    指定的表对应的实体类的类对象
     * @param cols      确定是否启用字段值
     * @param terms     确定是否启用该字段值条件判断
     * @author ChenWang
     * @date 2020/10/16 20:36
     *  考虑到反射的时候所取得的属性值是按代码顺序从上往下的所以用布尔数组来确定是否取值，而不是用一个
     *  map映射来确定
     *  public static<T> StringBuffer select(Class<T> tClass,map<String,Boolean> cols,map<String,Boolean> cols terms)
     */
    private static <E> int selectSql(StringBuffer sql,Class<E> eClass,boolean[] cols,boolean[] terms){
        sql = new StringBuffer(SELECT);
        int existTerms = 0;
        Field[] fields = eClass.getDeclaredFields();
        cols = isChoose(cols)!=0?cols:getAllTrue(eClass);
        colContact(sql,cols,fields,SPACE,COMMA);
        sql.append(FROM);
        sql.append(eClass.getAnnotation(ClassTableName.class).value());
        sql.append(WHERE);
        existTerms = colContact(sql,terms,fields,EQUAL_SIGN,AND);
        if(isChoose(terms)==0){
            //如果WHERE后面没有任何条件，则删除WHERE
            sql.deleteCharAt(sql.lastIndexOf(WHERE));
        }
        sql.trimToSize();
        return existTerms;
    }
    /**
     * 拼接字符串    三种情况 1. (列名,列名...)  2.(列名=?)   3.(?,?...)
     *              1,2适用方法多,3只有插入时使用;
     *              所以封装了1,2的通用方法
     * @param sql       sql可变字符串
	 * @param cols      判定是否拼接的布尔数组
	 * @param fields    属性名
	 * @param attach    字段名后缀
	 * @param con   连接词或连接字符
     * @return      拼接的字段名数目
     * @author ChenWang
     * @date 2020/10/17 09:16
     */
    private static int colContact(StringBuffer sql, boolean[] cols, Field[] fields, String attach, String con) {
        //记录选择的字段名的数目
        int cho = 0;
        String colName;
        FieldColName annotation;
        for (int i = 0; i < fields.length; i++) {
            //判定是否要查询该字段名
            if (cols[i]) {
                annotation = fields[i].getAnnotation(FieldColName.class);
                colName = (annotation == null || EMPTY_STRING.equals(annotation.value())) ? fields[i].getName() : annotation.value();
                sql.append(colName);
                sql.append(attach);
                sql.append(con);
                cho++;
            }
        }
        if (sql != null) {
            sql.deleteCharAt(sql.lastIndexOf(con));
        }
        return cho;
    }
    /**
     * 判定是否有选取列
     * @param bs    选取列的判定数组
     * @return  返回所选取的列数，0表示没有选取或者为空
     * @author ChenWang
     * @date 2020/10/17 15:24
     */
    private static int isChoose(boolean[] bs){
        int choose = 0;
        if (bs!=null){
            for (boolean b:bs){
                if (b){
                    choose++;
                }
            }
        }
        return choose;
    }
    //懒汉模式
    //双检查锁机制
    /**
     * 获得全为true的布尔数组
     * @param eClass    类对象的属性个数，决定布尔数组的长度
     * @return      布尔数组
     * @author ChenWang
     * @date 2020/10/17 16:06
     */
    private static <E> boolean[] getAllTrue(E eClass){
        if(null==ALL_TRUE){
            synchronized (BaseDAOImpl.class){
                if(null==ALL_TRUE){
                    ALL_TRUE = new boolean[eClass.getClass().getDeclaredFields().length];
                    Arrays.fill(ALL_TRUE,true);
                }
            }
        }
        return ALL_TRUE;
    }
}
