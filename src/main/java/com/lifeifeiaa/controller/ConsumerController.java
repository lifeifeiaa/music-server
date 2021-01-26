package com.lifeifeiaa.controller;

import com.alibaba.fastjson.JSONObject;
import com.lifeifeiaa.domain.Consumer;
import com.lifeifeiaa.service.ConsumerService;
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
 * 用户控制层
 * */
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    /**
     * 添加用户
     * @param request
     * @return json对象
     */
    @PostMapping("/add")
    public Object addConsumer(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();

        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String sex = request.getParameter("sex").trim();
        String phoneNum = request.getParameter("phoneNum").trim();
        String email = request.getParameter("email").trim();
        String birth = request.getParameter("birth").trim();
        String introduction = request.getParameter("introduction").trim();
        String location = request.getParameter("location").trim();
        String avatar = request.getParameter("avatar").trim();

        Consumer consumer1 = consumerService.getByUsername(username);
        if (consumer1 != null){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"用户已存在，请重新添加用户");
            return jsonObject;
        }

        if (username==null||username.equals("")){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"用户名不能为空");
            return jsonObject;
        }
        if (password==null||password.equals("")){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"密码不能为空");
            return jsonObject;
        }
        //转换时间
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = new Date();
        try {
            birthDate = dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Consumer consumer = new Consumer();
        consumer.setUsername(username);
        consumer.setPassword(password);
        consumer.setSex(new Byte(sex));
        consumer.setPhoneNum(phoneNum);
        consumer.setEmail(email);
        consumer.setBirth(birthDate);
        consumer.setIntroduction(introduction);
        consumer.setLocation(location);
        consumer.setAvatar(avatar);
        boolean flag = consumerService.insert(consumer);
        if (flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"用户添加成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"用户添加失败");
        return jsonObject;
    }

    /**
     * 修改用户
     * @param request
     * @return
     */
    @PostMapping("/update")
    public Object updateConsumer(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String sex = request.getParameter("sex").trim();
        String phoneNum = request.getParameter("phoneNum").trim();
        String email = request.getParameter("email").trim();
        String birth = request.getParameter("birth").trim();
        String introduction = request.getParameter("introduction").trim();
        String location = request.getParameter("location").trim();

        if (username==null||username.equals("")){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"用户名不能为空。");
            return jsonObject;
        }
        if (password==null||password.equals("")){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"密码不能为空。");
            return jsonObject;
        }

        //转换时间
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = new Date();
        try {
            birthDate = dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Consumer consumer = new Consumer();
        consumer.setId(Integer.parseInt(id));
        consumer.setUsername(username);
        consumer.setPassword(password);
        consumer.setSex(new Byte(sex));
        consumer.setPhoneNum(phoneNum);
        consumer.setEmail(email);
        consumer.setBirth(birthDate);
        consumer.setIntroduction(introduction);
        consumer.setLocation(location);
        boolean flag = consumerService.update(consumer);
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
     * 删除用户
     * @param request
     * @return
     */
    @GetMapping("/delete")
    public Object deleteConsumer(HttpServletRequest request){
        String id = request.getParameter("id").trim();
        Consumer consumer = consumerService.selectByPrimaryKey(Integer.parseInt(id));
        String path = consumer.getAvatar();
        Consts.delConsumerPic(path);
        boolean flag = consumerService.delete(Integer.parseInt(id));
        return flag;
    }

    /**
     * 通过主键查询歌手
     * @param request
     * @return
     */
    @GetMapping("/selectById")
    public Object selectByPrimaryKey(HttpServletRequest request){
        String id = request.getParameter("id").trim();
        return consumerService.selectByPrimaryKey(Integer.parseInt(id));
    }

    /**
     * 查询所有用户
     * @return
     */
    @GetMapping("/allConsumer")
    public Object allConsumer(){
        return consumerService.allConsumer();
    }


    /**
     * 更新歌手图片
     */
    @PostMapping("/updateConsumerPic")
    public Object updateConsumerPic(@RequestParam("file")MultipartFile avatarFile,@RequestParam("id") int id){
        Consumer consumer1 = consumerService.selectByPrimaryKey(id);
        String path = consumer1.getAvatar();
        Consts.delConsumerPic(path);
        JSONObject jsonObject = new JSONObject();
        if (avatarFile.isEmpty()){
            jsonObject.put(Consts.MSG,"文件上传失败");
            jsonObject.put(Consts.CODE,0);
            return jsonObject;
        }
        //文件名=当前时间到毫秒值+原来的文件名
        String fileName = System.currentTimeMillis()+avatarFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                +System.getProperty("file.separator")+"consumerPic";
        //如果文件路径不存在，新增该路径
        File file = new File(filePath);
        if (!file.exists()){
            file.mkdir();
        }
        //实际的文件路径
        File dest = new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里的相对文件地址
        String storeAvatarPath = "/img/consumerPic/"+fileName;
        try {
            avatarFile.transferTo(dest);
            Consumer consumer = new Consumer();
            consumer.setId(id);
            consumer.setAvatar(storeAvatarPath);
            boolean flag = consumerService.update(consumer);
            if (flag){
                jsonObject.put(Consts.MSG,"上传成功");
                jsonObject.put(Consts.CODE,1);
                jsonObject.put("avatar",storeAvatarPath);
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
     * 前端用户登录
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Object login(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();

        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        if (username==null||username.equals("")){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"用户名不能为空");
            return jsonObject;
        }
        if (password==null||password.equals("")){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"密码不能为空");
            return jsonObject;
        }
        boolean flag = consumerService.verifyPassword(username,password);
        if (flag){
            jsonObject.put(Consts.MSG,"登录成功");
            jsonObject.put(Consts.CODE,1);
            jsonObject.put("userMsg",consumerService.getByUsername(username));
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"用户名或密码错误。。");
        return jsonObject;
    }
}
