package com.xlibaba.ayys.dao;

/**
 * @author ChenWang
 * @interfaceName IUser
 * @date 2020/10/20 11:15
 * @since JDK 1.8
 */
public interface IUserDAO {
    Integer getTodayNum();

    Integer getYesterdayNum();

    Integer getWeekNum();

    Integer getMonthNum();

    Integer getTotalNum();
}
