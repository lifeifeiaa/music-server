package com.lifeifeiaa.service.impl;

import com.lifeifeiaa.dao.SongDao;
import com.lifeifeiaa.domain.Song;
import com.lifeifeiaa.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 歌曲service实现类
 */
@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongDao songDao;

    @Override
    public boolean insert(Song song) {
        return songDao.insert(song)>0;
    }

    @Override
    public boolean update(Song song) {
        return songDao.update(song)>0;
    }

    @Override
    public boolean delete(Integer id) {
        return songDao.delete(id)>0;
    }

    @Override
    public Song selectByPrimaryKey(Integer id) {
        return songDao.selectByPrimaryKey(id);
    }

    @Override
    public List<Song> allSong() {
        return songDao.allSong();
    }

    @Override
    public List<Song> songByName(String name) {
        return songDao.songByName(name);
    }

    @Override
    public List<Song> likeSongByName(String name) {
        return songDao.likeSongByName("%" + name + "%");
    }

    @Override
    public List<Song> songBySingerId(Integer singerId) {
        return songDao.songBySingerId(singerId);
    }
}
