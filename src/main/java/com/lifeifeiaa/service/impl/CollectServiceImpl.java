package com.lifeifeiaa.service.impl;

import com.lifeifeiaa.dao.CollectDao;
import com.lifeifeiaa.domain.Collect;
import com.lifeifeiaa.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectServiceImpl implements CollectService {

    @Autowired
    private CollectDao collectDao;

    @Override
    public boolean insert(Collect collect) {
        return collectDao.insert(collect)>0;
    }

    @Override
    public boolean delete(Integer id) {
        return collectDao.delete(id)>0;
    }

    @Override
    public boolean delCollect(Integer userId, Integer songId) {
        return collectDao.delCollect(userId,songId)>0;
    }

    @Override
    public List<Collect> allCollect() {
        return collectDao.allCollect();
    }

    @Override
    public List<Collect> collectOfUserId(Integer userId) {
        return collectDao.collectOfUserId(userId);
    }

    @Override
    public boolean existSongId(Integer userId, Integer songId) {
        return collectDao.existSongId(userId,songId)>0;
    }
}
