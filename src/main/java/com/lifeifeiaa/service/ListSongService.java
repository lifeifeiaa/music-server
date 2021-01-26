package com.lifeifeiaa.service;

import com.lifeifeiaa.domain.ListSong;

import java.util.List;

/**
 * 歌单歌曲的业务接口层
 */
public interface ListSongService {

    //增加
    boolean insert(ListSong listSong);
    //修改
    boolean update(ListSong listSong);
    //删除
    boolean delete(Integer id);
    //根据歌曲id和歌单id删除
    boolean deleteBySongIdAndSongListId(Integer songId,Integer songListId);
    //根据主键查询对象
    ListSong selectByPrimaryKey(Integer id);
    //查询所有歌单里歌曲
    List<ListSong> allListSong();
    //根据歌单id查询所有的歌曲
    List<ListSong> listSongBySongListId(Integer songListId);
}
