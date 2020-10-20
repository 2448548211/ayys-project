package com.xlibaba.ayys.dao;

import com.xlibaba.ayys.entity.User;
import java.util.List;

public interface IUserDao {
    //查询全表
    public List<User> selectUserAll();
    
    //通过用户名查询用户数据
    public User selectUserByName(String name);
}
