package com.iweb.spms.service.impl;

import com.iweb.spms.bean.Student;
import com.iweb.spms.dao.StudentDao;
import com.iweb.spms.dao.impl.StudentDaoImpl;
import com.iweb.spms.service.StudentService;
import com.iweb.spms.util.DateUtil;
import com.iweb.spms.util.MD5Util;
import com.iweb.spms.util.UUIDUtil;

import java.util.Date;
import java.util.List;

/**
 * @author Wang
 * @date 2024/4/25 10:43
 */
public class StudentServiceImpl implements StudentService {
    private StudentDao studentDao = new StudentDaoImpl();
    @Override
    public List<Student> studentList(int pageIndex, int pageSize, String studentName) {
        return studentDao.studentList(pageIndex,pageSize,studentName);
    }

    @Override
    public int count() {
        return studentDao.count();
    }

    @Override
    public boolean repeatStudent(String studentName) {
        return studentDao.repeatStudent(studentName);
    }

    @Override
    public boolean addStudent(Student student) {
        student.setStudentId(UUIDUtil.uuid());
        student.setStudentPassword(MD5Util.getMD5(student.getStudentPassword()));
        student.setAddTime(DateUtil.dateToString(new Date()));
        return studentDao.addStudent(student);
    }

    @Override
    public Student queryById(String studentId) {
        return studentDao.queryById(studentId);
    }

    @Override
    public boolean updateStudent(Student student) {
        student.setStudentPassword(MD5Util.getMD5(student.getStudentPassword()));
        return studentDao.updateStudent(student);
    }

    @Override
    public boolean deleteBatch(String ids) {
        return studentDao.deleteBatch(ids);
    }
}
