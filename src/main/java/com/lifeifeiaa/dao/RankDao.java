package com.lifeifeiaa.dao;

import com.lifeifeiaa.domain.Rank;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RankDao {

    //增加
    int insert(Rank rank);
    //查总分
    int selectScoreSum(Integer songListId);
    //查总评分人
    int selectRankNum(Integer songListId);
}
