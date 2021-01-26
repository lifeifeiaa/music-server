package com.lifeifeiaa.service;

import com.lifeifeiaa.domain.SongList;

import java.util.List;

/**
 * 歌单业务接口层
 */
public interface SongListService {

    //增加
    boolean insert(SongList songList);
    //修改
    boolean update(SongList songList);
    //删除
    boolean delete(Integer id);
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
