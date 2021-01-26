package com.lifeifeiaa.dao;

import com.lifeifeiaa.domain.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentDao {

    //增加
    int insert(Comment comment);
    //修改
    int update(Comment comment);
    //删除
    int delete(Integer id);
    //根据主键查询对象
    Comment selectByPrimaryKey(Integer id);
    //查询所有评论
    List<Comment> allComment();
    //根据歌曲id查询查询评论
    List<Comment> commentOfSongId(Integer songId);
    //查询歌单下的评论
    List<Comment> commentOfSongListId(Integer songListId);
}
