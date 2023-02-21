package com.kns.tenquest.controller;

import com.kns.tenquest.dto.MemberDto;
import com.kns.tenquest.dto.MemberResponseDto;
import com.kns.tenquest.entity.Member;
import com.kns.tenquest.response.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
    public ResponseEntity<MemberResponseDto> apiGetMemberByMemberId(@RequestParam("value") String memberId){
        MemberDto nullableMemberDto = memberService.getMemberByMemberId(memberId);
        ResponseStatus responseStatus = ResponseStatus.OK;

        if (nullableMemberDto.memberId == null)
            responseStatus = ResponseStatus.NOT_FOUND;

        return new ResponseEntity<MemberResponseDto>(
                new MemberResponseDto(
                        responseStatus.getStatus(),
                        responseStatus.getCode(),
                        nullableMemberDto),
                new HttpHeaders(),
                responseStatus.getCode());

    }
    @ResponseBody
    @GetMapping("/get/member/userId")
    public MemberDto apiGetMemberByUserId(@RequestParam("value") String userId){
        return memberService.getMemberByUserId(userId);
    }

    @ResponseBody
    @GetMapping("/get/member")
    public MemberDto apiGetMemberByUserNameAndUserId(@RequestParam("userName") String userName, @RequestParam("userEmail") String userEmail){
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
    public int apiRegisterMember(@RequestBody MemberDto dto) throws NoSuchAlgorithmException {
        int response = memberService.insertMember(dto);
        return response;
    }
}
