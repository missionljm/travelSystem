package com.springboot.travel.services;

import com.springboot.travel.exception.SpringbootException;
import com.springboot.travel.pojo.News;

import java.util.List;

public interface NewsService {

    List<News> selectAllNews();

    List<News> selectPageNews(int page , int size);

    News selectById(int newsId);

    void deleteNewsById(int newsId)throws SpringbootException;

    void editNews(News news) throws SpringbootException;

    void insertNews(News news) throws SpringbootException;

    List<News> selectNewsByTitle(String title);

}
