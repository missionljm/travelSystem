package com.springboot.travel.services.impl;

import com.springboot.travel.dao.ThumbsMapper;
import com.springboot.travel.exception.SpringbootException;
import com.springboot.travel.exception.SpringbootExceptionEnum;
import com.springboot.travel.pojo.Thumbs;
import com.springboot.travel.services.ThumbsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("ThumbsService")
public class ThumbsServiceImpl implements ThumbsService {

    @Autowired
    private ThumbsMapper thumbsMapper;


    @Override
    public Thumbs selectThumbs(int userId, int scenicSpotId) {
        return thumbsMapper.selectThumbs(userId , scenicSpotId);
    }

    @Override
    public void insertThumbs(int userId, int scenicSpotId) throws SpringbootException {
        Thumbs thumbs = new Thumbs();
        thumbs.setState(1);
        thumbs.setCreateTime(new Date());
        thumbs.setScenicSpotId(scenicSpotId);
        thumbs.setUserId(userId);
        int count  = thumbsMapper.insertSelective(thumbs);
        if (count == 0){
            throw new SpringbootException(SpringbootExceptionEnum.INSERT_FAILED);
        }
    }

    @Override
    public void updateThumbs(Thumbs thumbs) throws SpringbootException {
        int count = thumbsMapper.updateByPrimaryKey(thumbs);
        if (count == 0){
            throw new SpringbootException(SpringbootExceptionEnum.UPDATE_ERROR);
        }
    }
}
