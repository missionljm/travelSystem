package com.springboot.travel.controller;

import com.springboot.travel.common.ApiRestResponse;
import com.springboot.travel.exception.SpringbootException;
import com.springboot.travel.exception.SpringbootExceptionEnum;
import com.springboot.travel.pojo.ScenicSpot;
import com.springboot.travel.pojo.User;
import com.springboot.travel.services.ScenicSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ScenicSpotController {

    @Autowired
    private ScenicSpotService scenicSpotService;

    /**
     * 查询景点分页（管理员）
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/spotList.do/{page}/{size}")
    @ResponseBody
    public Map spotList(@PathVariable("page") int page, @PathVariable("size") int size) {
        List<ScenicSpot> spotList = scenicSpotService.selectAllSpot();
        List<ScenicSpot> spotPageList = scenicSpotService.selectPageSpot(page, size);
        int pageSize = spotList.size();
        Map spotMap = new HashMap();
        spotMap.put("code", 1000);
        spotMap.put("msg", "请求成功");
        spotMap.put("title", pageSize);
        spotMap.put("spotPageList", spotPageList);
        return spotMap;
    }

    /**
     * 删除景点信息（管理员）
     *
     * @param spotId
     * @return
     */
    @PostMapping("/deleteSpot.do/{spotId}")
    @ResponseBody
    public ApiRestResponse deleteUserById(@PathVariable("spotId") Integer spotId) {
        if (spotId == null) {
            return ApiRestResponse.error(SpringbootExceptionEnum.NEED_USER_NAME);
        }
        try {
            scenicSpotService.deleteSpot(spotId);
        } catch (SpringbootException ex) {
            return ApiRestResponse.error(ex.getCode(), ex.getMsg());
        }
        return ApiRestResponse.success();
    }

    @PostMapping("selectSpot.do/{address}")
    @ResponseBody
    public Map selectSpot(@PathVariable("address") String address) {
        Map spotMap = new HashMap();
        List<ScenicSpot> scenicSpotList;
        if (address.equals("null")) {
            scenicSpotList = scenicSpotService.selectAllSpot();
        } else {
            scenicSpotList = scenicSpotService.selectBySpotAddress(address);
        }
        spotMap.put("code", 10001);
        spotMap.put("msg", "查询成功");
        spotMap.put("scenicSpotList", scenicSpotList);
        return spotMap;
    }

    /**
     * 通过id查询scenicSpot
     *
     * @param spotId
     * @return
     */
    @GetMapping("selectSpotById.do/{scenicSpotId}")
    @ResponseBody
    public Map selectSpotById(@PathVariable("scenicSpotId") Integer spotId) {
        ScenicSpot scenicSpot = scenicSpotService.selectById(spotId);
        Map spotMap = new HashMap();
        spotMap.put("scenicSpot", scenicSpot);
        spotMap.put("code", 1000);
        spotMap.put("msg", "查询成功");
        return spotMap;
    }

    @PostMapping("editSpot.do")
    @ResponseBody
    public ApiRestResponse editSpot(@RequestParam("file") MultipartFile file ,ScenicSpot scenicSpot) throws IOException {
        try {
            if (file == null) {
                scenicSpotService.editSpot(scenicSpot);
            } else {
                String fileName = file.getOriginalFilename();
                String url = ResourceUtils.getURL("classpath:static").getPath()+"/upload/spot/";
                file.transferTo(new File(url + fileName));
                String sqlUrl = "http://localhost:8081/upload/spot/";
                scenicSpot.setScenicSpotImage(sqlUrl+fileName);
                scenicSpotService.editSpot(scenicSpot);
            }
        } catch (SpringbootException ex) {
            return ApiRestResponse.error(ex.getCode(), ex.getMsg());
        }
        return ApiRestResponse.success();
    }

    @PutMapping("insertSpot.do")
    @ResponseBody
    public ApiRestResponse insertSpot(@RequestBody ScenicSpot scenicSpot){
        try {
            scenicSpotService.insertSpot(scenicSpot);
        }catch (SpringbootException ex){
            return ApiRestResponse.error(ex.getCode() , ex.getMsg());
        }
        return ApiRestResponse.success();
    }

    @PostMapping("deleteAllSpot.do")
    @ResponseBody
    public ApiRestResponse deleteAllSpot(@RequestBody ScenicSpot[] scenicSpots){
        try {
            int[] spotId = new int[scenicSpots.length];
            for (int i = 0 ; i < scenicSpots.length ; i ++){
                spotId[i] = scenicSpots[i].getScenicSpotId();
                scenicSpotService.deleteSpot(spotId[i]);
            }
        }catch (SpringbootException ex){
            return ApiRestResponse.error(ex.getCode() , ex.getMsg());
        }
        return ApiRestResponse.success();
    }

    /**
     *获取轮播图照片
     * @return
     */
    @GetMapping("getCard.do")
    @ResponseBody
    public Map selectCard(){
        Map spotMap = new HashMap();
        List<ScenicSpot> scenicSpotList = scenicSpotService.selectBySetCard();
        int size = scenicSpotList.size();
        spotMap.put("code", 10001);
        spotMap.put("msg", "查询成功");
        spotMap.put("size" , size);
        spotMap.put("scenicSpotList", scenicSpotList);
        return spotMap;
    }

    /**
     * 查询首页展示照片
     * @param session
     * @return
     */
    @GetMapping("selectShowSpot.do")
    @ResponseBody
    public Map selectSpot(HttpSession session){
        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();
        List<ScenicSpot> spotShowList = scenicSpotService.selectIndexSpot(userId , 1 , 6);
        Map spotMap = new HashMap();
        spotMap.put("code", 10001);
        spotMap.put("msg", "查询成功");
        spotMap.put("spotShowList", spotShowList);
        return spotMap;
    }

    /**
     * 查询pCenter
     * @param session
     * @return
     */
    @GetMapping("selectPCenterSpot.do")
    @ResponseBody
    public Map selectPCenterSpot(HttpSession session){
        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();
        List<ScenicSpot> spotShowList = scenicSpotService.selectPCenterSpot(userId);
        Map spotMap = new HashMap();
        spotMap.put("code", 10001);
        spotMap.put("msg", "查询成功");
        spotMap.put("spotShowList", spotShowList);
        return spotMap;
    }



}
