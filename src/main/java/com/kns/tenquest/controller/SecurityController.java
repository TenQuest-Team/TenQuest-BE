package com.kns.tenquest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sec")
public class SecurityController {

    @ResponseBody
    @GetMapping("/memberInfo")
    public String getMemberInfo(){
        return "Member Info";
    }

    @ResponseBody
    @GetMapping("/jwtAuthenticationTest")
    public String jwtAuthenticationTest(){
        return "Authentication Succeed!";
    }

}
