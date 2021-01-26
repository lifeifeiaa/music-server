package com.lifeifeiaa.service;

import com.lifeifeiaa.domain.Comment;

import java.util.List;

public interface CommentService {

    //增加
    boolean insert(Comment comment);
    //修改
    boolean update(Comment comment);
    //删除
    boolean delete(Integer id);
    //根据主键查询对象
    Comment selectByPrimaryKey(Integer id);
    //查询所有评论
    List<Comment> allComment();
    //根据歌曲id查询查询评论
    List<Comment> commentOfSongId(Integer songId);
    //查询歌单下的评论
    List<Comment> commentOfSongListId(Integer songListId);
}
