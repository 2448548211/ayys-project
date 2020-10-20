package com.xlibaba.ayys.dao.impl;

import com.xlibaba.ayys.dao.IFilmDAO;
import com.xlibaba.ayys.utils.DBUtil;
import com.xlibaba.ayys.utils.chen.CountUtil;

/**
 * @author ChenWang
 * @className FilmDAOImpl
 * @date 2020/10/19 21:43
 * @since JDK 1.8
 */
public class FilmDAOImpl implements IFilmDAO {
    private static final String TABLE_NAME = "film";

    public FilmDAOImpl() {
    }
    @Override
    public Integer getTodayNum(){
        return CountUtil.getTodayNum(TABLE_NAME);
    }
    @Override
    public Integer getYesterdayNum(){
        return CountUtil.getYesterdayNum(TABLE_NAME);
    }
    @Override
    public Integer getWeekNum(){
        return CountUtil.getWeekNum(TABLE_NAME);
    }
    @Override
    public Integer getMonthNum(){
        return CountUtil.getMonthNum(TABLE_NAME);
    }
    @Override
    public Integer getTotalNum(){
        return CountUtil.getTotalNum(TABLE_NAME);
    }
}
