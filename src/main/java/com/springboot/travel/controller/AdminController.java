package com.springboot.travel.controller;

import com.springboot.travel.common.ApiRestResponse;
import com.springboot.travel.exception.SpringbootException;
import com.springboot.travel.exception.SpringbootExceptionEnum;
import com.springboot.travel.pojo.Admin;
import com.springboot.travel.pojo.User;
import com.springboot.travel.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 管理员登录
     * @param adminId
     * @param password
     * @param session
     * @return
     */
    @PostMapping("adminLogin.do/{username}/{password}")
    @ResponseBody
    public ApiRestResponse adminLogin(@PathVariable("username") int adminId ,
                                      @PathVariable("password") String password ,
                                      HttpSession session){
        if (StringUtils.isEmpty(adminId)){
            return ApiRestResponse.error(SpringbootExceptionEnum.NEED_USER_ROLE);
        }
        if (StringUtils.isEmpty(password)){
            return ApiRestResponse.error(SpringbootExceptionEnum.NEED_USER_PASSWORD);
        }
        try {
            adminService.adminLogin(adminId , password);
            Admin admin = adminService.selectAdminById(adminId);
            session.setAttribute("admin",admin);
        }catch (SpringbootException ex){
            return ApiRestResponse.error(ex.getCode() , ex.getMsg());
        }
        return ApiRestResponse.success();
    }

    @PostMapping("adminRegister.do")
    @ResponseBody
    public ApiRestResponse adminRegister(@RequestBody Admin admin){
        try {
            adminService.checkRegister(admin);
        }catch (SpringbootException ex){
            return ApiRestResponse.error(ex.getCode() , ex.getMsg());
        }
        return ApiRestResponse.success();
    }

    @GetMapping("checkAdmin.do")
    @ResponseBody
    public Map checkAdmin(HttpSession session){
        Admin admin = (Admin) session.getAttribute("admin");
        Map adminMap = new HashMap();
        if (admin == null){
            adminMap.put("status" , 10019);
            adminMap.put("msg" , "用户未登录，请重新登录");
            return adminMap;
        }
        adminMap.put("status" , 10001);
        adminMap.put("msg" , "success");
        adminMap.put("admin" , admin);
        return adminMap;
    }
}
