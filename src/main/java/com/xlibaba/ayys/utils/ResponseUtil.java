package com.xlibaba.ayys.utils;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ChenWang
 * @className ResponseUtil
 * @date 2020/10/13 19:46
 * @since JDK 1.8
 */
public class ResponseUtil {

    /**
     * 把数据对象转化成JSON字符串并且发送到响应中
     * @param resp  指定的响应
	 * @param obj   需要被转化的对象
     * @author ChenWang
     * @date 2020/10/13 20:14
     */
    public static void sendJSON(HttpServletResponse resp, Object obj) throws IOException {
        //设置响应的编码格式
        resp.setCharacterEncoding("UTF-8");
        //告知请求方响应返回的数据类型是什么
        resp.setContentType("application/json; charset=UTF-8");
        //把JSON对象(字符串)发送到响应中去
        resp.getWriter().println(JSON.toJSONString(obj));
    }
}
