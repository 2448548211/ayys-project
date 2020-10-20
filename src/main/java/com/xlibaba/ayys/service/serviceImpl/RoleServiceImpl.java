package com.xlibaba.ayys.service.serviceImpl;

import com.xlibaba.ayys.dao.IRoleDao;
import com.xlibaba.ayys.dao.impl.RoleDaoImpl;
import com.xlibaba.ayys.entity.Role;
import com.xlibaba.ayys.service.IRoleService;

import java.util.List;

public class RoleServiceImpl implements IRoleService {
    private IRoleDao iRoleDao = new RoleDaoImpl();
    @Override
    public List<Role> selectRoles() {
        return iRoleDao.selectRoles();
    }
}
