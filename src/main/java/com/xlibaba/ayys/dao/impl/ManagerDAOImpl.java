package com.xlibaba.ayys.dao.impl;

import com.xlibaba.ayys.dao.IManagerDAO;
import com.xlibaba.ayys.utils.chen.CountUtil;

/**
 * @author ChenWang
 * @className ManagerDAOImpl
 * @date 2020/10/20 11:14
 * @since JDK 1.8
 */
public class ManagerDAOImpl implements IManagerDAO {
    private static final String TABLE_NAME = "manager";
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
