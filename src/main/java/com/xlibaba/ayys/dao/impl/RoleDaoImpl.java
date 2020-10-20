package com.xlibaba.ayys.dao.impl;

import com.xlibaba.ayys.dao.IRoleDao;
import com.xlibaba.ayys.entity.Role;
import com.xlibaba.ayys.utils.DBUtil;

import java.util.List;

public class RoleDaoImpl implements IRoleDao{

    @Override
    public List<Role> selectRoles() {
        String sql = "select * from role where is_delete = 1";
        List<Role> roles = DBUtil.getDbUtil().excuteQuery(sql, Role.class);
        return roles;
    }
}
