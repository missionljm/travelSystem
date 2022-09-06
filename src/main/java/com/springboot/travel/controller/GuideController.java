package com.springboot.travel.controller;

import com.springboot.travel.common.ApiRestResponse;
import com.springboot.travel.exception.SpringbootException;
import com.springboot.travel.exception.SpringbootExceptionEnum;
import com.springboot.travel.pojo.Guide;
import com.springboot.travel.pojo.ScenicSpot;
import com.springboot.travel.pojo.User;
import com.springboot.travel.services.GuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class GuideController {

    @Autowired
    private GuideService guideService;

    /**
     * 查询导游列表（管理员）
     * @param page
     * @param size
     * @return
     */
    @GetMapping("guideList.do/{page}/{size}")
    @ResponseBody
    public Map guideList(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        List<Guide> guideList = guideService.selectAllGuide();
        List<Guide> guidePageList = guideService.selectPageGuide(page , size);
        int pageSize = guideList.size();
        Map guideMap = new HashMap();
        guideMap.put("code", 1000);
        guideMap.put("massage", "请求成功");
        guideMap.put("title", pageSize);
        guideMap.put("guidePageList", guidePageList);
        return guideMap;
    }

    /**
     * 编辑导游信息
     * @param guide
     * @return
     */
    @PutMapping("/editGuide.do")
    @ResponseBody
    public ApiRestResponse editGuide(@RequestBody Guide guide) {
        if (guide == null) {
            return ApiRestResponse.error(SpringbootExceptionEnum.NEED_NO_EXISTED);
        }
        try {
            guideService.editGuide(guide);
        } catch (SpringbootException ex) {
            return ApiRestResponse.error(ex.getCode(), ex.getMsg());
        }
        return ApiRestResponse.success();
    }
    
    @PutMapping("insertGuide.do")
    @ResponseBody
    public ApiRestResponse insertGuide(@RequestBody Guide guide){
        try {
            guideService.insertGuide(guide);
        }catch (SpringbootException ex){
            return ApiRestResponse.error(ex.getCode() , ex.getMsg());
        }
        return ApiRestResponse.success();
    }
    
    @PostMapping("deleteGuideById.do/{guideId}")
    @ResponseBody
    public ApiRestResponse deleteGuideById(@PathVariable("guideId") Integer guideId){
        if (guideId == null) {
            return ApiRestResponse.error(SpringbootExceptionEnum.NEED_USER_NAME);
        }
        try {
            guideService.deleteGuideById(guideId);
        } catch (SpringbootException ex) {
            return ApiRestResponse.error(ex.getCode(), ex.getMsg());
        }
        return ApiRestResponse.success();
    }
    
    @PostMapping("deleteAllGuide.do")
    @ResponseBody
    public ApiRestResponse deleteAllGuide(@RequestBody Guide[] guides){
        try {
            int[] guideId = new int[guides.length];
            for (int i = 0 ; i < guides.length ; i ++){
                guideId[i] = guides[i].getGuideId();
                guideService.deleteGuideById(guideId[i]);
            }
        }catch (SpringbootException ex){
            return ApiRestResponse.error(ex.getCode() , ex.getMsg());
        }
        return ApiRestResponse.success();
    }
    
    @PostMapping("selectGuideByName.do/{guideName}")
    @ResponseBody
    public Map selectGuideByName(@PathVariable("guideName") String guideName){
        Map guideMap = new HashMap();
        List<Guide> guideList;
        if (guideName.equals("null")) {
            guideList = guideService.selectAllGuide();
        } else {
            guideList = guideService.selectGuideByName(guideName);
        }
        guideMap.put("code", 10001);
        guideMap.put("msg", "查询成功");
        guideMap.put("scenicSpotList", guideList);
        return guideMap;
    }

    @GetMapping("selectGuideById.do/{guideId}")
    @ResponseBody
    public Map selectGuideById(@PathVariable("guideId") Integer guideId){
        Guide guide = guideService.selectById(guideId);
        Map guideMap = new HashMap();
        guideMap.put("guide", guide);
        guideMap.put("code", 1000);
        guideMap.put("msg", "查询成功");
        return guideMap;
    }

    @GetMapping("selectAllGuide.do")
    @ResponseBody
    public Map selectAllGuide(){
        List<Guide> guideList = guideService.selectAllGuide();
        Map guideMap = new HashMap();
        guideMap.put("guideList", guideList);
        guideMap.put("status", 10001);
        guideMap.put("msg", "查询成功");
        return guideMap;
    }


}
