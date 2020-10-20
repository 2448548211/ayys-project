package com.xlibaba.ayys.controller;

import com.xlibaba.ayys.entity.BaseResponseEntity;
import com.xlibaba.ayys.entity.InformationData;
import com.xlibaba.ayys.service.InformationService;
import com.xlibaba.ayys.service.impl.InformationServiceImpl;
import com.xlibaba.ayys.utils.ResponseUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/information")
public class InformationController extends HttpServlet {
    private InformationService service = new InformationServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BaseResponseEntity<InformationData> responseEntity = new BaseResponseEntity<>();
        try {
            InformationData data = service.getInformation();
            responseEntity.setData(data);
        } catch (Exception e){
            e.printStackTrace();
        }
        ResponseUtil.sendJSON(resp,responseEntity);
    }
}
