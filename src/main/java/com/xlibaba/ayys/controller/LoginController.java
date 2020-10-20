package com.xlibaba.ayys.controller;


import com.xlibaba.ayys.entity.BaseResponseEntity;
import com.xlibaba.ayys.utils.ResponseUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: ayys-project
 * @author: hzx
 * @since: JDK 1.8
 * @create: 2020-10-19 16:52
 **/
@WebServlet("/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        BaseResponseEntity<Boolean> entity = null;

        //获取用户输入的验证码
        String code = req.getParameter("code");
        //获取存放在 seesion 作用域中的验证码
        String serverCode = (String) req.getSession().getAttribute("code");
        //验证码效验
        if (!serverCode.equals(code)) {
            entity = BaseResponseEntity.error(404,"验证码有误");
            ResponseUtil.sendJSON(resp,entity);
            return;
        }

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username+"   "+password);
        //用户名验证
        if (username.equals("asd")) {
            //密码验证
            if (password.equals("1")) {
                entity = BaseResponseEntity.success(true);
            } else {
                entity = BaseResponseEntity.error(404,"密码错误");
            }
        } else {
            entity = BaseResponseEntity.error(404,"用户名不存在");
        }
        ResponseUtil.sendJSON(resp,entity);
    }
}
