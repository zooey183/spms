package com.iweb.spms.controller;

import cn.hutool.json.JSONUtil;
import com.iweb.spms.bean.Manager;
import com.iweb.spms.bean.ResultVo;
import com.iweb.spms.service.ManagerService;
import com.iweb.spms.service.impl.ManagerServiceImpl;
import com.iweb.spms.util.FormBeanUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Wang
 * @date 2024/4/21 21:33
 */
@WebServlet("/manager")
public class ManagerServlet extends BaseServlet{
    private ManagerService managerService = new ManagerServiceImpl();
    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResultVo resultVo = new ResultVo<>();
        String code = req.getParameter("code");
        String correctCode = (String) req.getSession().getAttribute("code");
        if(!correctCode.equalsIgnoreCase(code)){
            resultVo.setMess("验证码错误");
        }else{
            Manager manager = FormBeanUtil.formToBean(req.getParameterMap(),Manager.class);
            manager = managerService.login(manager);
            if(manager==null){
                resultVo.setMess("用户名或密码错误");
            }else{
                resultVo.setOk(true);
                req.getSession().setAttribute("manager",manager);
            }
        }
        resp.getWriter().write(JSONUtil.toJsonStr(resultVo));
    }
    public void verifyManagerName(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        //获取是否被注册过的结果
        boolean isExists = managerService.verifyManagerName(req.getParameter("username"));
        ResultVo resultVo = new ResultVo();
        if(isExists){
            resultVo.setOk(true);
            resultVo.setMess("用户已经被注册");
        }
        resp.getWriter().write(JSONUtil.toJsonStr(resultVo));
    }
    public void regist(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        Manager manager = FormBeanUtil.formToBean(req.getParameterMap(),Manager.class);
        boolean addOk = managerService.addManager(manager);
        ResultVo resultVo = new ResultVo();
        if(addOk){
            resultVo.setOk(true);
            resultVo.setMess("注册成功ヾ(≧▽≦*)o");
        }else{
            resultVo.setOk(false);
            resultVo.setMess("注册失败，请检查用户名是否有误！");
        }
        resp.getWriter().write(JSONUtil.toJsonStr(resultVo));
    }
    public void getInfo(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        Manager manager = (Manager) req.getSession().getAttribute("manager");
        ResultVo<Manager> resultVo = new ResultVo<>();
        resultVo.setT(manager);
        resp.getWriter().write(JSONUtil.toJsonStr(resultVo));
    }
    public void logOut(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        req.getSession().removeAttribute("manager");
        resp.sendRedirect("/spms/login.html");
    }
}
