package com.iweb.spms.dao;

import com.iweb.spms.bean.Manager;

/**
 * @author Wang
 * @date 2024/4/21 20:02
 */
public interface ManagerDao {
    /** 登录
     * @param manager 从前端获取登陆用户的输入信息
     * @return 返回用户对象本身
     */
    Manager login(Manager manager);
    /** 验证注册的用户名是否重复
     * @param managerName  需要被验证的用户名
     * @return
     */
    boolean verifyManagerName(String managerName);

    /** 添加注册用户
     * @param manager 被添加的用户
     * @return 添加成功返回true 添加失败返回false
     */
    boolean addManager(Manager manager);
}
