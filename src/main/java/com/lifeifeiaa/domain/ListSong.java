package com.lifeifeiaa.domain;

import java.io.Serializable;

/**
 * 歌单歌曲
 */
public class ListSong implements Serializable {

    private Integer id;
    private Integer songId; //歌曲id
    private Integer songListId; //歌单id

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSongId() {
        return songId;
    }

    public void setSongId(Integer songId) {
        this.songId = songId;
    }

    public Integer getSongListId() {
        return songListId;
    }

    public void setSongListId(Integer songListId) {
        this.songListId = songListId;
    }
}
