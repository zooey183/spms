package com.iweb.spms.dao.impl;

import cn.hutool.core.util.StrUtil;
import com.iweb.spms.bean.Student;
import com.iweb.spms.dao.StudentDao;
import com.iweb.spms.util.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Wang
 * @date 2024/4/21 20:03
 */
public class StudentDaoImpl implements StudentDao {
    QueryRunner qr = new QueryRunner(DruidUtil.getDataSource());

    @Override
    public List<Student> studentList(int pageIndex, int pageSize, String studentName) {
        List<Student> studentList = new ArrayList<>();
        int a = pageIndex * pageSize;
        String sql = "select * from student where 1 = 1 ";
        try {
            Map<String, String> params = new HashMap<>();
            if (StrUtil.isNotEmpty(studentName)) {
                params.put("studentname", "%" + studentName + "%");
                sql = sql + "and studentname like ? ";
            }
            sql = sql + "limit " + a + "," + pageSize;
            if (params.size() > 0) {
                Object[] objects = params.values().toArray();
                studentList = qr.query(sql, new BeanListHandler<>(Student.class), objects);
            } else {
                studentList = qr.query(sql, new BeanListHandler<>(Student.class));
            }
            return studentList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int count() {
        String sql = "select count(*) from student";
        try {
            Number number = qr.query(sql, new ScalarHandler<>());
            return number.intValue();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean repeatStudent(String studentname) {
        String sql = "select count(*) from student where studentname = ?";
        try {
            Number number = qr.query(sql, new ScalarHandler<>(), studentname);
            return number.intValue() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addStudent(Student student) {
        String sql = "insert into student values(?,?,?,?,?,?,?,?,?,?)";
        int count = 0;
        try {
            count = qr.update(sql, student.getStudentId(), student.getAddTime(), student.getStudentName(), student.getStudentPassword(), student.getStudentSex(), student.getStudentHeadSculpture(), student.getSchool(), student.getMajor(), student.getPhone(), student.getEmail());
            return count > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Student queryById(String studentid) {
        String sql = "select * from student where studentid=?";
        try{
            Student student=qr.query(sql,new BeanHandler<Student>(Student.class),studentid);
            return student;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateStudent(Student student) {
        String sql = "update student set studentname=?,studentpassword=?,studentsex=?,studentheadsculpture=?,school=?,major=?,phone=?,email=?";
        int count = 0;
        try{
            count = qr.update(sql,student.getStudentName(),student.getStudentPassword(),student.getStudentSex(),student.getStudentHeadSculpture(),student.getSchool(),student.getMajor(),student.getPhone(),student.getEmail());
            return count>0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteBatch(String ids) {

        String[] split = ids.split(",");
        boolean deleteOk = true;
        loop:
        for (String s : split) {
            String sql = "delete from student where studentid=?";
            try {
                int count = qr.update(sql, s);
                if (count <= 0) {
                    deleteOk = false;
                    DruidUtil.getDataSource().getConnection().rollback();
                    break loop;
                }
                return deleteOk;
            } catch (SQLException e) {
                e.printStackTrace();
                try {
                    DruidUtil.getDataSource().getConnection().rollback();
                } catch (SQLException ex) {
                    e.printStackTrace();
                }
            }
        }return false;
    }
}
