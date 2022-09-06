package com.springboot.travel.services.impl;

import com.springboot.travel.dao.ScenicSpotMapper;
import com.springboot.travel.exception.SpringbootException;
import com.springboot.travel.exception.SpringbootExceptionEnum;
import com.springboot.travel.pojo.ScenicSpot;
import com.springboot.travel.services.ScenicSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("ScenicSpotService")
public class ScenicSpotServiceImpl implements ScenicSpotService {

    @Autowired
    private ScenicSpotMapper scenicSpotMapper;

    @Override
    public List<ScenicSpot> selectPageSpot(int page, int size) {
        return scenicSpotMapper.selectSpotPageList((page-1)*size , size);
    }

    @Override
    public List<ScenicSpot> selectAllSpot() {
        return scenicSpotMapper.selectSpotList();
    }

    @Override
    public List<ScenicSpot> selectBySpotAddress(String address) {
        return scenicSpotMapper.selectBySpotAddress(address);
    }

    @Override
    public void insertSpot(ScenicSpot scenicSpot) throws SpringbootException {
        scenicSpot.setCreateTime(new Date());
        scenicSpot.setScenicSpotHot(0);
        scenicSpot.setSetCard(0);
        int count = scenicSpotMapper.insertSelective(scenicSpot);
        if (count == 0){
            throw new SpringbootException(SpringbootExceptionEnum.INSERT_FAILED);
        }
    }

    @Override
    public void deleteSpot(int spotId) throws SpringbootException {
        int count = scenicSpotMapper.deleteByPrimaryKey(spotId);
        if (count == 0){
            throw new SpringbootException(SpringbootExceptionEnum.DELETE_ERROR);
        }
    }

    @Override
    public ScenicSpot selectById(int spotId) {
        return scenicSpotMapper.selectByPrimaryKey(spotId);
    }

    @Override
    public void editSpot(ScenicSpot scenicSpot) throws SpringbootException {
        ScenicSpot scenicSpot1 = scenicSpotMapper.selectByPrimaryKey(scenicSpot.getScenicSpotId());
        scenicSpot.setCreateTime(scenicSpot1.getCreateTime());
        int count = scenicSpotMapper.updateByPrimaryKey(scenicSpot);

        if (count == 0){
            throw new SpringbootException(SpringbootExceptionEnum.INSERT_FAILED);
        }
    }

    @Override
    public List<ScenicSpot> selectBySetCard() {
        return scenicSpotMapper.selectByCart();
    }

    @Override
    public void increaseHot(int scenicSpotId) throws SpringbootException {
        ScenicSpot scenicSpot = scenicSpotMapper.selectByPrimaryKey(scenicSpotId);
        scenicSpot.setScenicSpotHot(scenicSpot.getScenicSpotHot() + 1);
        scenicSpotMapper.updateByPrimaryKey(scenicSpot);
    }

    @Override
    public void reduceHot(int scenicSpotId) throws SpringbootException {
        ScenicSpot scenicSpot = scenicSpotMapper.selectByPrimaryKey(scenicSpotId);
        scenicSpot.setScenicSpotHot(scenicSpot.getScenicSpotHot() - 1);
        scenicSpotMapper.updateByPrimaryKey(scenicSpot);
    }

    @Override
    public List<ScenicSpot> selectIndexSpot(int userId, int page, int size) {
        return scenicSpotMapper.selectIndexSpot(userId , (page-1)*size , size);
    }

    @Override
    public List<ScenicSpot> selectPCenterSpot(int userId) {
        return scenicSpotMapper.selectPCenterSpot(userId);
    }
}
