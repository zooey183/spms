package com.iweb.spms.controller;

import cn.hutool.json.JSONUtil;
import com.iweb.spms.bean.PageResult;
import com.iweb.spms.bean.ResultVo;
import com.iweb.spms.bean.Student;
import com.iweb.spms.service.StudentService;
import com.iweb.spms.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Wang
 * @date 2024/4/25 10:39
 */
@WebServlet("/student")
public class StudentServlet extends BaseServlet {
    private StudentService studentService = new StudentServiceImpl();

    public void studentList(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String studentName = req.getParameter("studentName");
        int pageIndex = Integer.parseInt(req.getParameter("pageIndex"));
        int pageSize = Integer.parseInt(req.getParameter("pageSize"));
        List<Student> studentList = studentService.studentList(pageIndex, pageSize, studentName);
        int total = studentService.count();
        PageResult<Student> pageResult = new PageResult<>();
        pageResult.setTotal(total);
        pageResult.setData(studentList);
        res.getWriter().write(JSONUtil.toJsonStr(pageResult));
    }

    public void addStudent(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String data = req.getParameter("data");
        Student student = JSONUtil.toBean(data, Student.class);
        boolean repeat = studentService.repeatStudent(student.getStudentName());
        ResultVo resultVo = new ResultVo();
        if (repeat) {
            resultVo.setMess("学生姓名重复");
        } else {
            boolean addOk = studentService.addStudent(student);
            if (addOk) {
                resultVo.setOk(true);
                resultVo.setMess("添加学生信息成功");
            }
        }
        res.getWriter().write(JSONUtil.toJsonStr(resultVo));
    }

    public void updateStudent(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String data = req.getParameter("data");
        Student student = JSONUtil.toBean(data, Student.class);
        ResultVo resultVo = new ResultVo();
        boolean updateOk = studentService.updateStudent(student);
        if (updateOk) {
            resultVo.setOk(true);
            resultVo.setMess("修改学生信息成功");
        }
        res.getWriter().write(JSONUtil.toJsonStr(resultVo));
    }

    public void queryById(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String studentId = req.getParameter("studentId");
        Student student = studentService.queryById(studentId);
        res.getWriter().write(JSONUtil.toJsonStr(student));
    }

    public void deleteBatch(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String ids = req.getParameter("ids");
        ResultVo resultVo = new ResultVo();
        boolean deleteOk = studentService.deleteBatch(ids);
        if(deleteOk){
            resultVo.setOk(true);
            resultVo.setMess("删除成功！");
        }else{
            resultVo.setMess("删除出现问题，请联系后台管理员");
        }
        res.getWriter().write(JSONUtil.toJsonStr(resultVo));
    }
}
