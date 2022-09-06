package com.springboot.travel.services;

import com.springboot.travel.exception.SpringbootException;
import com.springboot.travel.pojo.ScenicSpot;

import java.util.List;

public interface ScenicSpotService {

    List<ScenicSpot> selectPageSpot(int page , int size);

    List<ScenicSpot> selectAllSpot();

    List<ScenicSpot> selectBySpotAddress(String address);

    void insertSpot(ScenicSpot scenicSpot) throws SpringbootException;

    void deleteSpot(int spotId) throws SpringbootException;

    ScenicSpot selectById(int spotId);

    void editSpot(ScenicSpot scenicSpot) throws SpringbootException;

    List<ScenicSpot> selectBySetCard();

    void increaseHot(int scenicSpotId) throws SpringbootException;

    void reduceHot(int scenicSpotId) throws SpringbootException;

    List<ScenicSpot> selectIndexSpot(int userId , int page , int size);

    List<ScenicSpot> selectPCenterSpot(int userId);
}
