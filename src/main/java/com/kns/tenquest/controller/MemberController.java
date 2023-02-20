package com.kns.tenquest.controller;

import com.kns.tenquest.dto.MemberResponseMapping;
import com.kns.tenquest.response.ResponseStatus;
import com.kns.tenquest.dto.MemberDTO;
import com.kns.tenquest.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.kns.tenquest.service.MemberService;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Controller
public class MemberController {
    @Autowired
    MemberService memberService;
    //@ResponseBody
    @GetMapping("/view/members")
    public String memberView(Model model){
        // Temporarily implemented. Just for test.
        List<Member> memberList = memberService.getAllMembers();
        model.addAttribute("memberList", memberList);
        return "member_view";
    }

    @ResponseBody
    @GetMapping("/get/members")
    public List<Member> apiGetAllMembers(){
        return memberService.getAllMembers();
    }
    @ResponseBody
    @GetMapping("/get/member/memberId")
    public MemberResponseMapping apiGetMemberByMemberId(@RequestParam("value") String memberId){
        return memberService.getMemberByMemberId(memberId);
    }
    @ResponseBody
    @GetMapping("/get/member/userId")
    public MemberResponseMapping apiGetMemberByUserId(@RequestParam("value") String userId){
        return memberService.getMemberByUserId(userId);
    }

    @ResponseBody
    @GetMapping("/get/member")
    public MemberResponseMapping apiGetMemberByUserNameAndUserId(@RequestParam("userName") String userName, @RequestParam("userEmail") String userEmail){
        return memberService.getMemberByUserNameAndEmail(userName,userEmail);
    }

    @ResponseBody
    @GetMapping("/get/memberId/userId")
    public String apiGetMemberIdByUserId(@RequestParam("value") String userId){
        return memberService.getMemberIdByUserId(userId);
    }


    @ResponseBody
    @GetMapping("/get/memberId")
    public String apiGetMemberIdByUserName(@RequestParam("userName") String userName, @RequestParam("userEmail") String userEmail){
        return memberService.getMemberIdByUserNameAndUserEmail(userName, userEmail);
    }

    @ResponseBody
    @PostMapping("/member/register")
    public int apiRegisterMember(@RequestBody MemberDTO dto) throws NoSuchAlgorithmException {
       ResponseStatus responseStatus = memberService.insertMember(dto);
        return responseStatus.getCode();
    }
}
