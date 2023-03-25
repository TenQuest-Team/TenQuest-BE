package com.kns.tenquest.controller;


import com.kns.tenquest.ENV;
import com.kns.tenquest.dto.MemberDto;
import com.kns.tenquest.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping(ENV.API_PREFIX + "/admin")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AdminController {
    @Autowired
    MemberService memberService;
    @ResponseBody
    @PostMapping("/members")
    public void createAdminMember(@RequestBody MemberDto memberDto){
        memberService.insertAdmin(memberDto);
    }
}
