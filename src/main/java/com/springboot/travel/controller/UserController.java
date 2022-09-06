package com.springboot.travel.controller;

import com.springboot.travel.common.ApiRestResponse;
import com.springboot.travel.exception.SpringbootException;
import com.springboot.travel.exception.SpringbootExceptionEnum;
import com.springboot.travel.pojo.Admin;
import com.springboot.travel.pojo.User;
import com.springboot.travel.services.UserService;
import com.springboot.travel.utils.MD5Utils;
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
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户列表（管理员）
     * @param page
     * @param size
     * @return
     */
    @GetMapping("userList.do/{page}/{size}")
    @ResponseBody
    public Map getUserName(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        List<User> userList = userService.selectUserList();
        List<User> userPageList = userService.selectUserPageList(page, size);
        int pageSize = userList.size();
        Map userMap = new HashMap();
        userMap.put("code", 1000);
        userMap.put("massage", "请求成功");
        userMap.put("title", pageSize);
        userMap.put("userPageList", userPageList);
        return userMap;
    }

    /**
     * 编辑用户（管理员）
     * @param user
     * @return
     */
    @PutMapping("/editUser.do")
    @ResponseBody
    public ApiRestResponse editUser(@RequestBody User user , HttpSession session) {
        if (user == null) {
            return ApiRestResponse.error(SpringbootExceptionEnum.NEED_NO_EXISTED);
        }
        try {
            User user1 = (User) session.getAttribute("user");
            if (user1 != null){
                user1.setHeadPortrait(user.getHeadPortrait());
                user1.setAge(user.getAge());
                user1.setEmail(user.getEmail());
                user1.setUsername(user.getUsername());
                user1.setTelephone(user.getTelephone());
                session.removeAttribute("user");
                session.setAttribute("user" , user1);
            }
            userService.editUser(user);
        } catch (SpringbootException ex) {
            return ApiRestResponse.error(ex.getCode(), ex.getMsg());
        }
        return ApiRestResponse.success();
    }

    /**
     * 通过id查询用户（管理员）
     * @param userId
     * @return
     */
    @GetMapping("/selectUserById.do/{userId}")
    @ResponseBody
    public Map selectUserById(@PathVariable("userId") Integer userId) {
        User user = userService.selectUserById(userId);
        Map userMap = new HashMap();
        userMap.put("user", user);
        userMap.put("code", 1000);
        userMap.put("msg", "查询成功");
        return userMap;
    }

    /**
     * 删除用户（管理员）
     * @param userId
     * @return
     */
    @PostMapping("/deleteUser.do/{userId}")
    @ResponseBody
    public ApiRestResponse deleteUserById(@PathVariable("userId") Integer userId) {
        if (userId == null) {
            return ApiRestResponse.error(SpringbootExceptionEnum.NEED_USER_NAME);
        }
        try {
            userService.deleteUser(userId);
        } catch (SpringbootException ex) {
            return ApiRestResponse.error(ex.getCode(), ex.getMsg());
        }
        return ApiRestResponse.success();
    }

    /**
     * 查询用户信息（管理员）
     * @param username
     * @return
     */
    @PostMapping("/selectUser.do/{input}")
    @ResponseBody
    public Map selectUser(@PathVariable("input") String username) {
        Map userMap = new HashMap();
        List<User> userList;
        if (username.equals("null")) {
            userList = userService.selectUserList();
        } else {
            userList = userService.selectUserByUsername(username);
        }
        userMap.put("code", 10001);
        userMap.put("msg", "查询成功");
        userMap.put("userList", userList);
        return userMap;
    }

    /**
     * 批量删除用户（管理员）
     * @param user
     * @return
     */
    @PostMapping("/deleteAll.do")
    @ResponseBody
    public ApiRestResponse deleteAll(@RequestBody User[] user) {
        int[] userId = new int[user.length];
        for (int i = 0; i < user.length; i++) {
            userId[i] = user[i].getUserId();
        }
        if (userId == null) {
            return ApiRestResponse.error(SpringbootExceptionEnum.DELETE_ERROR);
        }
        userService.deleteAll(userId);
        return ApiRestResponse.success();
    }

    /**
     * 登录功能
     * @param telephone
     * @param password
     * @param session
     * @return
     */
    @PostMapping("userLogin.do/{telephone}/{password}")
    @ResponseBody
    public ApiRestResponse adminLogin(@PathVariable("telephone") String telephone ,
                                      @PathVariable("password") String password ,
                                      HttpSession session){
        if (StringUtils.isEmpty(telephone)){
            return ApiRestResponse.error(SpringbootExceptionEnum.NEED_USER_ROLE);
        }
        if (StringUtils.isEmpty(password)){
            return ApiRestResponse.error(SpringbootExceptionEnum.NEED_USER_PASSWORD);
        }
        try {
            userService.userLogin(telephone , password);
            User user = userService.selectByTelephone(telephone);
            session.setAttribute("user",user);
            System.out.println(user);
        }catch (SpringbootException ex){
            return ApiRestResponse.error(ex.getCode() , ex.getMsg());
        }
        return ApiRestResponse.success();
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @PostMapping("userRegister.do")
    @ResponseBody
    public ApiRestResponse insertUser(@RequestBody User user){
        try {
            userService.userRegister(user);
        }catch (SpringbootException ex){
            return ApiRestResponse.error(ex.getCode() , ex.getMsg());
        }
        return ApiRestResponse.success();
    }

    /**
     * 验证用户是否登录
     * @param session
     * @return
     */
    @GetMapping("checkUser.do")
    @ResponseBody
    public Map checkUser(HttpSession session){
        User user = (User) session.getAttribute("user");
        Map userMap = new HashMap();
        System.out.println(user);
        if (user == null){
            userMap.put("status" , 10019);
            userMap.put("msg" , "用户未登录，请重新登录");
            return userMap;
        }
        userMap.put("status" , 10001);
        userMap.put("msg" , "success");
        userMap.put("user" , user);
        return userMap;
    }

    @PutMapping("updatePassword.do/{pass}")
    @ResponseBody
    public ApiRestResponse updatePassword(@PathVariable("pass") String password , HttpSession session){
        User user = (User) session.getAttribute("user");

        String md5 = MD5Utils.md5Digest(password , user.getSalt());
        user.setPassword(md5);
        try {
            userService.editUser(user);
        }catch (SpringbootException ex){
            return ApiRestResponse.error(ex.getCode() , ex.getMsg());
        }
        return ApiRestResponse.success();

    }

    /**
     * 上传头像功能
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("updatePortrait.do")
    @ResponseBody
    public String updatePortrait(MultipartFile file) throws IOException {
        String url = ResourceUtils.getURL("classpath:static").getPath()+"/head/";
        String fileName = file.getOriginalFilename();
        try{
            file.transferTo(new File(url + fileName));
            String imgURL = "http://localhost:8081/head/" + fileName;
            return imgURL;
        }catch (IOException e){
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 用户预约导游功能
     * @param guideId
     * @param session
     * @return
     */
    @PostMapping("order.do/{guideId}")
    @ResponseBody
    public ApiRestResponse orderGuide(@PathVariable("guideId") Integer guideId , HttpSession session){
        User user = (User) session.getAttribute("user");
        user.setGuideId(guideId);
        session.removeAttribute("user");
        session.setAttribute("user" , user);
        try {
            userService.updateUser(user);
        }catch (SpringbootException ex){
            return ApiRestResponse.error(ex.getCode() , ex.getMsg());
        }
        return ApiRestResponse.success();
    }
}
