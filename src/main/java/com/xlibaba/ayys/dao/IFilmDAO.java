package com.xlibaba.ayys.dao;

/**
 * @author ChenWang
 * @interfaceName IFilmDAO
 * @date 2020/10/19 21:42
 * @since JDK 1.8
 */
public interface IFilmDAO {
    Integer getTodayNum();

    Integer getYesterdayNum();

    Integer getWeekNum();

    Integer getMonthNum();

    Integer getTotalNum();
}
