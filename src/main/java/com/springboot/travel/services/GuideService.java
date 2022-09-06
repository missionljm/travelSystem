package com.springboot.travel.services;

import com.springboot.travel.exception.SpringbootException;
import com.springboot.travel.pojo.Guide;

import java.util.List;

public interface GuideService {

    List<Guide> selectAllGuide();

    List<Guide> selectPageGuide(int page , int size);

    Guide selectById(int guideId);

    void deleteGuideById(int guideId)throws SpringbootException;

    void editGuide(Guide guide) throws SpringbootException;

    void insertGuide(Guide guide) throws SpringbootException;

    List<Guide> selectGuideByName(String guideName);
}
