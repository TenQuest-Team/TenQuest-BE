package com.kns.tenquest.controller;

import com.kns.tenquest.DtoList;
import com.kns.tenquest.ENV;
import com.kns.tenquest.dto.MemberDto;
import com.kns.tenquest.dto.ResponseDto;
import com.kns.tenquest.response.Response;
import com.kns.tenquest.response.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.kns.tenquest.service.MemberService;

@RequestMapping(ENV.API_PREFIX)
@RestController

@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MemberController {
    @Autowired
    MemberService memberService;


    @GetMapping("/members")
    public Response<MemberDto> apiGetAllMembers(){
        ResponseStatus responseStatus = ResponseStatus.OK;
        DtoList<MemberDto> memberDtoList = memberService.getAllMembers();

        //return new ResponseDto<MemberDto>(responseStatus,memberDtoList).toResponse();
        return memberDtoList.toResponse(responseStatus);
    }
    @GetMapping("/members/memberId")
    public Response<MemberDto> apiGetMemberByMemberId(@RequestParam("value") String memberId){
        MemberDto nullableMemberDto = memberService.getMemberByMemberId(memberId);
        ResponseStatus responseStatus = ResponseStatus.OK;

        if (nullableMemberDto.memberId == null)
            responseStatus = ResponseStatus.NOT_FOUND;

        return nullableMemberDto.toResponse(responseStatus);
    }
    @GetMapping("/members/userId")
    public Response<MemberDto> apiGetMemberByUserId(@RequestParam("value") String userId){
        MemberDto nullableMemberDto = memberService.getMemberByUserId(userId);
        ResponseStatus responseStatus = ResponseStatus.OK;

        if (nullableMemberDto.memberId == null) responseStatus = ResponseStatus.NOT_FOUND;

        return nullableMemberDto.toResponse(responseStatus);

    }

    @GetMapping("/members/nameAndEmail")
    public Response<MemberDto> apiGetMemberByUserNameAndUserId(@RequestParam("name") String userName, @RequestParam("email") String userEmail){
        MemberDto nullableMemberDto = memberService.getMemberByUserNameAndEmail(userName,userEmail);
        ResponseStatus responseStatus = ResponseStatus.OK;
        if (nullableMemberDto.memberId == null) responseStatus = ResponseStatus.NOT_FOUND;
        return nullableMemberDto.toResponse(responseStatus);

    }

    @GetMapping("/memberId/userId")
    public Response<String> apiGetMemberIdByUserId(@RequestParam("value") String userId){
        String nullableString = memberService.getMemberIdByUserId(userId);
        ResponseStatus responseStatus = ResponseStatus.OK;

        if (nullableString.equals(ResponseStatus.NOT_FOUND.getStatus())){
            responseStatus = ResponseStatus.NOT_FOUND;
            nullableString = null;}

        return new ResponseDto<String>(responseStatus, nullableString).toResponse();

    }

    @GetMapping("/memberId/nameAndEmail")
    public Response<String> apiGetMemberIdByUserName(@RequestParam("name") String userName, @RequestParam("email") String userEmail){
        String nullableString = memberService.getMemberIdByUserNameAndUserEmail(userName, userEmail);
        ResponseStatus responseStatus = ResponseStatus.OK;

        if (nullableString.equals(ResponseStatus.NOT_FOUND.getStatus())){
            responseStatus = ResponseStatus.NOT_FOUND;
            nullableString = null;}

        return new ResponseDto<String>(responseStatus, nullableString).toResponse();

    }

    @PostMapping("/members")
    public Response<Integer> apiRegisterMember(@RequestBody MemberDto dto) {
        int insertResult = memberService.insertMember(dto);

        ResponseStatus responseStatus = ResponseStatus.CREATE_DONE;

        if (insertResult == ResponseStatus.CREATE_FAIL.getCode()){
            responseStatus = ResponseStatus.CREATE_FAIL;
            }

        return new ResponseDto<Integer>(responseStatus, insertResult).toResponse();

    }

    //Testing
    @ResponseBody
    @PostMapping("/join")
    public String join(@RequestBody MemberDto memberDto){
        // just for test
        memberService.insertMember(memberDto);
        return "Join complete";
    }


}
