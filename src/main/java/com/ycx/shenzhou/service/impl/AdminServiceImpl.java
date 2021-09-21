package com.ycx.shenzhou.service.impl;

import com.ycx.shenzhou.mapper.AdminMapper;
import com.ycx.shenzhou.pojo.Admin;
import com.ycx.shenzhou.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AdminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public String addAdmin(Admin admin) {
        adminMapper.addAdmin(admin); // 在数据库中插入新的管理员
        return admin.getId(); //返回该管理员的ID
    }

    @Override
    public boolean removeAdmin(String id) {
        return adminMapper.removeAdmin(id); // 通过ID在数据库中删除管理员，并判断是否移除成功
    }

    @Override
    public boolean isAdmin(String account) {
        Admin admin = new Admin();
        admin = adminMapper.getAdminByAccount(account);
        if(admin != null) {
            return true;
        }
        return false;
    }
}