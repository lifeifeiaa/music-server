package com.lifeifeiaa.service;

import com.lifeifeiaa.domain.Collect;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CollectService {

    //增加
    boolean insert(Collect collect);
    //删除
    boolean delete(Integer id);
    boolean delCollect(Integer userId,Integer songId);
    //查询所有收藏
    List<Collect> allCollect();
    //根据用户id查询收藏
    List<Collect> collectOfUserId(Integer userId);
    //查询某个用户是否已经收藏了某个歌曲
    boolean existSongId(@Param("userId") Integer userId, @Param("songId") Integer songId);
}
