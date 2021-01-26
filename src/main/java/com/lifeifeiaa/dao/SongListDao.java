package com.lifeifeiaa.dao;

import com.lifeifeiaa.domain.SongList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SongListDao {

    //增加
    int insert(SongList songList);
    //修改
    int update(SongList songList);
    //删除
    int delete(Integer id);
    //根据主键查询对象
    SongList selectByPrimaryKey(Integer id);
    //查询所有歌单
    List<SongList> allSongList();
    //根据标题精确查询歌单列表
    List<SongList> songListByTitle(String title);
    //根据标题模糊查询个单列表
    List<SongList> likeTitle(String title);
    //根据风格模糊查询
    List<SongList> likeStyle(String style);

}
