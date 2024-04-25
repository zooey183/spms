package com.iweb.spms.service.impl;

import com.iweb.spms.bean.Manager;
import com.iweb.spms.dao.ManagerDao;
import com.iweb.spms.dao.impl.ManagerDaoImpl;
import com.iweb.spms.service.ManagerService;
import com.iweb.spms.util.DateUtil;
import com.iweb.spms.util.MD5Util;
import com.iweb.spms.util.UUIDUtil;

import java.util.Date;

/**
 * @author Wang
 * @date 2024/4/21 21:36
 */
public class ManagerServiceImpl implements ManagerService {
    private ManagerDao managerDao = new ManagerDaoImpl();
    @Override
    public Manager login(Manager manager) {
        manager.setManagerPassword(MD5Util.getMD5(manager.getManagerPassword()));
        return managerDao.login(manager);
    }

    @Override
    public boolean verifyManagerName(String managerName) {
        return managerDao.verifyManagerName(managerName);
    }

    @Override
    public boolean addManager(Manager manager) {
        manager.setManagerId(UUIDUtil.uuid());
        manager.setManagerPassword(MD5Util.getMD5(manager.getManagerPassword()));
        manager.setAddTime(DateUtil.dateToString(new Date()));
        return managerDao.addManager(manager);
    }
}
