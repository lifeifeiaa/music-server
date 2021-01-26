package com.lifeifeiaa.service.impl;

import com.lifeifeiaa.dao.SingerDao;
import com.lifeifeiaa.domain.Singer;
import com.lifeifeiaa.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SingerServiceImpl implements SingerService {

    @Autowired
    private SingerDao singerDao;

    @Override
    public boolean insert(Singer singer) {
        return singerDao.insert(singer)>0;
    }

    @Override
    public boolean update(Singer singer) {
        return singerDao.update(singer)>0;
    }

    @Override
    public boolean delete(Integer id) {
        return singerDao.delete(id)>0;
    }

    @Override
    public Singer selectByPrimaryKey(Integer id) {
        return singerDao.selectByPrimaryKey(id);
    }

    @Override
    public List<Singer> allSinger() {
        return singerDao.allSinger();
    }

    @Override
    public List<Singer> singerByName(String name) {
        String username = "%"+name+"%";
        return singerDao.singerByName(username);
    }

    @Override
    public List<Singer> singerBySex(Integer sex) {
        return singerDao.singerBySex(sex);
    }
}
