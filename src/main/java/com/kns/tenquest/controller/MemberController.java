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
@CrossOrigin(origins = "*", allowedHeaders = "*")
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
    @GetMapping("/members")
    public ResponseJson<MemberDto> apiGetAllMembers(){
        ResponseStatus responseStatus = ResponseStatus.OK;
        List<MemberDto> memberDtoList = memberService.getAllMembers();

        return new ResponseDto<MemberDto>(responseStatus,memberDtoList).toResponse();

    }
    @ResponseBody
    @GetMapping("/members/memberId")
    public ResponseJson<MemberDto> apiGetMemberByMemberId(@RequestParam("value") String memberId){
        MemberDto nullableMemberDto = memberService.getMemberByMemberId(memberId);
        ResponseStatus responseStatus = ResponseStatus.OK;

        if (nullableMemberDto.memberId == null)
            responseStatus = ResponseStatus.NOT_FOUND;

        return new ResponseDto<MemberDto>(responseStatus, nullableMemberDto).toResponse();
    }
    @ResponseBody
    @GetMapping("/members/userId")
    public ResponseJson<MemberDto> apiGetMemberByUserId(@RequestParam("value") String userId){
        MemberDto nullableMemberDto = memberService.getMemberByUserId(userId);
        ResponseStatus responseStatus = ResponseStatus.OK;

        if (nullableMemberDto.memberId == null) responseStatus = ResponseStatus.NOT_FOUND;

        return new ResponseDto<MemberDto>(responseStatus, nullableMemberDto).toResponse();

    }

    @ResponseBody
    @GetMapping("/members/nameAndEmail")
    public ResponseJson<MemberDto> apiGetMemberByUserNameAndUserId(@RequestParam("name") String userName, @RequestParam("email") String userEmail){
        MemberDto nullableMemberDto = memberService.getMemberByUserNameAndEmail(userName,userEmail);
        ResponseStatus responseStatus = ResponseStatus.OK;
        if (nullableMemberDto.memberId == null) responseStatus = ResponseStatus.NOT_FOUND;
        return new ResponseDto<MemberDto>(responseStatus, nullableMemberDto).toResponse();

    }

    @ResponseBody
    @GetMapping("/memberId/userId")
    public ResponseJson<String> apiGetMemberIdByUserId(@RequestParam("value") String userId){
        String nullableString = memberService.getMemberIdByUserId(userId);
        ResponseStatus responseStatus = ResponseStatus.OK;

        if (nullableString.equals(ResponseStatus.NOT_FOUND.getStatus())){
            responseStatus = ResponseStatus.NOT_FOUND;
            nullableString = null;}

        return new ResponseDto<String>(responseStatus, nullableString).toResponse();

    }

    @ResponseBody
    @GetMapping("/memberId/nameAndEmail")
    public ResponseJson<String> apiGetMemberIdByUserName(@RequestParam("name") String userName, @RequestParam("email") String userEmail){
        String nullableString = memberService.getMemberIdByUserNameAndUserEmail(userName, userEmail);
        ResponseStatus responseStatus = ResponseStatus.OK;

        if (nullableString.equals(ResponseStatus.NOT_FOUND.getStatus())){
            responseStatus = ResponseStatus.NOT_FOUND;
            nullableString = null;}

        return new ResponseDto<String>(responseStatus, nullableString).toResponse();

    }

    @ResponseBody
    @PostMapping("/members")
    public ResponseJson<Integer> apiRegisterMember(@RequestBody MemberDto dto) throws NoSuchAlgorithmException {
        int insertResult = memberService.insertMember(dto);

        ResponseStatus responseStatus = ResponseStatus.CREATE_DONE;

        if (insertResult == ResponseStatus.CREATE_FAIL.getCode()){
            responseStatus = ResponseStatus.CREATE_FAIL;
            }

        return new ResponseDto<Integer>(responseStatus, insertResult).toResponse();

    }

}
