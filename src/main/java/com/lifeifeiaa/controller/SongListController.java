package com.lifeifeiaa.controller;

import com.alibaba.fastjson.JSONObject;
import com.lifeifeiaa.domain.SongList;
import com.lifeifeiaa.service.SongListService;
import com.lifeifeiaa.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * 歌单控制层
 * */
@RestController
@RequestMapping("/songList")
public class SongListController {

    @Autowired
    private SongListService songListService;

    /**
     * 添加歌单
     * @param request
     * @return json对象
     */
    @PostMapping("/add")
    public Object addSongList(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        //转换时间
        String title = request.getParameter("title").trim();
        String pic = request.getParameter("pic").trim();
        String introduction = request.getParameter("introduction").trim();
        String style = request.getParameter("style").trim();

        SongList songList = new SongList();
        songList.setTitle(title);
        songList.setPic(pic);
        songList.setIntroduction(introduction);
        songList.setStyle(style);
        boolean flag = songListService.insert(songList);
        if (flag){
            jsonObject.put(Consts.MSG,"添加成功");
            jsonObject.put(Consts.CODE,1);
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"添加失败");
        return jsonObject;
    }

    /**
     * 修改歌单
     * @param request
     * @return
     */
    @PostMapping("/update")
    public Object updateSongList(HttpServletRequest request){
        String id = request.getParameter("id").trim();
        String title = request.getParameter("title").trim();
        String introduction = request.getParameter("introduction").trim();
        String style = request.getParameter("style").trim();

        SongList songList = new SongList();
        songList.setId(Integer.parseInt(id));
        songList.setTitle(title);
        songList.setIntroduction(introduction);
        songList.setStyle(style);
        boolean flag = songListService.update(songList);
        JSONObject jsonObject = new JSONObject();
        if (flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"修改成功.");
            return jsonObject;
        }
        jsonObject.put(Consts.MSG,"修改失败");
        jsonObject.put(Consts.CODE,0);
        return jsonObject;
    }

    /**
     * 删除歌单
     * @param request
     * @return
     */
    @GetMapping("/delete")
    public Object deleteSongList(HttpServletRequest request){
        String id = request.getParameter("id").trim();
        SongList songList = songListService.selectByPrimaryKey(Integer.parseInt(id));
        String path = songList.getPic();
        Consts.delSongListPic(path);
        boolean flag = songListService.delete(Integer.parseInt(id));
        return flag;
    }

    /**
     * 通过主键查询歌单
     * @param request
     * @return
     */
    @GetMapping("/selectById")
    public Object selectByPrimaryKey(HttpServletRequest request){
        String id = request.getParameter("id").trim();
        return songListService.selectByPrimaryKey(Integer.parseInt(id));
    }

    /**
     * 查询所有歌单
     * @return
     */
    @GetMapping("/allSongList")
    public Object allSongList(){
        return songListService.allSongList();
    }

    /**
     * 通过标题精确查询
     * @param request
     * @return
     */
    @GetMapping("/songListByTitle")
    public Object songListByTitle(HttpServletRequest request){
        String title = request.getParameter("title").trim();
        return songListService.songListByTitle(title);
    }

    /**
     * 通过标题模糊查询
     * @param request
     * @return
     */
    @GetMapping("/likeTitle")
    public Object likeTitle(HttpServletRequest request){
        String title = request.getParameter("title").trim();
        return songListService.likeTitle(title);
    }

    /**
     * 通过风格模糊查询
     * @param request
     * @return
     */
    @GetMapping("/likeStyle")
    public Object likeStyle(HttpServletRequest request){
        String style = request.getParameter("style").trim();
        return songListService.likeStyle(style);
    }

    /**
     * 更新歌单图片
     */
    @PostMapping("/updateSongListPic")
    public Object updateSongListPic(@RequestParam("file")MultipartFile avatorFile,@RequestParam("id") int id){
        SongList songList1 = songListService.selectByPrimaryKey(id);
        String path = songList1.getPic();
        Consts.delSongListPic(path);
        JSONObject jsonObject = new JSONObject();
        if (avatorFile.isEmpty()){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"文件上传失败.");
            return jsonObject;
        }
        //文件名=当前时间到毫秒值+原来的文件名
        String fileName = System.currentTimeMillis()+avatorFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                +System.getProperty("file.separator")+"songListPic";
        //如果文件路径不存在，新增该路径
        File file = new File(filePath);
        if (!file.exists()){
            file.mkdir();
        }
        //实际的文件路径
        File dest = new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里的相对文件地址
        String storeAvatorPath = "/img/songListPic/"+fileName;
        try {
            avatorFile.transferTo(dest);
            SongList songList = new SongList();
            songList.setId(id);
            songList.setPic(storeAvatorPath);
            boolean flag = songListService.update(songList);
            if (flag){
                jsonObject.put(Consts.MSG,"上传成功.");
                jsonObject.put(Consts.CODE,1);
                jsonObject.put("pic",storeAvatorPath);
                return jsonObject;
            }else{
                jsonObject.put(Consts.CODE,0);
                jsonObject.put(Consts.MSG,"上传失败");
                return jsonObject;
            }
        } catch (IOException e) {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"上传失败"+e.getMessage());
            return jsonObject;
        }finally {
            return jsonObject;
        }
    }
}
