package com.kns.tenquest.controller;

import com.kns.tenquest.ENV;
import com.kns.tenquest.dto.MemberDto;
import com.kns.tenquest.dto.ServiceResult;
import com.kns.tenquest.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.kns.tenquest.service.MemberService;

@RequestMapping(ENV.API_PREFIX+"/members")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("")
    public Response apiGetAllMembers(){
        ServiceResult sr = memberService.getAllMembers();
        return sr.isFailed() ?
                new Response().BadRequest() : new Response().Ok(sr.getData());
    }

    @GetMapping("/{memberId}")
    public Response apiGetMemberByMemberId(@PathVariable("memberId") String memberId){
        ServiceResult sr = memberService.getMemberByMemberId(memberId);
        return sr.isFailed() ?
                new Response().BadRequest() : new Response().Ok(sr.getData());
    }

    @GetMapping("/userId")
    public Response apiGetMemberByUserId(@RequestParam("value") String userId){
        ServiceResult sr = memberService.getMemberByUserId(userId);
        return sr.isFailed() ?
                new Response().BadRequest() : new Response().Ok(sr.getData());
    }

    @GetMapping("/nameAndEmail")
    public Response apiGetMemberByUserNameAndUserId(@RequestParam("name") String userName, @RequestParam("email") String userEmail){
        ServiceResult sr = memberService.getMemberByUserNameAndEmail(userName,userEmail);
        return sr.isFailed() ?
                new Response().BadRequest() : new Response().Ok(sr.getData());
    }

    @GetMapping("/memberId/userId")
    public Response apiGetMemberIdByUserId(@RequestParam("value") String userId){
        ServiceResult sr = memberService.getMemberIdByUserId(userId);
        return sr.isFailed() ?
                new Response().BadRequest() : new Response().Ok(sr.getData());
    }

    @GetMapping("/memberId/nameAndEmail")
    public Response apiGetMemberIdByUserName(@RequestParam("name") String userName, @RequestParam("email") String userEmail){
        ServiceResult sr = memberService.getMemberIdByUserNameAndUserEmail(userName, userEmail);
        return sr.isFailed() ?
                new Response().BadRequest() : new Response().Ok(sr.getData());
    }

    @GetMapping("/check-userId")
    /* ex) /api/v1/members/check-userId?value={사용자 id} */
    public Response apiIsUserIdExists(@RequestParam("value") String userId){
        ServiceResult sr = memberService.isUserIdExist(userId);
        return sr.isFailed() ?
                new Response().BadRequest() : new Response().Ok(sr.getData());
    }

    @GetMapping("/userName/{memberId}")
    /* ex) /api/v1/members/userName/{memberId} */
    public Response ApigetUserNameByMemberId(@PathVariable("memberId") String memberId) {
        ServiceResult sr = memberService.getUserNameByMemberId(memberId);
        return sr.isFailed() ?
                new Response().BadRequest() : new Response().Ok(sr.getData());
    }

    @PostMapping("")
    public Response apiRegisterMember(@RequestBody MemberDto dto) {
        ServiceResult sr =  memberService.insertMember(dto);
        return sr.isFailed() ?
                new Response().BadRequest() : new Response().Ok(sr.getData());
    }


}
