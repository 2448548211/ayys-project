package com.xlibaba.ayys.dao;

/**
 * @author ChenWang
 * @interfaceName IInformationDAO
 * @date 2020/10/20 11:07
 * @since JDK 1.8
 */
public interface IInformationDAO {
    Integer getTodayNum();

    Integer getYesterdayNum();

    Integer getWeekNum();

    Integer getMonthNum();

    Integer getTotalNum();
}
