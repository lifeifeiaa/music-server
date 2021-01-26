package com.lifeifeiaa.dao;

import com.lifeifeiaa.domain.ListSong;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ListSongDao {

    //增加
    int insert(ListSong listSong);
    //修改
    int update(ListSong listSong);
    //删除
    int delete(Integer id);
    //根据歌曲id和歌单id删除
    int deleteBySongIdAndSongListId(Integer songId,Integer songListId);
    //根据主键查询对象
    ListSong selectByPrimaryKey(Integer id);
    //查询所有歌单里歌曲
    List<ListSong> allListSong();
    //根据歌单id查询所有的歌曲
    List<ListSong> listSongBySongListId(Integer songListId);
}
