package com.lifeifeiaa.dao;

import com.lifeifeiaa.domain.Collect;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CollectDao {

    //增加
    int insert(Collect collect);
    //删除
    int delete(Integer id);
    //通过userId和songId进行删除
    int delCollect(@Param("userId") Integer userId,@Param("songId") Integer songId);
    //查询所有收藏
    List<Collect> allCollect();
    //根据用户id查询收藏
    List<Collect> collectOfUserId(Integer userId);
    //查询某个用户是否已经收藏了某个歌曲
    int existSongId(@Param("userId") Integer userId,@Param("songId") Integer songId);
}
