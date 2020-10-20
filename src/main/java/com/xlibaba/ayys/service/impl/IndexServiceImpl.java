package com.xlibaba.ayys.service.impl;

import com.xlibaba.ayys.dao.*;
import com.xlibaba.ayys.dao.impl.*;
import com.xlibaba.ayys.service.entity.IndexPage;
import com.xlibaba.ayys.service.IIndexService;
import com.xlibaba.ayys.service.entity.IndexServer;
import com.xlibaba.ayys.service.entity.IndexStatistic;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenWang
 * @className IndexServiceImpl
 * @date 2020/10/19 20:54
 * @since JDK 1.8
 */
public class IndexServiceImpl implements IIndexService {
    private ISystemLogDAO systemLogDAO = new SystemDAOImpl();
    private IFilmDAO filmDAO = new FilmDAOImpl();
    private IImgDAO imgDAO = new ImgDAOImpl();
    private IManagerDAO managerDAO = new ManagerDAOImpl();
    private IInformationDAO informationDAO = new InfromationDAOImpl();
    private IUserDAO userDAO = new UserDAOImpl();
    private static final String[] ZONES = {"total","today","yesterday","week","month"};
    @Override
    public IndexPage getIndexPage(String username) {
        IndexPage indexPage = new IndexPage();
        indexPage.setLoginCount(systemLogDAO.getCountByName(username));
        Map<String,IndexStatistic> indexStatisticHashMap = new HashMap<>();
        Arrays.stream(ZONES).forEach(item->indexStatisticHashMap.put(item, getSta(item)));
        indexPage.setMap(indexStatisticHashMap);

        return indexPage;
    }
    private IndexStatistic getSta(String zone){
        IndexStatistic is = new IndexStatistic();
        switch(zone){
            case "total":
                is.setAdminCount(managerDAO.getTotalNum());
                is.setFilmCount(filmDAO.getTotalNum());
                is.setImgCount(imgDAO.getTotalNum());
                is.setInfoCount(informationDAO.getTotalNum());
                is.setUserCount(userDAO.getTotalNum());
                break;
            case "today":
                is.setAdminCount(managerDAO.getTodayNum());
                is.setFilmCount(filmDAO.getTodayNum());
                is.setImgCount(imgDAO.getTodayNum());
                is.setInfoCount(informationDAO.getTodayNum());
                is.setUserCount(userDAO.getTodayNum());
                break;
            case "yesterday":
                is.setAdminCount(managerDAO.getYesterdayNum());
                is.setFilmCount(filmDAO.getYesterdayNum());
                is.setImgCount(imgDAO.getYesterdayNum());
                is.setInfoCount(informationDAO.getYesterdayNum());
                is.setUserCount(userDAO.getYesterdayNum());
                break;
            case "week":
                is.setAdminCount(managerDAO.getWeekNum());
                is.setFilmCount(filmDAO.getWeekNum());
                is.setImgCount(imgDAO.getWeekNum());
                is.setInfoCount(informationDAO.getWeekNum());
                is.setUserCount(userDAO.getWeekNum());
                break;
            default:
                is.setAdminCount(managerDAO.getMonthNum());
                is.setFilmCount(filmDAO.getMonthNum());
                is.setImgCount(imgDAO.getMonthNum());
                is.setInfoCount(informationDAO.getMonthNum());
                is.setUserCount(userDAO.getMonthNum());
                break;
        }
        return is;
    }
    private IndexServer getSer(HttpServletRequest req,HttpServletResponse resp){
        IndexServer indexServer = new IndexServer();
        return indexServer;
    }

}
