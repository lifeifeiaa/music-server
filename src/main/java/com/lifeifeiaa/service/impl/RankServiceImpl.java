package com.lifeifeiaa.service.impl;

import com.lifeifeiaa.dao.RankDao;
import com.lifeifeiaa.domain.Rank;
import com.lifeifeiaa.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RankServiceImpl implements RankService {

    @Autowired
    private RankDao rankDao;

    @Override
    public boolean insert(Rank rank) {
        return rankDao.insert(rank)>0;
    }

    @Override
    public int selectScoreSum(Integer songListId) {
        return rankDao.selectScoreSum(songListId);
    }

    @Override
    public int selectRankNum(Integer songListId) {
        return rankDao.selectRankNum(songListId);
    }

    @Transactional
    @Override
    public int rankOfSongListId(Integer songListId) {
        int rankNum = rankDao.selectRankNum(songListId);
        if (rankNum == 0){
            return 5;
        }
        return (rankDao.selectScoreSum(songListId)/rankNum);
    }
}
