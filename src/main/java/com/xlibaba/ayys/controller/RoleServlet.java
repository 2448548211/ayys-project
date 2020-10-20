package com.xlibaba.ayys.controller;

import com.xlibaba.ayys.entity.BaseResponseEntity;
import com.xlibaba.ayys.entity.Role;
import com.xlibaba.ayys.service.IRoleService;
import com.xlibaba.ayys.service.serviceImpl.RoleServiceImpl;
import com.xlibaba.ayys.utils.ResponseUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/roleServlet")
public class RoleServlet extends HttpServlet {
    private IRoleService iRoleService = new RoleServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action){
            case "getAll":
                toGetAll(request,response);
                break;
            case "add":
                toAdd(request,response);
                break;

        }
    }

    private void toAdd(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入方法");
        String rolename = request.getParameter("name");
        String remark = request.getParameter("remark");
        System.out.println(rolename);
        System.out.println(remark);
    }

    private void toGetAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BaseResponseEntity bs=null;
        try {
            List<Role> roles = iRoleService.selectRoles();
            bs = BaseResponseEntity.success(roles);
        }catch (Exception e){
            bs = BaseResponseEntity.error();
        }
        ResponseUtil.sendJSON(response,bs);
    }
}
