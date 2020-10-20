package com.xlibaba.ayys.dao;

import java.util.List;

/**
 * @author ChenWang
 * @interfaceName BaseDAO
 * @date 2020/10/15 20:04
 * @since JDK 1.8
 */
public interface IBaseDAO<T>{
    int update(Class<T> tClass, T t);

    int update(Class<T> tClass, boolean[] cols, boolean[] terms, Object... objs);

    int insertSingle(Class<T> tClass, T t);

    int commonUpdate(String mul, Class<T> tClass, boolean[] cols, boolean[] terms, Object... objs);

    T selectById(Class<T> tClass, String id);

    List<T> selectAll(Class<T> tClass);

    List<T> selectAll(Class<T> tClass, boolean[] terms, Object... objs);

    List<T> selectExecute(Class<T> tClass, boolean getSingle, boolean[] cols, boolean[] terms, Object... objs);
}
