package com.springboot.travel.dao;

import com.springboot.travel.pojo.Guide;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface GuideMapper {
    int deleteByPrimaryKey(Integer guideId);

    int insert(Guide record);

    int insertSelective(Guide record);

    Guide selectByPrimaryKey(Integer guideId);

    int updateByPrimaryKeySelective(Guide record);

    int updateByPrimaryKey(Guide record);

    List<Guide> selectAll();

    List<Guide> selectPageGuide(int page , int size);

    List<Guide> selectGuideByName(String guideName);
}