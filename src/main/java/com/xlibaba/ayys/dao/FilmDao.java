package com.xlibaba.ayys.dao;

import com.xlibaba.ayys.entity.Film;

import java.util.List;

public interface FilmDao {

    /**
     * 获取字段数据
     * @return 数据集合
     */
    List<Film> selectList();
}
