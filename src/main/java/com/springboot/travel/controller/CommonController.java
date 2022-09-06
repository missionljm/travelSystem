package com.springboot.travel.controller;

import com.springboot.travel.common.ApiRestResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class CommonController {

    @PutMapping("cancellation.do")
    @ResponseBody
    public ApiRestResponse cancellation(HttpSession session){
        session.removeAttribute("user");
        return ApiRestResponse.success();
    }
}
