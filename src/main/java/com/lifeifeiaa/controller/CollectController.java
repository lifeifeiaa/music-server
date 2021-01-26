package com.lifeifeiaa.controller;

import com.alibaba.fastjson.JSONObject;
import com.lifeifeiaa.domain.Collect;
import com.lifeifeiaa.service.CollectService;
import com.lifeifeiaa.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 收藏控制层
 * */
@RestController
@RequestMapping("/collect")
public class CollectController {

    @Autowired
    private CollectService collectService;

    /**
     * 添加
     * @param request
     * @return json对象
     */
    @PostMapping("/add")
    public Object addCollect(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();

        String type = request.getParameter("type");
        String userId = request.getParameter("userId");
        String songId = request.getParameter("songId");

        if (songId == null || songId.equals("")){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"收藏歌曲失败");
            return jsonObject;
        }
        if (collectService.existSongId(Integer.parseInt(userId), Integer.parseInt(songId))) {
            jsonObject.put(Consts.CODE,2);
            jsonObject.put(Consts.MSG,"已经收藏了该歌曲");
            return jsonObject;
        }
        Collect collect = new Collect();
        collect.setType(new Byte(type));
        collect.setUserId(Integer.parseInt(userId));
        collect.setSongId(Integer.parseInt(songId));
        boolean flag = collectService.insert(collect);
        if (flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"收藏成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"收藏失败");
        return jsonObject;
    }

    /**
     * 删除收藏
     * @param request
     * @return
     */
    @GetMapping("/delete")
    public Object deleteCollect(HttpServletRequest request){
        String id = request.getParameter("id").trim();
        boolean flag = collectService.delete(Integer.parseInt(id));
        return flag;
    }

    /**
     * 删除指定用户的收藏
     * @param request
     * @return
     */
    @GetMapping("/delCollect")
    public Object deleteCollectByUserIdAndSongId(HttpServletRequest request){
        String userId = request.getParameter("userId").trim();
        String songId = request.getParameter("songId").trim();
        boolean flag = collectService.delCollect(Integer.parseInt(userId),Integer.parseInt(songId));
        return flag;
    }

    /**
     * 查询所有收藏
     * @return
     */
    @GetMapping("/allCollect")
    public Object allCollect(){
        return collectService.allCollect();
    }

    /**
     * 查询某个用户的收藏列表
     * @param request
     * @return
     */
    @GetMapping("/collectOfUserId")
    public Object collectOfUserId(HttpServletRequest request){
        String userId = request.getParameter("userId").trim();
        return collectService.collectOfUserId(Integer.parseInt(userId));
    }
}
