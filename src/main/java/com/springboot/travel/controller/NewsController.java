package com.springboot.travel.controller;

import com.springboot.travel.common.ApiRestResponse;
import com.springboot.travel.exception.SpringbootException;
import com.springboot.travel.exception.SpringbootExceptionEnum;
import com.springboot.travel.pojo.Admin;
import com.springboot.travel.pojo.News;
import com.springboot.travel.pojo.ScenicSpot;
import com.springboot.travel.pojo.User;
import com.springboot.travel.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class NewsController {
    
    @Autowired
    private NewsService newsService;
    
    @GetMapping("newsPageList.do/{page}/{size}")
    @ResponseBody
    public Map newsPageList(@PathVariable("page") int page , @PathVariable("size") int size){
        List<News> newsList = newsService.selectAllNews();
        List<News> newsPageList = newsService.selectPageNews(page , size);
        int pageSize = newsList.size();
        Map newsMap = new HashMap();
        newsMap.put("code", 1000);
        newsMap.put("msg", "请求成功");
        newsMap.put("title", pageSize);
        newsMap.put("newsPageList", newsPageList);
        return newsMap;
    }

    @PostMapping("deleteNewsById.do/{newsId}")
    @ResponseBody
    public ApiRestResponse deleteNewsById(@PathVariable("newsId") int newsId){
        try {
            newsService.deleteNewsById(newsId);
        }catch (SpringbootException ex){
            return ApiRestResponse.error(ex.getCode() , ex.getMsg());
        }
        return ApiRestResponse.success();
    }

    @PostMapping("deleteAllNews.do")
    @ResponseBody
    public ApiRestResponse deleteAllNews(@RequestBody News[] news){
        try {
            int newsId[] = new int[news.length];
            for (int i = 0 ; i < news.length ; i ++){
                newsId[i] = news[i].getNewId();
                newsService.deleteNewsById(newsId[i]);
            }
        }catch (SpringbootException ex){
            return ApiRestResponse.error(ex.getCode() , ex.getMsg());
        }
        return ApiRestResponse.success();
    }

    @PutMapping("insertNews.do")
    @ResponseBody
    public ApiRestResponse insertNews(@RequestBody News news , HttpSession session){
        Admin admin =(Admin) session.getAttribute("admin");
        int adminId = admin.getAdmin();
        news.setAdminId(adminId);
        try {
            newsService.insertNews(news);
        }catch (SpringbootException ex){
            return ApiRestResponse.error(ex.getCode() , ex.getMsg());
        }
        return ApiRestResponse.success();
    }

    /**
     * 查询时政新闻列表
     * @param title
     * @return
     */
    @PostMapping("selectNewsByTitle.do/{title}")
    @ResponseBody
    public Map selectNewsByTitle(@PathVariable("title") String title){
        Map newsMap = new HashMap();
        List<News> newsList ;
        if (title.equals("null")) {
            newsList = newsService.selectAllNews();
        } else {
            newsList = newsService.selectNewsByTitle(title);
        }
        newsMap.put("code", 10001);
        newsMap.put("msg", "查询成功");
        newsMap.put("newsList", newsList);
        return newsMap;
    }

    /**
     * 通过id查询新闻列表
     * @param newsId
     * @return
     */
    @GetMapping("selectNewsById.do/{newsId}")
    @ResponseBody
    public Map selectNewsById(@PathVariable("newsId") int newsId){
        News news = newsService.selectById(newsId);
        Map newsMap = new HashMap();
        newsMap.put("news", news);
        newsMap.put("code", 1000);
        newsMap.put("msg", "查询成功");
        return newsMap;
    }

    /**
     * 修改新闻内容（管理员）
     * @param news
     * @return
     */
    @PutMapping("editNews.do")
    @ResponseBody
    public ApiRestResponse editNews(@RequestBody News news){
        if (news == null){
            return ApiRestResponse.error(SpringbootExceptionEnum.UPDATE_ERROR);
        }
        try {
            newsService.editNews(news);
        } catch (SpringbootException ex) {
            return ApiRestResponse.error(ex.getCode(), ex.getMsg());
        }
        return ApiRestResponse.success();
    }

    /**
     * 查询全部新闻列表
     * @return
     */
    @GetMapping("selectAll.do")
    @ResponseBody
    public Map selectAll(){
        List<News> newsList = newsService.selectAllNews();
        Map newsMap = new HashMap();
        newsMap.put("newsList", newsList);
        newsMap.put("status", 10001);
        newsMap.put("msg", "查询成功");
        return newsMap;
    }
}
