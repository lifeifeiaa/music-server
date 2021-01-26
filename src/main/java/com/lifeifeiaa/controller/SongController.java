package com.lifeifeiaa.controller;

import com.alibaba.fastjson.JSONObject;
import com.lifeifeiaa.domain.Singer;
import com.lifeifeiaa.domain.Song;
import com.lifeifeiaa.service.SongService;
import com.lifeifeiaa.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * 歌曲管理Controller
 */
@RestController
@RequestMapping("/song")
public class SongController {

    @Autowired
    private SongService songService;

    /**
     * 添加歌曲
     * @param request
     * @return
     */
    @PostMapping("/add")
    public Object addSong(HttpServletRequest request, @RequestParam("file") MultipartFile mpFile){
        JSONObject jsonObject = new JSONObject();
        String singerId = request.getParameter("singerId").trim();
        String name = request.getParameter("name").trim();
        String introduction = request.getParameter("introduction").trim();
        String pic = "/img/songPic/song.jpg";
        String lyric = request.getParameter("lyric").trim();
        //上传歌曲文件
        if (mpFile.isEmpty()){
            jsonObject.put(Consts.MSG,"歌曲上传失败");
            jsonObject.put(Consts.CODE,0);
            return jsonObject;
        }
        //文件名=当前时间到毫秒值+原来的文件名
        String fileName = System.currentTimeMillis()+mpFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"song";
        //如果文件路径不存在，新增该路径
        File file = new File(filePath);
        if (!file.exists()){
            file.mkdir();
        }
        //实际的文件路径
        File dest = new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里的相对文件地址
        String storeUrlPath = "/song/"+fileName;
        try {
            mpFile.transferTo(dest);
            Song song = new Song();
            song.setSingerId(Integer.parseInt(singerId));
            song.setName(name);
            song.setIntroduction(introduction);
            song.setPic(pic);
            song.setLyric(lyric);
            song.setUrl(storeUrlPath);
            boolean flag = songService.insert(song);
            if (flag){
                jsonObject.put(Consts.MSG,"保存成功");
                jsonObject.put(Consts.CODE,1);
                jsonObject.put("pic",storeUrlPath);
                return jsonObject;
            }else{
                jsonObject.put(Consts.CODE,0);
                jsonObject.put(Consts.MSG,"保存失败");
                return jsonObject;
            }
        } catch (IOException e) {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"保存失败"+e.getMessage());
            return jsonObject;
        }finally {
            return jsonObject;
        }
    }

    /**
     * 根据歌手id查询歌曲
     */
    @GetMapping("/singer/detail")
    public Object songBySingerId(HttpServletRequest request){
        String singerId = request.getParameter("singerId");
        return songService.songBySingerId(Integer.parseInt(singerId));
    }

    /**
     * 修改歌曲信息
     */
    @PostMapping("/update")
    public Object updateSong(HttpServletRequest request){
        String id = request.getParameter("id").trim();
        String name = request.getParameter("name").trim();
        String introduction = request.getParameter("introduction").trim();
        String lyric = request.getParameter("lyric").trim();

        Song song = new Song();
        song.setId(Integer.parseInt(id));
        song.setName(name);
        song.setIntroduction(introduction);
        song.setLyric(lyric);
        boolean flag = songService.update(song);
        JSONObject jsonObject = new JSONObject();
        if (flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"修改成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"修改失败");
        return jsonObject;
    }

    /**
     * 删除歌曲
     * @param request
     * @return
     */
    @GetMapping("/delete")
    public Object deleteSong(HttpServletRequest request){
        String id = request.getParameter("id").trim();
        Song song1 = songService.selectByPrimaryKey(Integer.parseInt(id));
        String path = song1.getPic();
        String url = song1.getUrl();
        Consts.delSongPic(path);
        Consts.delSongUrl(url);
        boolean flag = songService.delete(Integer.parseInt(id));
        return flag;
    }

    /**
     * 更新歌曲图片
     * @param avatorFile
     * @param id
     * @return
     */
    @PostMapping("/updateSongPic")
    public Object updateSongPic(@RequestParam("file")MultipartFile avatorFile,@RequestParam("id") int id){
        Song song1 = songService.selectByPrimaryKey(id);
        String path = song1.getPic();
        Consts.delSongPic(path);
        JSONObject jsonObject = new JSONObject();
        if (avatorFile.isEmpty()){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"文件上传失败");
            return jsonObject;
        }
        //文件名=当前时间到毫秒值+原来的文件名
        String fileName = System.currentTimeMillis()+avatorFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                +System.getProperty("file.separator")+"songPic";
        //如果文件路径不存在，新增该路径
        File file = new File(filePath);
        if (!file.exists()){
            file.mkdir();
        }
        //实际的文件路径
        File dest = new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里的相对文件地址
        String storeAvatorPath = "/img/songPic/"+fileName;
        try {
            avatorFile.transferTo(dest);
            Song song = new Song();
            song.setId(id);
            song.setPic(storeAvatorPath);
            boolean flag = songService.update(song);
            if (flag){
                jsonObject.put(Consts.CODE,1);
                jsonObject.put(Consts.MSG,"上传成功");
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

    /**
     * 更新歌曲
     * @param avatorFile
     * @param id
     * @return
     */
    @RequestMapping(value = "/updateSongUrl",method = RequestMethod.POST)
    public Object updateSongUrl(@RequestParam("file")MultipartFile avatorFile,@RequestParam("id") int id){
        JSONObject jsonObject = new JSONObject();
        if (avatorFile.isEmpty()){
            jsonObject.put(Consts.MSG,"歌曲上传失败。");
            jsonObject.put(Consts.CODE,0);
            return jsonObject;
        }
        //文件名=当前时间到毫秒值+原来的文件名
        String fileName = System.currentTimeMillis()+avatorFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"song";
        //如果文件路径不存在，新增该路径
        File file = new File(filePath);
        if (!file.exists()){
            file.mkdir();
        }
        //实际的文件路径
        File dest = new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里的相对文件地址
        String storeAvatorPath = "/song/"+fileName;
        try {
            avatorFile.transferTo(dest);
            Song song = new Song();
            song.setId(id);
            song.setUrl(storeAvatorPath);
            boolean flag = songService.update(song);
            if (flag){
                jsonObject.put(Consts.CODE,1);
                jsonObject.put(Consts.MSG,"上传成功");
                jsonObject.put("song",storeAvatorPath);
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

    /**
     * 根据歌曲id查询歌曲
     */
    @GetMapping("/songOfSongId")
    public Object songOfSongId(HttpServletRequest request){
        String songId = request.getParameter("songId");
        return songService.selectByPrimaryKey(Integer.parseInt(songId));
    }

    /**
     * 根据歌曲名查询歌曲
     */
    @GetMapping("/songOfSongName")
    public Object songOfSongName(HttpServletRequest request){
        String songName = request.getParameter("songName");
        return songService.songByName(songName);
    }

    /**
     * 根据歌曲名模糊查询歌曲
     */
    @GetMapping("/likeSongOfName")
    public Object likeSongOfName(HttpServletRequest request){
        String songName = request.getParameter("songName");
        return songService.likeSongByName(songName);
    }

    /**
     * 查询所有的歌曲
     */
    @GetMapping("/allSong")
    public Object allSong(){
        return songService.allSong();
    }
}
