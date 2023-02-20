package com.kns.tenquest.controller;

import com.kns.tenquest.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TemplateController {

    @Autowired
    private MemberService memberService;
}
