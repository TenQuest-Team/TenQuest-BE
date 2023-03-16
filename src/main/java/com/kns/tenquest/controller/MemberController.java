package com.kns.tenquest.controller;

import com.kns.tenquest.dto.MemberDto;
import com.kns.tenquest.dto.ResponseDto;
import com.kns.tenquest.response.Response;
import com.kns.tenquest.response.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/view/members")
    public String memberView(Model model){
        // Temporarily implemented. Just for test.
        List<MemberDto> memberList = memberService.getAllMembers();
        model.addAttribute("memberList", memberList);
        return "member_view";
    }

    @ResponseBody
    @GetMapping("/get/members")
    public Response<MemberDto> apiGetAllMembers(){
        ResponseStatus responseStatus = ResponseStatus.OK;
        List<MemberDto> memberDtoList = memberService.getAllMembers();

        return new ResponseDto<MemberDto>(responseStatus,memberDtoList).toResponseJson();

    }
    @ResponseBody
    @GetMapping("/get/member/memberId")
    public Response<MemberDto> apiGetMemberByMemberId(@RequestParam("value") String memberId){
        MemberDto nullableMemberDto = memberService.getMemberByMemberId(memberId);
        ResponseStatus responseStatus = ResponseStatus.OK;

        if (nullableMemberDto.memberId == null)
            responseStatus = ResponseStatus.NOT_FOUND;

        return new ResponseDto<MemberDto>(responseStatus, nullableMemberDto).toResponseJson();
    }
    @ResponseBody
    @GetMapping("/get/member/userId")
    public Response<MemberDto> apiGetMemberByUserId(@RequestParam("value") String userId){
        MemberDto nullableMemberDto = memberService.getMemberByUserId(userId);
        ResponseStatus responseStatus = ResponseStatus.OK;

        if (nullableMemberDto.memberId == null) responseStatus = ResponseStatus.NOT_FOUND;

        return new ResponseDto<MemberDto>(responseStatus, nullableMemberDto).toResponseJson();

    }

    @ResponseBody
    @GetMapping("/get/member")
    public Response<MemberDto> apiGetMemberByUserNameAndUserId(@RequestParam("userName") String userName, @RequestParam("userEmail") String userEmail){
        MemberDto nullableMemberDto = memberService.getMemberByUserNameAndEmail(userName,userEmail);
        ResponseStatus responseStatus = ResponseStatus.OK;
        if (nullableMemberDto.memberId == null) responseStatus = ResponseStatus.NOT_FOUND;
        return new ResponseDto<MemberDto>(responseStatus, nullableMemberDto).toResponseJson();

    }

    @ResponseBody
    @GetMapping("/get/memberId/userId")
    public Response<String> apiGetMemberIdByUserId(@RequestParam("value") String userId){
        String nullableString = memberService.getMemberIdByUserId(userId);
        ResponseStatus responseStatus = ResponseStatus.OK;

        if (nullableString.equals(ResponseStatus.NOT_FOUND.getStatus())){
            responseStatus = ResponseStatus.NOT_FOUND;
            nullableString = null;}

        return new ResponseDto<String>(responseStatus, nullableString).toResponseJson();

    }

    @ResponseBody
    @GetMapping("/get/memberId")
    public Response<String> apiGetMemberIdByUserName(@RequestParam("userName") String userName, @RequestParam("userEmail") String userEmail){
        String nullableString = memberService.getMemberIdByUserNameAndUserEmail(userName, userEmail);
        ResponseStatus responseStatus = ResponseStatus.OK;

        if (nullableString.equals(ResponseStatus.NOT_FOUND.getStatus())){
            responseStatus = ResponseStatus.NOT_FOUND;
            nullableString = null;}

        return new ResponseDto<String>(responseStatus, nullableString).toResponseJson();

    }

    @ResponseBody
    @PostMapping("/member/register")
    public Response<Integer> apiRegisterMember(@RequestBody MemberDto dto) throws NoSuchAlgorithmException {
        int insertResult = memberService.insertMember(dto);

        ResponseStatus responseStatus = ResponseStatus.CREATE_DONE;

        if (insertResult == ResponseStatus.CREATE_FAIL.getCode()){
            responseStatus = ResponseStatus.CREATE_FAIL;
            }

        return new ResponseDto<Integer>(responseStatus, insertResult).toResponseJson();

    }

}
