package com.lifeifeiaa.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminDao {

    /**
     * 验证密码是否正确
     * */
    int verifyPassword(String username,String password);
}
