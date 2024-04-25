package com.iweb.spms.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Wang
 * @date 2024/4/18 15:03
 */
@WebServlet("/code")
public class CodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(200,100,4,50);
        String code = captcha.getCode();
        req.getSession().setAttribute("code",code);
        captcha.write(resp.getOutputStream());
    }
}
