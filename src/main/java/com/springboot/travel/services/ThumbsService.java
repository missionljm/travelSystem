package com.springboot.travel.services;

import com.springboot.travel.exception.SpringbootException;
import com.springboot.travel.pojo.Thumbs;

public interface ThumbsService {

    Thumbs selectThumbs(int userId , int scenicSpotId);

    void insertThumbs(int userId , int scenicSpotId) throws SpringbootException;

    void updateThumbs(Thumbs thumbs) throws SpringbootException;
}
