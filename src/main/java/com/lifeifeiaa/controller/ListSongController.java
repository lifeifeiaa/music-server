package com.lifeifeiaa.controller;

import com.alibaba.fastjson.JSONObject;
import com.lifeifeiaa.domain.ListSong;
import com.lifeifeiaa.service.ListSongService;
import com.lifeifeiaa.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 歌单歌曲管理Controller
 */
@RestController
@RequestMapping("/listSong")
public class ListSongController {

    @Autowired
    private ListSongService listSongService;

    /**
     * 给歌单添加歌曲
     * @param request
     * @return
     */
    @PostMapping("/add")
    public Object addListSong(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String songId = request.getParameter("songId").trim();
        String songListId = request.getParameter("songListId").trim();
        ListSong listSong = new ListSong();
        listSong.setSongId(Integer.parseInt(songId));
        listSong.setSongListId(Integer.parseInt(songListId));
        boolean flag = listSongService.insert(listSong);
        if (flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"添加成功.");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"添加失败");
        return jsonObject;
    }

    /**
     * 根据歌单id查询歌曲
     */
    @GetMapping("/detail")
    public Object detail(HttpServletRequest request){
        String songListId = request.getParameter("songListId");
        return listSongService.listSongBySongListId(Integer.parseInt(songListId));
    }

    /**
     * 删除歌单的歌曲
     * @param request
     * @return
     */
    @GetMapping("/delete")
    public Object deleteListSong(HttpServletRequest request){
        /**
         * 删除本地文件，待做
         * */
        String songId = request.getParameter("songId").trim();
        String songListId = request.getParameter("songListId").trim();
        boolean flag = listSongService.deleteBySongIdAndSongListId(Integer.parseInt(songId),Integer.parseInt(songListId));
        return flag;
    }
}
