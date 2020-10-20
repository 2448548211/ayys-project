package com.xlibaba.ayys.dao;

/**
 * @author ChenWang
 * @interfaceName IImg
 * @date 2020/10/20 11:06
 * @since JDK 1.8
 */
public interface IImgDAO {
    Integer getTodayNum();

    Integer getYesterdayNum();

    Integer getWeekNum();

    Integer getMonthNum();

    Integer getTotalNum();
}
