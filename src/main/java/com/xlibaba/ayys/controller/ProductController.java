package com.xlibaba.ayys.controller;

import com.xlibaba.ayys.entity.BaseResponseEntity;
import com.xlibaba.ayys.service.ProductService;
import com.xlibaba.ayys.service.impl.ProductServiceImpl;
import com.xlibaba.ayys.utils.ProductData;
import com.xlibaba.ayys.utils.ResponseUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/product")
public class ProductController extends HttpServlet {
    private final ProductService service = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BaseResponseEntity<ProductData> responseEntity = new BaseResponseEntity<>();
        try {
            ProductData data = service.getList();
            responseEntity.setData(data);
        } catch (Exception e){
            e.printStackTrace();
        }
        ResponseUtil.sendJSON(resp,responseEntity);
    }
}
