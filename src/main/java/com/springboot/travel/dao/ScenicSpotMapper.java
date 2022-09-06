package com.springboot.travel.dao;

import com.springboot.travel.pojo.ScenicSpot;
import com.springboot.travel.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ScenicSpotMapper {
    int deleteByPrimaryKey(Integer scenicSpotId);

    int insert(ScenicSpot record);

    int insertSelective(ScenicSpot record);

    ScenicSpot selectByPrimaryKey(Integer scenicSpotId);

    int updateByPrimaryKeySelective(ScenicSpot record);

    int updateByPrimaryKey(ScenicSpot record);

    List<ScenicSpot> selectSpotList();

    List<ScenicSpot> selectSpotPageList(int page , int size);

    List<ScenicSpot> selectBySpotAddress(String address);

    List<ScenicSpot> selectByCart();

    List<ScenicSpot> selectIndexSpot(int userId , int page , int size);

    List<ScenicSpot> selectPCenterSpot(int userId);
}