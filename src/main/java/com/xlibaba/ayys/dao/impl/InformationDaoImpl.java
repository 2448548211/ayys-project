package com.xlibaba.ayys.dao.impl;

import com.xlibaba.ayys.dao.InformationDao;
import com.xlibaba.ayys.entity.Information;
import com.xlibaba.ayys.utils.DbManager;

import java.util.List;

public class InformationDaoImpl implements InformationDao {
    @Override
    public List<Information> selectInformation() {
        String sql = "select id,title,type,source,update_time,view_count,is_used,is_published from information";
        return DbManager.commonQuery(sql,Information.class);
    }

    /**
     *  往数据库中添加资讯数据
     * @param information 资讯数据
     * @return 影响行数
     */
    @Override
    public Integer updateInformation(Information information) {
        String sql = "insert into information(id,title,type,source,update_time,view_count) values(?,?,?,?,?,?)";
        //传递参数
        return DbManager.commonUpdate(sql,information.getId(),information.getTitle(),information.getType()
        ,information.getSource(),information.getUpdateTime(),information.getViewCount());
    }

    /**
     *  业务删除资讯数据
     * @param id 根据id
     * @return 影响行数
     */
    @Override
    public Integer updateInformation(String id) {
        String sql = "update information set is_delete = 0 where id = ?";
        //传递参数
        return DbManager.commonUpdate(sql,id);
    }
}
