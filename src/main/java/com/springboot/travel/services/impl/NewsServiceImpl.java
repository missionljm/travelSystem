package com.springboot.travel.services.impl;

import com.springboot.travel.dao.NewsMapper;
import com.springboot.travel.exception.SpringbootException;
import com.springboot.travel.exception.SpringbootExceptionEnum;
import com.springboot.travel.pojo.News;
import com.springboot.travel.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("NewsService")
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsMapper newsMapper;

    @Override
    public List<News> selectAllNews() {
        return newsMapper.selectAllNews();
    }

    @Override
    public List<News> selectPageNews(int page, int size) {
        return newsMapper.selectPageNews((page-1)*size , size);
    }

    @Override
    public News selectById(int newsId) {
        return newsMapper.selectByPrimaryKey(newsId);
    }

    @Override
    public void deleteNewsById(int newsId) throws SpringbootException {
        int count = newsMapper.deleteByPrimaryKey(newsId);
        if (count == 0){
            throw new SpringbootException(SpringbootExceptionEnum.DELETE_ERROR);
        }
    }

    @Override
    public void editNews(News news) throws SpringbootException {
        int count  = newsMapper.updateByPrimaryKey(news);
        if (count == 0){
            throw new SpringbootException(SpringbootExceptionEnum.UPDATE_ERROR);
        }
    }

    @Override
    public void insertNews(News news) throws SpringbootException {
        news.setCreateTime(new Date());
        int count = newsMapper.insertSelective(news);
        if (count == 0){
            throw new SpringbootException(SpringbootExceptionEnum.INSERT_FAILED);
        }
    }

    @Override
    public List<News> selectNewsByTitle(String title) {
        return newsMapper.selectNewByTitle(title);
    }
}
