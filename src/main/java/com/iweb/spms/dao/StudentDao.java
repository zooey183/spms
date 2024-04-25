package com.iweb.spms.dao;

import com.iweb.spms.bean.Student;

import java.util.List;

/**
 * @author Wang
 * @date 2024/4/21 20:02
 */
public interface StudentDao {
    List<Student> studentList(int pageIndex, int pageSize, String studentName);
    int count();
    boolean repeatStudent(String studentname);
    boolean addStudent(Student student);
    Student queryById(String studentid);
    boolean updateStudent(Student student);
    boolean deleteBatch(String ids);

}
