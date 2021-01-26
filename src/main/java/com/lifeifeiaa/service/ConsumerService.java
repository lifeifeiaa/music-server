package com.lifeifeiaa.service;

import com.lifeifeiaa.domain.Consumer;

import java.util.List;

public interface ConsumerService {

    //增加
    boolean insert(Consumer consumer);
    //修改
    boolean update(Consumer consumer);
    //删除
    boolean delete(Integer id);
    //根据主键查询用户
    Consumer selectByPrimaryKey(Integer id);
    //查询所有用户
    List<Consumer> allConsumer();
    //验证密码功能
    boolean verifyPassword(String username,String password);
    //根据账号查询
    Consumer getByUsername(String username);
}
