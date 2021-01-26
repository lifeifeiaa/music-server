package com.lifeifeiaa.service.impl;

import com.lifeifeiaa.dao.ConsumerDao;
import com.lifeifeiaa.domain.Consumer;
import com.lifeifeiaa.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private ConsumerDao consumerDao;

    @Override
    public boolean insert(Consumer consumer) {
        return consumerDao.insert(consumer)>0;
    }

    @Override
    public boolean update(Consumer consumer) {
        return consumerDao.update(consumer)>0;
    }

    @Override
    public boolean delete(Integer id) {
        return consumerDao.delete(id)>0;
    }

    @Override
    public Consumer selectByPrimaryKey(Integer id) {
        return consumerDao.selectByPrimaryKey(id);
    }

    @Override
    public List<Consumer> allConsumer() {
        return consumerDao.allConsumer();
    }

    @Override
    public boolean verifyPassword(String username, String password) {
        return consumerDao.verifyPassword(username,password)>0;
    }

    @Override
    public Consumer getByUsername(String username) {
        return consumerDao.getByUsername(username);
    }
}
