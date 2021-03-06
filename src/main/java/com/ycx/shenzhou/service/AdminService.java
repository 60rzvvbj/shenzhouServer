package com.ycx.shenzhou.service;

import com.ycx.shenzhou.pojo.Admin;

public interface AdminService {

    // 添加管理员
    String addAdmin(Admin admin);

    // 删除管理员
    boolean removeAdmin(String id);

    // 判断用户是否为管理员
    boolean isAdmin(String account);
}
