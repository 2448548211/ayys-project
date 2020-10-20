package com.xlibaba.ayys.dao.impl;

import com.xlibaba.ayys.dao.IImgDAO;
import com.xlibaba.ayys.utils.chen.CountUtil;

/**
 * @author ChenWang
 * @className ImgDAOImpl
 * @date 2020/10/20 11:06
 * @since JDK 1.8
 */
public class ImgDAOImpl implements IImgDAO {
    private static final String TABLE_NAME = "img";
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
