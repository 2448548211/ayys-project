package com.xlibaba.ayys.dao.impl;

import com.xlibaba.ayys.dao.FilmDao;
import com.xlibaba.ayys.entity.Film;
import com.xlibaba.ayys.utils.DbManager;

import java.util.List;

public class FilmDaoImpl implements FilmDao {
    @Override
    public List<Film> selectList() {
        String sql = "select * from film";
        return DbManager.commonQuery(sql,Film.class);
    }
}
