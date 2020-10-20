package com.xlibaba.ayys.dao;

import com.xlibaba.ayys.entity.Information;

import java.util.List;

public interface InformationDao {

    /**
     * 获取资讯数据
     * @return 数据集合
     */
    List<Information> selectInformation();

    /**
     * 添加、修改、删除资讯信息
     * @param information 资讯数据
     * @return 数据库影响行数
     */
    Integer updateInformation(Information information);

    /**
     *  业务删除资讯数据
     * @param id 根据id
     * @return 影响行数
     */
    Integer updateInformation(String id);
}
