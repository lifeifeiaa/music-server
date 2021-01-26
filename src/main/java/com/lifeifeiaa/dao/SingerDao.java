package com.lifeifeiaa.dao;

import com.lifeifeiaa.domain.Singer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SingerDao {

    //增加
    int insert(Singer singer);
    //修改
    int update(Singer singer);
    //删除
    int delete(Integer id);
    //根据主键查询对象
    Singer selectByPrimaryKey(Integer id);
    //查询所有歌手
    List<Singer> allSinger();
    //根据歌手名字模糊查询列表
    List<Singer> singerByName(String name);
    //根据性别查询
    List<Singer> singerBySex(Integer sex);
}
