package com.springboot.travel.dao;

import com.springboot.travel.pojo.News;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface NewsMapper {
    int deleteByPrimaryKey(Integer newId);

    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(Integer newId);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);

    List<News> selectAllNews();

    List<News> selectPageNews(int page , int size);

    List<News> selectNewByTitle(String newsTitle);
}