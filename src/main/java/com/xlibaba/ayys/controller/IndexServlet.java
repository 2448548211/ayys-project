package com.xlibaba.ayys.controller;

import com.xlibaba.ayys.entity.BaseResponseEntity;
import com.xlibaba.ayys.service.entity.IndexPage;
import com.xlibaba.ayys.service.IIndexService;
import com.xlibaba.ayys.service.impl.IndexServiceImpl;
import com.xlibaba.ayys.utils.ResponseUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ChenWang
 * @className IndexServlet
 * @date 2020/10/19 20:53
 * @since JDK 1.8
 */
public class IndexServlet extends HttpServlet {
    private IIndexService indexService = new IndexServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        IndexPage indexPage = indexService.getIndexPage(username);
        BaseResponseEntity<IndexPage> pakage;
        if(indexPage!=null){
            pakage = new BaseResponseEntity<IndexPage>().success(indexPage);
        }else{
            pakage = new BaseResponseEntity<IndexPage>().error();
        }
        ResponseUtil.sendJSON(resp,pakage);
    }
}
