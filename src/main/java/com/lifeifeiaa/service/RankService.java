package com.lifeifeiaa.service;

import com.lifeifeiaa.domain.Rank;

public interface RankService {

    //增加
    boolean insert(Rank rank);
    //查总分
    int selectScoreSum(Integer songListId);
    //查总评分人
    int selectRankNum(Integer songListId);
    //计算平均分
    int rankOfSongListId(Integer songListId);
}
