package com.lifeifeiaa.service;

public interface AdminService {

    /**
     * 验证密码是否正确
     * */
    boolean verifyPassword(String username,String password);
}
