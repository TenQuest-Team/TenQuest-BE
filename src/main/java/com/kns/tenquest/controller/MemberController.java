package com.kns.tenquest.controller;

import com.kns.tenquest.ENV;
import com.kns.tenquest.dto.MemberDto;
import com.kns.tenquest.dto.ServiceResult;
import com.kns.tenquest.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.kns.tenquest.service.MemberService;

/**
 * /api/v1/members<br>
 * @apiNote 회원 정보를 관리하는 컨트롤러
 * @see com.kns.tenquest.response.Response
 * @see com.kns.tenquest.dto.ServiceResult
 * @see com.kns.tenquest.service.MemberService
 */
@RequestMapping(ENV.API_PREFIX)
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * Request: [GET] /api/v1/members<br>
     * @apiNote 등록된 모든 회원 정보를 조회한다.
     * @see com.kns.tenquest.service.MemberService#getAllMembers()
     * @return  com.kns.tenquest.response.Response
     * <br> 성공 시, 모든 회원 정보를 담은 Response를 반환한다.
     **/
    @GetMapping("/members")
    public Response apiGetAllMembers(){
        ServiceResult sr = memberService.getAllMembers();
        return sr.isFailed() ?
                new Response().BadRequest() : new Response().Ok(sr.getData());
    }

    /**
     * Request: [GET] /api/v1/members/memberId/value=${memberId}<br>
     * @apiNote 회원의 `회원 식별자(memberId)`로 특정 회원 정보를 조회한다.<br>
     * @param memberId 조회할 회원의 `회원 식별자(memberId)`
     * @see com.kns.tenquest.service.MemberService#getMemberByMemberId(String)
     * @return  com.kns.tenquest.response.Response
     * <br> 성공 시, 요청한 회원 정보를 담은 Response를 반환한다.
     **/
    @GetMapping("/members/{memberId}")
    public Response apiGetMemberByMemberId(@PathVariable("memberId") String memberId){
        ServiceResult sr = memberService.getMemberByMemberId(memberId);
        return sr.isFailed() ?
                new Response().BadRequest() : new Response().Ok(sr.getData());
    }

    /**
     * Request: [GET] /api/v1/members/userId?value=${userId}<br>
     * @apiNote 회원의 로그인 아이디로 특정 회원 정보를 조회한다.<br>
     * @param userId 조회할 회원의 로그인 아이디
     * @see com.kns.tenquest.service.MemberService#getMemberByUserId(String)
     * @return  com.kns.tenquest.response.Response
     * <br> 성공 시, 요청한 회원 정보를 담은 Response를 반환한다.
     **/
    @GetMapping("/userId")
    public Response apiGetMemberByUserId(@RequestParam("value") String userId){
        ServiceResult sr = memberService.getMemberByUserId(userId);
        return sr.isFailed() ?
                new Response().BadRequest() : new Response().Ok(sr.getData());
    }

    /**
     * Request: [GET] /api/v1/members/nameAndEmail?name=${userName}&email=${userEmail}<br>
     * @apiNote 회원의 이름과 이메일로 특정 회원 정보를 조회한다.<br>
     * @param userName 조회할 회원의 이름(또는 닉네임)
     * @param userEmail 조회할 회원의 이메일
     * @see com.kns.tenquest.service.MemberService#getMemberByUserNameAndEmail(String, String)
     * @see com.kns.tenquest.dto.ServiceResult
     * @see com.kns.tenquest.response.Response
     * @return  com.kns.tenquest.response.Response
     * <br> 성공 시, 요청한 회원 정보를 담은 Response를 반환한다.
     **/
    @GetMapping("/nameAndEmail")
    public Response apiGetMemberByUserNameAndUserId(@RequestParam("name") String userName, @RequestParam("email") String userEmail){
        ServiceResult sr = memberService.getMemberByUserNameAndEmail(userName,userEmail);
        return sr.isFailed() ?
                new Response().BadRequest() : new Response().Ok(sr.getData());
    }

    /**
     * @apiNote
     * HttpMethod: GET<br>
     * Uri: /api/v1/members/memberId/value=${userId}<br>
     * Description: 회원이 로그인시 사용하는 아이디로 특정 회원의 `회원 식별자(memberId)`를 조회한다.<br>
     * @param userId 조회할 회원의 로그인 아이디
     * @see com.kns.tenquest.service.MemberService#getMemberIdByUserId(String)
     * @return  com.kns.tenquest.response.Response
     * <br> 성공 시, 조회된 회원의 `회원 식별자(memberId)`를 반환한다.
     **/
    @GetMapping("/memberId/userId")
    public Response apiGetMemberIdByUserId(@RequestParam("value") String userId){
        ServiceResult sr = memberService.getMemberIdByUserId(userId);
        return sr.isFailed() ?
                new Response().BadRequest() : new Response().Ok(sr.getData());
    }

    /**
     * Request: [GET] /api/v1/members/memberId/nameAndEmail?name=${userName}&email=${userEmail}<br>
     * @apiNote 회원의 이름으로 회원의 `회원 식별자(memberId)`를 조회한다.<br>
     * @param userName 조회할 회원의 이름(또는 닉네임)
     * @param userEmail 조회할 회원의 이메일
     * @see com.kns.tenquest.service.MemberService#getMemberByMemberId(String)
     * @return  com.kns.tenquest.response.Response
     * <br> 성공 시, 조회된 회원의 `회원 식별자(memberId)`를 담은 Response를 반환한다.
     **/
    @GetMapping("/memberId/nameAndEmail")
    public Response apiGetMemberIdByUserName(@RequestParam("name") String userName, @RequestParam("email") String userEmail){
        ServiceResult sr = memberService.getMemberIdByUserNameAndUserEmail(userName, userEmail);
        return sr.isFailed() ?
                new Response().BadRequest() : new Response().Ok(sr.getData());
    }

    /**
     * Request: [GET] /api/v1/members/check-userId?value=${userId}<br>
     * @apiNote 특정 로그인 아이디가 사용중인 아이디인지 확인한다.<br>
     * @param userId 조회할 회원의 로그인 아이디
     * @see com.kns.tenquest.service.MemberService#isUserIdExist(String)
     * @return  com.kns.tenquest.response.Response
     * <br> 성공 시, 요청한 아이디가 사용중인 아이디인지 여부를 담은 Response를 반환한다.
     **/
    @GetMapping("/check-userId")
    public Response apiIsUserIdExists(@RequestParam("value") String userId){
        ServiceResult sr = memberService.isUserIdExist(userId);
        return sr.isFailed() ?
                new Response().BadRequest() : new Response().Ok(sr.getData());
    }

    /**
     * Request: [GET] /api/v1/members/userName/${memberId}<br>
     * @apiNote `회원 식별자(memberId)`를 통해 해당 회원의 `로그인 아이디(userId)`를 조회한다.<br>
     * @param memberId 조회할 회원의 `회원 식별자(memberId)`
     * @see com.kns.tenquest.service.MemberService#getUserNameByMemberId(String)
     * @return  com.kns.tenquest.response.Response
     * <br> 성공 시, 요청한 회원의 `로그인 아이디(userId)`를 담은 Response를 반환한다.
     **/
    @GetMapping("/userName/{memberId}")
    /* ex) /api/v1/members/userName/{memberId} */
    public Response ApigetUserNameByMemberId(@PathVariable("memberId") String memberId) {
        ServiceResult sr = memberService.getUserNameByMemberId(memberId);
        return sr.isFailed() ?
                new Response().BadRequest() : new Response().Ok(sr.getData());
    }


    /**
     * Request: [POST] /api/v1/members<br>
     * @apiNote 회원가입 요청을 처리한다.<br>
     * @param memberDto 회원가입에 필요한 정보를 담은 RequestBody 객체
     * @see com.kns.tenquest.service.MemberService#insertMember(MemberDto)
     * @return  com.kns.tenquest.response.Response
     * <br> 성공 시, 회원 가입된 회원 정보를 담은 Response를 반환한다.
     **/
    @PostMapping("")
    public Response apiRegisterMember(@RequestBody MemberDto memberDto) {
        ServiceResult sr =  memberService.insertMember(memberDto);
        return sr.isFailed() ?
                new Response().BadRequest() : new Response().Ok(sr.getData());
    }


}
