package com.xlibaba.ayys.controller;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @author Hzx
 * @since JDK 1.8
 */
@WebServlet("/check")
public class CheckController extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int width = 100;
        int height = 30;
        //1.创建带缓冲的图片对象，需要传递宽，高，图片颜色的类型
        BufferedImage bImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //2.通过图片对象获取画笔
        Graphics graphics = bImage.getGraphics();
        //3.给画笔设置颜色
        graphics.setColor(Color.PINK);
        //4.填充背景颜色
        graphics.fillRect(0,0,width,height);
        //5.再给画笔设置颜色
        graphics.setColor(Color.BLUE);
        //6.设置字体
        graphics.setFont(new Font("黑体", Font.BOLD,20));
        //7.生成随机数当成验证码
        Random ran = new Random();
        int code = 0;
        StringBuffer sb = new StringBuffer();
        for (int i = 0;i < 4;i++){
            code = ran.nextInt(10);
            sb.append(code);
            // 把随机数添加到图片中
            graphics.drawString(code+"",15+20*i,20);
        }

        //把验证码存放到 session 作用域中
        HttpSession session = req.getSession();
        session.setAttribute("code",sb.toString());

        //8.改变画笔的颜色
        graphics.setColor(Color.RED);
        //9.绘制干扰线 ran.nextInt(width)
        for (int i = 0; i < 5; i++) {
            graphics.drawLine(10,ran.nextInt(height-10)+5,width-10,ran.nextInt(height-10)+5);
        }
        //10.把绘制好的图片对象响应回请求的客户端
        ImageIO.write(bImage,"jpg",resp.getOutputStream());
    }
}
