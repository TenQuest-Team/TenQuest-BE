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

@RequestMapping(ENV.API_PREFIX+"/members")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class MemberController {
    @Autowired
    MemberService memberService;


    @GetMapping("")
    public Response<MemberDto> apiGetAllMembers(){
        ResponseStatus responseStatus = ResponseStatus.OK;
        DtoList<MemberDto> memberDtoList = memberService.getAllMembers();

        //return new ResponseDto<MemberDto>(responseStatus,memberDtoList).toResponse();
        return memberDtoList.toResponse(responseStatus);
    }
    @GetMapping("/{memberId}")
    public Response<MemberDto> apiGetMemberByMemberId(@PathVariable("memberId") String memberId){
        MemberDto nullableMemberDto = memberService.getMemberByMemberId(memberId);
        ResponseStatus responseStatus = ResponseStatus.OK;

        if (nullableMemberDto.memberId == null)
            responseStatus = ResponseStatus.NOT_FOUND;

        return nullableMemberDto.toResponse(responseStatus);
    }
    @GetMapping("/userId")
    public Response<MemberDto> apiGetMemberByUserId(@RequestParam("value") String userId){
        MemberDto nullableMemberDto = memberService.getMemberByUserId(userId);
        ResponseStatus responseStatus = ResponseStatus.OK;

        if (nullableMemberDto.memberId == null) responseStatus = ResponseStatus.NOT_FOUND;

        return nullableMemberDto.toResponse(responseStatus);

    }

    @GetMapping("/nameAndEmail")
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

    @GetMapping("/check-userId")
    /* ex) /api/v1/members/check-userId?value={사용자 id} */
    public Response apiIsUserIdExists(@RequestParam("value") String userId){
        ResponseStatus response = memberService.isUserIdExist(userId);
        return new ResponseDto(response).toResponse();
    }

    @GetMapping("/userName/{memberId}")
    /* ex) /api/v1/members/userName/{memberId} */
    public Response<String> ApigetUserNameByMemberId(@PathVariable("memberId") String memberId){
        var resultMap = memberService.getUserNameByMemberId(memberId);
        var responseStatus = (ResponseStatus) resultMap.get("ResponseStatus");
        var responseData = (String)resultMap.get("ResponseData");
        return new ResponseDto<String> (responseStatus, responseData).toResponse();

    }

    @PostMapping("")
    public Response<Integer> apiRegisterMember(@RequestBody MemberDto dto) {
        int insertResult = memberService.insertMember(dto);

        ResponseStatus responseStatus = ResponseStatus.CREATE_DONE;

        if (insertResult == ResponseStatus.CREATE_FAIL.getCode()){
            responseStatus = ResponseStatus.CREATE_FAIL;
            }

        return new ResponseDto<Integer>(responseStatus, insertResult).toResponse();

    }


}
