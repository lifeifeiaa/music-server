package com.lifeifeiaa.service;

import com.lifeifeiaa.domain.Singer;

import java.util.List;

/**
 * 歌手业务接口层
 * */
public interface SingerService {

    //增加
    boolean insert(Singer singer);
    //修改
    boolean update(Singer singer);
    //删除
    boolean delete(Integer id);
    //根据主键查询对象
    Singer selectByPrimaryKey(Integer id);
    //查询所有歌手
    List<Singer> allSinger();
    //根据歌手名字模糊查询列表
    List<Singer> singerByName(String name);
    //根据性别查询
    List<Singer> singerBySex(Integer sex);
}
