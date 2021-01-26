package com.lifeifeiaa.controller;

import com.alibaba.fastjson.JSONObject;
import com.lifeifeiaa.domain.Comment;
import com.lifeifeiaa.service.CommentService;
import com.lifeifeiaa.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 评论控制层
 * */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 添加评论
     * @param request
     * @return json对象
     */
    @PostMapping("/add")
    public Object addComment(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();

        String userId = request.getParameter("userId");
        String type = request.getParameter("type");
        String songId = request.getParameter("songId");
        String songListId = request.getParameter("songListId");
        String content = request.getParameter("content").trim();

        Comment comment = new Comment();
        comment.setUserId(Integer.parseInt(userId));
        comment.setType(new Byte(type));
        if (new Byte(type) == 0){       //歌曲
            comment.setSongId(Integer.parseInt(songId));
        }else{                          //歌单
            comment.setSongListId(Integer.parseInt(songListId));
        }
        comment.setContent(content);
        boolean flag = commentService.insert(comment);
        if (flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"评论成功。。");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"评论失败");
        return jsonObject;
    }

    /**
     * 修改评论
     * @param request
     * @return
     */
    @PostMapping("/update")
    public Object updateComment(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();
        String userId = request.getParameter("userId").trim();
        String songId = request.getParameter("songId").trim();
        String type = request.getParameter("type").trim();
        String content = request.getParameter("content").trim();
        String songListId = request.getParameter("songListId").trim();

        Comment comment = new Comment();
        comment.setId(Integer.parseInt(id));
        comment.setUserId(Integer.parseInt(userId));
        comment.setType(new Byte(type));
        if (songId != null && songId.equals("")){
            songId = null;
        }else{
            comment.setSongId(Integer.parseInt(songId));
        }

        if (songListId != null && songListId.equals("")){
            songListId = null;
        }else{
            comment.setSongListId(Integer.parseInt(songListId));
        }
        comment.setContent(content);
        boolean flag = commentService.update(comment);
        if (flag){
            jsonObject.put(Consts.MSG,"修改成功..");
            jsonObject.put(Consts.CODE,1);
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"修改失败");
        return jsonObject;
    }

    /**
     * 删除评论
     * @param request
     * @return
     */
    @GetMapping("/delete")
    public Object deleteComment(HttpServletRequest request){
        String id = request.getParameter("id").trim();
        boolean flag = commentService.delete(Integer.parseInt(id));
        return flag;
    }

    /**
     * 通过主键查询
     * @param request
     * @return
     */
    @GetMapping("/selectById")
    public Object selectByPrimaryKey(HttpServletRequest request){
        String id = request.getParameter("id").trim();
        return commentService.selectByPrimaryKey(Integer.parseInt(id));
    }

    /**
     * 查询所有评论
     * @return
     */
    @GetMapping("/allComment")
    public Object allComment(){
        return commentService.allComment();
    }

    /**
     * 根据指定的歌曲下的评论
     * @param request
     * @return
     */
    @GetMapping("/commentOfSongId")
    public Object commentOfSongId(HttpServletRequest request){
        String songId = request.getParameter("songId").trim();
        return commentService.commentOfSongId(Integer.parseInt(songId));
    }

    /**
     * 某个歌单下的评论
     * */
    @GetMapping("/commentOfSongListId")
    public Object commentOfSongListId(HttpServletRequest request){
        String songListId = request.getParameter("songListId").trim();
        return commentService.commentOfSongListId(Integer.parseInt(songListId));
    }

    @RequestMapping(value = "/like",method = RequestMethod.POST)
    public Object likeComment(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();
        String up =request.getParameter("up").trim();

        Comment comment = new Comment();
        comment.setId(Integer.parseInt(id));
        comment.setUp(Integer.parseInt(up));
        boolean flag = commentService.update(comment);
        if (flag){
            jsonObject.put(Consts.MSG,"点赞成功");
            jsonObject.put(Consts.CODE,1);
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"点赞失败");
        return jsonObject;
    }
}
