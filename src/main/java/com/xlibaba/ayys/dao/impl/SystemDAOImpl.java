package com.xlibaba.ayys.dao.impl;

import com.xlibaba.ayys.dao.ISystemLogDAO;
import com.xlibaba.ayys.utils.DBUtil;

/**
 * @author ChenWang
 * @className SystemDAOImpl
 * @date 2020/10/20 09:24
 * @since JDK 1.8
 */
public class SystemDAOImpl implements ISystemLogDAO {
    public SystemDAOImpl() {
    }

    @Override
    public Integer getCountByName(String username) {
        return DBUtil.getDbUtil().executeUpdate("SELECT COUNT(s.person_id) FROM system_log s INNER JOIN" +
                " user u ON s.person_id = u.id");
    }
}
