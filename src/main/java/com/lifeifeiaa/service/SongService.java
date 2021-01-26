package com.lifeifeiaa.service;

import com.lifeifeiaa.domain.Song;

import java.util.List;

/**
 * 歌曲业务接口层
 * */
public interface SongService {

    //增加
    boolean insert(Song song);
    //修改
    boolean update(Song song);
    //删除
    boolean delete(Integer id);
    //根据主键查询对象
    Song selectByPrimaryKey(Integer id);
    //查询所有歌曲
    List<Song> allSong();
    //根据歌名精确查询列表
    List<Song> songByName(String name);
    //根据歌名精确查询列表
    List<Song> likeSongByName(String name);
    //根据歌手id查询
    List<Song> songBySingerId(Integer singerId);
}
