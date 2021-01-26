package com.lifeifeiaa.service.impl;

import com.lifeifeiaa.dao.CommentDao;
import com.lifeifeiaa.domain.Comment;
import com.lifeifeiaa.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    public boolean insert(Comment comment) {
        return commentDao.insert(comment)>0;
    }

    @Override
    public boolean update(Comment comment) {
        return commentDao.update(comment)>0;
    }

    @Override
    public boolean delete(Integer id) {
        return commentDao.delete(id)>0;
    }

    @Override
    public Comment selectByPrimaryKey(Integer id) {
        return commentDao.selectByPrimaryKey(id);
    }

    @Override
    public List<Comment> allComment() {
        return commentDao.allComment();
    }

    @Override
    public List<Comment> commentOfSongId(Integer songId) {
        return commentDao.commentOfSongId(songId);
    }

    @Override
    public List<Comment> commentOfSongListId(Integer songListId) {
        return commentDao.commentOfSongListId(songListId);
    }
}
