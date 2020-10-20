package com.xlibaba.ayys.utils.chen;

import com.xlibaba.ayys.utils.ClassTableName;
import com.xlibaba.ayys.utils.DBUtil;

import java.util.Calendar;

/**
 * @author ChenWang
 * @className DBUtil
 * @date 2020/10/20 09:36
 * @since JDK 1.8
 */
public class CountUtil {
    private static final Integer TODAY = 0;
    private static final Integer YESTERDAY = -1;
    private static final Integer TOMORROW = 1;
    private static final Integer DAY_OF_WEEK = 7;
    /**
     * 获取从起始日期到截止日期之间的数据数量，包前不包后
     * @param tableName     表名
	 * @param from_time     起始日期
	 * @param to_time       截止日期
     * @return          数目
     * @author ChenWang
     * @date 2020/10/20 10:03
     */
    public static Integer getStatisticNum(String tableName,String from_time,String to_time){
        return DBUtil.getDbUtil().executeUpdate("SELECT COUNT(id) FROM "+tableName+" WHERE create_time>="
        +from_time+" AND create_time <="+to_time);
    }
    public static Integer getTotalNum(String tableName){
        Calendar calendar = Calendar.getInstance();
        String to = calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+(calendar.get(Calendar.DATE)+TOMORROW);
        return DBUtil.getDbUtil().executeUpdate("SELECT COUNT(id) FROM "+tableName+" WHERE create_time<="+ to);
    }
    public static Integer commonCount(String tableName,int pre,int to){
        Calendar calendar = Calendar.getInstance();
        String fromDay = calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+(calendar.get(Calendar.DATE)+pre);
        String toDay = calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+(calendar.get(Calendar.DATE)+to);
        return getStatisticNum(tableName,fromDay,toDay);
    }
    public static Integer getTodayNum(String tableName){
        return commonCount(tableName,TODAY,TOMORROW);
    }
    public static Integer getYesterdayNum(String tableName){
        return commonCount(tableName,YESTERDAY,TODAY);
    }
    public static Integer getWeekNum(String tableName){
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        day = day==0?DAY_OF_WEEK:day;
        return commonCount(tableName,day,TOMORROW);
    }
    public static Integer getMonthNum(String tableName){
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return commonCount(tableName,day,TOMORROW);
    }

}
