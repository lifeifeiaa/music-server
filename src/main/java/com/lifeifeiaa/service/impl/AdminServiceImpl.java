package com.lifeifeiaa.service.impl;

import com.lifeifeiaa.dao.AdminDao;
import com.lifeifeiaa.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Override
    public boolean verifyPassword(String username, String password) {
        return adminDao.verifyPassword(username,password)>0;
    }
}
