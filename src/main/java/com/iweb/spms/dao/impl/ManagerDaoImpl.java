package com.iweb.spms.dao.impl;

import com.iweb.spms.bean.Manager;
import com.iweb.spms.dao.ManagerDao;
import com.iweb.spms.util.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;

/**
 * @author Wang
 * @date 2024/4/21 20:03
 */
public class ManagerDaoImpl implements ManagerDao {
    QueryRunner qr = new QueryRunner(DruidUtil.getDataSource());
    @Override
    public Manager login(Manager manager) {
        String sql = "select * from manager where managername = ? and managerpassword = ?";
        try{
            manager = qr.query(sql,new BeanHandler<Manager>(Manager.class),manager.getManagerName(),manager.getManagerPassword());
            return manager;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean verifyManagerName(String managerName) {
        String sql = "select count(*) from manager where managername = ?";
        try{
            Number number = qr.query(sql,new ScalarHandler<>(),managerName);
            return number.intValue()>0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addManager(Manager manager) {
        String sql = "insert into manager(managerid,addtime,managername,managerpassword) values(?,?,?,?)";
        try{
            int update = qr.update(sql,manager.getManagerId(),manager.getAddTime(),manager.getManagerName(),manager.getManagerPassword());
            return update>0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
