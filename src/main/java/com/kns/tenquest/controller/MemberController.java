package com.kns.tenquest.controller;

import com.kns.tenquest.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kns.tenquest.service.MemberService;

import java.util.List;

@RestController
public class MemberController {
    @Autowired
    MemberService memberService;
    //@ResponseBody
    @GetMapping("/data/members")
    public List<Member> memberView(){
        // Temporarily implemented. Just for test.
        return memberService.getAllMembers();
    }
}
