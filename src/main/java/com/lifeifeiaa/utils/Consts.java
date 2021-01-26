package com.lifeifeiaa.utils;

import java.io.File;

/**
 * 常量
 * */
public class Consts {

    /*登录名*/
    public static final String NAME = "name";
    /*返回码*/
    public static final String CODE = "code";
    /*返回信息*/
    public static final String MSG = "msg";

    //删除歌手多余图片
    public static boolean delSingerPic(String path){
        if (path.equals("/img/singerPic/singer.jpg")){
            //默认图片未修改，不删除
            return true;
        }else{
            String[] name = path.split("/");
            String picPath = name[3];
            String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                    +System.getProperty("file.separator")+"singerPic"+System.getProperty("file.separator")+picPath;
            File file = new File(filePath);
            return file.delete();//true为 删除成功，false为删除失败
        }
    }

    //删除用户的图片
    public static boolean delConsumerPic(String path){
        if (path.equals("/img/consumerPic/consumer.jpg")){
            //默认图片未修改，不删除
            return true;
        }else{
            String[] name = path.split("/");
            String picPath = name[3];
            String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                    +System.getProperty("file.separator")+"consumerPic"+System.getProperty("file.separator")+picPath;
            File file = new File(filePath);
            return file.delete();//true为 删除成功，false为删除失败
        }
    }

    //删除歌单图片
    public static boolean delSongListPic(String path){
        if (path.equals("/img/songListPic/songList.jpg")){
            //默认图片未修改，不删除
            return true;
        }else{
            String[] name = path.split("/");
            String picPath = name[3];
            String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                    +System.getProperty("file.separator")+"songListPic"+System.getProperty("file.separator")+picPath;
            File file = new File(filePath);
            return file.delete();//true为 删除成功，false为删除失败
        }
    }

    //删除歌曲图片
    public static boolean delSongPic(String path){
        if (path.equals("/img/songPic/song.jpg")){
            //默认图片未修改，不删除
            return true;
        }else{
            String[] name = path.split("/");
            String picPath = name[3];
            String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                    +System.getProperty("file.separator")+"songPic"+System.getProperty("file.separator")+picPath;
            File file = new File(filePath);
            return file.delete();//true为 删除成功，false为删除失败
        }
    }
    //删除歌曲
    public static void delSongUrl(String path){
        String[] name = path.split("/");
        String picPath = name[2];
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"song"
                +System.getProperty("file.separator")+picPath;
        File file = new File(filePath);
        file.delete();
    }
}
