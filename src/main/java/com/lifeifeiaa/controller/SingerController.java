package com.lifeifeiaa.controller;

import com.alibaba.fastjson.JSONObject;
import com.lifeifeiaa.domain.Singer;
import com.lifeifeiaa.service.SingerService;
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
 * 歌手控制层
 * */
@RestController
@RequestMapping("/singer")
public class SingerController {

    @Autowired
    private SingerService singerService;

    /**
     * 添加歌手
     * @param request
     * @return json对象
     */
    @PostMapping("/add")
    public Object addSinger(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        //转换时间
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = new Date();
        String name = request.getParameter("name").trim();
        String sex = request.getParameter("sex").trim();
        String pic = request.getParameter("pic").trim();
        String birth = request.getParameter("birth").trim();
        String location = request.getParameter("location").trim();
        String introduction = request.getParameter("introduction").trim();
        try {
            birthDate = dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Singer singer = new Singer();
        singer.setName(name);
        singer.setSex(new Byte(sex));
        singer.setPic(pic);
        singer.setBirth(birthDate);
        singer.setLocation(location);
        singer.setIntroduction(introduction);
        boolean flag = singerService.insert(singer);
        if (flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"添加成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"添加失败");
        return jsonObject;
    }

    /**
     * 修改歌手
     * @param request
     * @return
     */
    @PostMapping("/update")
    public Object updateSinger(HttpServletRequest request){
        String id = request.getParameter("id").trim();
        String name = request.getParameter("name").trim();
        String sex = request.getParameter("sex").trim();
        String birth = request.getParameter("birth").trim();
        String location = request.getParameter("location").trim();
        String introduction = request.getParameter("introduction").trim();
        //转换时间
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = new Date();
        try {
            birthDate = dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Singer singer = new Singer();
        singer.setId(Integer.parseInt(id));
        singer.setName(name);
        singer.setSex(new Byte(sex));
        singer.setBirth(birthDate);
        singer.setLocation(location);
        singer.setIntroduction(introduction);
        boolean flag = singerService.update(singer);
        JSONObject jsonObject = new JSONObject();
        if (flag){
            jsonObject.put(Consts.MSG,"修改成功");
            jsonObject.put(Consts.CODE,1);
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"修改失败");
        return jsonObject;
    }

    /**
     * 删除歌手
     * @param request
     * @return
     */
    @GetMapping("/delete")
    public Object deleteSinger(HttpServletRequest request){
        String id = request.getParameter("id").trim();
        Singer singer = singerService.selectByPrimaryKey(Integer.parseInt(id));
        String path = singer.getPic();
        String picPath = path.substring(15);
        boolean flag = singerService.delete(Integer.parseInt(id));
        if (path == "/img/singerPic/singer.jpg"){
            return flag;
        }else{
            String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                    +System.getProperty("file.separator")+"singerPic"+System.getProperty("file.separator")+picPath;
            File file = new File(filePath);
            if (file.delete()){
                return flag;
            }else{
                return false;
            }
        }
    }

    /**
     * 通过主键查询歌手
     * @param request
     * @return
     */
    @GetMapping("/selectById")
    public Object selectByPrimaryKey(HttpServletRequest request){
        String id = request.getParameter("id").trim();
        return singerService.selectByPrimaryKey(Integer.parseInt(id));
    }

    /**
     * 查询所有歌手
     * @return
     */
    @GetMapping("/allSinger")
    public Object allSinger(){
        return singerService.allSinger();
    }

    /**
     * 通过名字模糊查询
     * @param request
     * @return
     */
    @GetMapping("/singerByName")
    public Object singerByName(HttpServletRequest request){
        String name = request.getParameter("name").trim();
        return singerService.singerByName(name);
    }

    /**
     * 通过性别查询歌手
     * */
    @GetMapping("/singerBySex")
    public Object singerBySex(HttpServletRequest request){
        String sex = request.getParameter("sex").trim();
        return singerService.singerBySex(Integer.parseInt(sex));
    }

    /**
     * 更新歌手图片
     */
    @PostMapping("/updateSingerPic")
    public Object updateSingerPic(@RequestParam("file")MultipartFile avatorFile,@RequestParam("id") int id){
        //跟新图片前先删除，原先图片
        Singer singer1 = singerService.selectByPrimaryKey(id);
        String path = singer1.getPic();
        Consts.delSingerPic(path);
        JSONObject jsonObject = new JSONObject();
        if (avatorFile.isEmpty()){
            jsonObject.put(Consts.MSG,"文件上传失败");
            jsonObject.put(Consts.CODE,0);
            return jsonObject;
        }
        //文件名=当前时间到毫秒值+原来的文件名
        String fileName = System.currentTimeMillis()+avatorFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                +System.getProperty("file.separator")+"singerPic";
        //如果文件路径不存在，新增该路径
        File file = new File(filePath);
        if (!file.exists()){
            file.mkdir();
        }
        //实际的文件路径
        File dest = new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里的相对文件地址
        String storeAvatorPath = "/img/singerPic/"+fileName;
        try {
            avatorFile.transferTo(dest);
            Singer singer = new Singer();
            singer.setId(id);
            singer.setPic(storeAvatorPath);
            boolean flag = singerService.update(singer);
            if (flag){
                jsonObject.put(Consts.MSG,"上传成功");
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
