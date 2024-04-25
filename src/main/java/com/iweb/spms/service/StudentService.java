package com.iweb.spms.service;

import com.iweb.spms.bean.Student;

import java.util.List;

/**
 * @author Wang
 * @date 2024/4/25 10:40
 */
public interface StudentService {
    List<Student> studentList(int pageIndex, int pageSize, String studentName);
    int count();
    boolean repeatStudent(String studentName);
    boolean addStudent(Student student);
    Student queryById(String studentId);
    boolean updateStudent(Student student);
    boolean deleteBatch(String ids);}
