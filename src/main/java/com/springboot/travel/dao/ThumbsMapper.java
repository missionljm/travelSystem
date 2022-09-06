package com.springboot.travel.dao;

import com.springboot.travel.pojo.Thumbs;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ThumbsMapper {
    int deleteByPrimaryKey(Integer thumbsId);

    int insert(Thumbs record);

    int insertSelective(Thumbs record);

    Thumbs selectByPrimaryKey(Integer thumbsId);

    int updateByPrimaryKeySelective(Thumbs record);

    int updateByPrimaryKey(Thumbs record);

    Thumbs selectThumbs(int userId , int scenicSpotId);
}