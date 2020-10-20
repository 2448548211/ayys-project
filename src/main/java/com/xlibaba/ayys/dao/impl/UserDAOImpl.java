package com.xlibaba.ayys.dao.impl;

import com.xlibaba.ayys.dao.IUserDAO;
import com.xlibaba.ayys.utils.chen.CountUtil;

/**
 * @author ChenWang
 * @className UserDAOImpl
 * @date 2020/10/20 11:16
 * @since JDK 1.8
 */
public class UserDAOImpl implements IUserDAO {
    private static final String TABLE_NAME = "user";
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
