package com.lifeifeiaa.dao;

import com.lifeifeiaa.domain.Consumer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
/**
 * 前端用户dao
 * */
@Mapper
public interface ConsumerDao {

    //增加
    int insert(Consumer consumer);
    //修改
    int update(Consumer consumer);
    //删除
    int delete(Integer id);
    //根据主键查询用户
    Consumer selectByPrimaryKey(Integer id);
    //查询所有用户
    List<Consumer> allConsumer();
    //验证密码功能
    int verifyPassword(String username,String password);
    //根据账号查询
    Consumer getByUsername(String username);
}
