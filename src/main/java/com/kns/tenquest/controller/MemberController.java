package com.kns.tenquest.controller;

import com.kns.tenquest.dto.MemberDto;
import com.kns.tenquest.dto.MemberResponseDto;
import com.kns.tenquest.dto.StringResponseDto;
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
                        responseStatus,
                        nullableMemberDto),
                new HttpHeaders(),
                responseStatus.getCode());

    }
    @ResponseBody
    @GetMapping("/get/member/userId")
    public ResponseEntity<MemberResponseDto> apiGetMemberByUserId(@RequestParam("value") String userId){
        MemberDto nullableMemberDto = memberService.getMemberByUserId(userId);
        ResponseStatus responseStatus = ResponseStatus.OK;
        if (nullableMemberDto.memberId == null)
            responseStatus = ResponseStatus.NOT_FOUND;

        return new ResponseEntity<MemberResponseDto>(
                new MemberResponseDto(
                        responseStatus,
                        nullableMemberDto),
                new HttpHeaders(),
                responseStatus.getCode());
    }

    @ResponseBody
    @GetMapping("/get/member")
    public ResponseEntity<MemberResponseDto> apiGetMemberByUserNameAndUserId(@RequestParam("userName") String userName, @RequestParam("userEmail") String userEmail){
        MemberDto nullableMemberDto = memberService.getMemberByUserNameAndEmail(userName,userEmail);
        ResponseStatus responseStatus = ResponseStatus.OK;
        if (nullableMemberDto.memberId == null)
            responseStatus = ResponseStatus.NOT_FOUND;

        return new ResponseEntity<MemberResponseDto>(
                new MemberResponseDto(
                        responseStatus,
                        nullableMemberDto),
                new HttpHeaders(),
                responseStatus.getCode());
    }

    @ResponseBody
    @GetMapping("/get/memberId/userId")
    public ResponseEntity<StringResponseDto> apiGetMemberIdByUserId(@RequestParam("value") String userId){
        String nullableString = memberService.getMemberIdByUserId(userId);
        ResponseStatus responseStatus = ResponseStatus.OK;

        if (nullableString.equals(ResponseStatus.NOT_FOUND.getStatus())){
            responseStatus = ResponseStatus.NOT_FOUND;
            nullableString = null;}

        return new ResponseEntity<StringResponseDto>(
                new StringResponseDto(
                        responseStatus,
                        nullableString),
                new HttpHeaders(),
                responseStatus.getCode());
    }


    @ResponseBody
    @GetMapping("/get/memberId")
    public ResponseEntity<StringResponseDto> apiGetMemberIdByUserName(@RequestParam("userName") String userName, @RequestParam("userEmail") String userEmail){
        String nullableString = memberService.getMemberIdByUserNameAndUserEmail(userName, userEmail);
        ResponseStatus responseStatus = ResponseStatus.OK;

        if (nullableString.equals(ResponseStatus.NOT_FOUND.getStatus())){
            responseStatus = ResponseStatus.NOT_FOUND;
            nullableString = null;}

        return new ResponseEntity<StringResponseDto>(
                new StringResponseDto(
                        responseStatus,
                        nullableString),
                new HttpHeaders(),
                responseStatus.getCode());
    }

    @ResponseBody
    @PostMapping("/member/register")
    public ResponseEntity<StringResponseDto> apiRegisterMember(@RequestBody MemberDto dto) throws NoSuchAlgorithmException {
        int response = memberService.insertMember(dto);
        String nullableString = Integer.toString(response);

        ResponseStatus responseStatus = ResponseStatus.CREATE_DONE;

        if (nullableString.equals(ResponseStatus.CREATE_FAIL.getStatus())){
            responseStatus = ResponseStatus.CREATE_FAIL;
            }

        return new ResponseEntity<StringResponseDto>(
                new StringResponseDto(
                        responseStatus,
                        null),
                new HttpHeaders(),
                responseStatus.getCode());
    }

}
