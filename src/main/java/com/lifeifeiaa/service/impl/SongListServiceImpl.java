package com.lifeifeiaa.service.impl;

import com.lifeifeiaa.dao.SongListDao;
import com.lifeifeiaa.domain.SongList;
import com.lifeifeiaa.service.SongListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 歌单接口实现类
 */
@Service
public class SongListServiceImpl implements SongListService {

    @Autowired
    private SongListDao songListDao;

    @Override
    public boolean insert(SongList songList) {
        return songListDao.insert(songList)>0;
    }

    @Override
    public boolean update(SongList songList) {
        return songListDao.update(songList)>0;
    }

    @Override
    public boolean delete(Integer id) {
        return songListDao.delete(id)>0;
    }

    @Override
    public SongList selectByPrimaryKey(Integer id) {
        return songListDao.selectByPrimaryKey(id);
    }

    @Override
    public List<SongList> allSongList() {
        return songListDao.allSongList();
    }

    @Override
    public List<SongList> songListByTitle(String title) {
        return songListDao.songListByTitle(title);
    }

    @Override
    public List<SongList> likeTitle(String title) {
        return songListDao.likeTitle("%" + title + "%");
    }

    @Override
    public List<SongList> likeStyle(String style) {
        return songListDao.likeStyle("%" + style + "%");
    }
}
