package com.iweb.spms.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author Wang
 * @date 2024/4/18 14:54
 */
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String methodName = req.getParameter("method");
        try{
            //根据方法名称 获取反射对象
            Method method = this.getClass().getMethod
                    (methodName,HttpServletRequest.class,HttpServletResponse.class);
            method.invoke(this,req,resp);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
