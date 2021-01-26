package com.lifeifeiaa.service.impl;

import com.lifeifeiaa.dao.ListSongDao;
import com.lifeifeiaa.domain.ListSong;
import com.lifeifeiaa.service.ListSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListSongServiceImpl implements ListSongService {

    @Autowired
    private ListSongDao listSongDao;

    @Override
    public boolean insert(ListSong listSong) {
        return listSongDao.insert(listSong)>0;
    }

    @Override
    public boolean update(ListSong listSong) {
        return listSongDao.update(listSong)>0;
    }

    @Override
    public boolean delete(Integer id) {
        return listSongDao.delete(id)>0;
    }

    @Override
    public boolean deleteBySongIdAndSongListId(Integer songId, Integer songListId) {
        return listSongDao.deleteBySongIdAndSongListId(songId,songListId)>0;
    }

    @Override
    public ListSong selectByPrimaryKey(Integer id) {
        return listSongDao.selectByPrimaryKey(id);
    }

    @Override
    public List<ListSong> allListSong() {
        return listSongDao.allListSong();
    }

    @Override
    public List<ListSong> listSongBySongListId(Integer songListId) {
        return listSongDao.listSongBySongListId(songListId);
    }
}
