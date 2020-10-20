package com.xlibaba.ayys.dao;

/**
 * @author ChenWang
 * @interfaceName IIManager
 * @date 2020/10/20 11:13
 * @since JDK 1.8
 */
public interface IManagerDAO {
    Integer getTodayNum();

    Integer getYesterdayNum();

    Integer getWeekNum();

    Integer getMonthNum();

    Integer getTotalNum();
}
