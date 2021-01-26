package com.lifeifeiaa.dao;

import com.lifeifeiaa.domain.Song;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SongDao {

    //增加
    int insert(Song song);
    //修改
    int update(Song song);
    //删除
    int delete(Integer id);
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
