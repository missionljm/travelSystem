package com.springboot.travel.services.impl;

import com.springboot.travel.dao.GuideMapper;
import com.springboot.travel.exception.SpringbootException;
import com.springboot.travel.exception.SpringbootExceptionEnum;
import com.springboot.travel.pojo.Guide;
import com.springboot.travel.services.GuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("GuideService")
public class GuideServiceImpl implements GuideService {

    @Autowired
    private GuideMapper guideMapper;

    @Override
    public List<Guide> selectAllGuide() {
        return guideMapper.selectAll();
    }

    @Override
    public List<Guide> selectPageGuide(int page, int size) {
        return guideMapper.selectPageGuide((page-1)*size , size);
    }

    @Override
    public Guide selectById(int guideId) {
        return guideMapper.selectByPrimaryKey(guideId);
    }

    @Override
    public void deleteGuideById(int guideId) throws SpringbootException {
        int count = guideMapper.deleteByPrimaryKey(guideId);
        if (count == 0){
            throw new SpringbootException(SpringbootExceptionEnum.DELETE_ERROR);
        }
    }

    @Override
    public void editGuide(Guide guide) throws SpringbootException {
        int count = guideMapper.updateByPrimaryKey(guide);
        if (count == 0){
            throw new SpringbootException(SpringbootExceptionEnum.UPDATE_ERROR);
        }
    }

    @Override
    public void insertGuide(Guide guide) throws SpringbootException {
        guide.setCreateTime(new Date());
        int count = guideMapper.insertSelective(guide);
        if (count == 0){
            throw new SpringbootException(SpringbootExceptionEnum.INSERT_FAILED);
        }
    }

    @Override
    public List<Guide> selectGuideByName(String guideName) {
        return guideMapper.selectGuideByName(guideName);
    }
}
