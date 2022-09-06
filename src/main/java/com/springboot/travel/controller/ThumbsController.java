package com.springboot.travel.controller;

import com.springboot.travel.common.ApiRestResponse;
import com.springboot.travel.exception.SpringbootException;
import com.springboot.travel.pojo.Thumbs;
import com.springboot.travel.services.ScenicSpotService;
import com.springboot.travel.services.ThumbsService;
import com.springboot.travel.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThumbsController {

    @Autowired
    private UserService userService;

    @Autowired
    private ThumbsService thumbsService;

    @Autowired
    private ScenicSpotService scenicSpotService;

    @PostMapping("doLike.do/{userId}/{scenicSpotId}")
    public ApiRestResponse doLike(@PathVariable("userId") Integer userId
            , @PathVariable("scenicSpotId") Integer scenicSpotId){
        Thumbs thumbs = thumbsService.selectThumbs(userId , scenicSpotId);
        try {
            if (thumbs == null){
                thumbsService.insertThumbs(userId , scenicSpotId);
                scenicSpotService.increaseHot(scenicSpotId);
            }else {
                if (thumbs.getState() == 1){
                    thumbs.setState(0);
                    thumbsService.updateThumbs(thumbs);
                    scenicSpotService.reduceHot(scenicSpotId);
                }else {
                    thumbs.setState(1);
                    thumbsService.updateThumbs(thumbs);
                    scenicSpotService.increaseHot(scenicSpotId);
                }
            }
        }catch (SpringbootException ex){
            return ApiRestResponse.error(ex.getCode() , ex.getMsg());
        }

        return ApiRestResponse.success();
    }
}
