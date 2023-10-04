package com.kns.tenquest.service;

import com.kns.tenquest.DtoList;
import com.kns.tenquest.dto.MemberDto;
import com.kns.tenquest.dto.ServiceResult;
import com.kns.tenquest.entity.Member;
import com.kns.tenquest.repository.MemberRepository;
import com.kns.tenquest.response.ResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.util.*;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    public ServiceResult getAllMembers() {

        DtoList<MemberDto> memberDtoList = new DtoList<>(memberRepository.findAll());

        return new ServiceResult().success(memberDtoList);

    }
    public ServiceResult getMemberByMemberId(String memberId){
        Optional<Member> optMember = memberRepository.findMemberByMemberId(memberId);

        return optMember.isEmpty() ?
                new ServiceResult().fail().message("No member found with memberId: " + memberId)
                : new ServiceResult().success(new MemberDto(optMember.get()));
    }

    public ServiceResult getMemberByUserNameAndEmail(String userId, String userEmail){
        Optional<Member> optMember =  memberRepository.findMemberByUserNameAndUserEmail(userId,userEmail);
        return optMember.isEmpty() ?
                new ServiceResult().fail().message("No member found with userId: " + userId + " and userEmail: " + userEmail)
                : new ServiceResult().success(new MemberDto(optMember.get()));
    }

    public ServiceResult getMemberByUserId(String userId){
        Optional<Member> optMember = memberRepository.findMemberByUserId(userId);

        return optMember.isEmpty() ?
                new ServiceResult().fail().message("No member found with userId: " + userId)
                : new ServiceResult().success(new MemberDto(optMember.get()));


    }
    public ServiceResult getMemberIdByUserId(String userId) {
        Optional<Member> optMember = memberRepository.findMemberByUserId(userId);

        return optMember.isEmpty() ?
                new ServiceResult().fail().message("No member found with userId: " + userId)
                : new ServiceResult().success(optMember.get().getMemberId());
    }

    public ServiceResult getMemberIdByUserNameAndUserEmail(String userName, String userEmail) {
        Optional<Member> optMember = memberRepository.findMemberByUserNameAndUserEmail(userName,userEmail);

        return optMember.isEmpty() ?
                new ServiceResult().fail().message("No member found with userName: " + userName + " and userEmail: " + userEmail)
                : new ServiceResult().success(optMember.get().getMemberId());
    }

    public ServiceResult getUserNameByMemberId(String memberId) {
        Optional<Member> optMember = memberRepository.findMemberByMemberId(memberId);

        return optMember.isEmpty() ?
                new ServiceResult().fail().message("No member found with memberId: " + memberId)
                : new ServiceResult().success(optMember.get().getUserName());
    }

    public ServiceResult insertMember(MemberDto dto) {

        Optional<Member> optMember = memberRepository.findMemberByUserId(dto.userId);
        if (optMember.isEmpty()) {
            dto.setMemberId(UUID.randomUUID().toString().replace("-",""));
            dto.setUserRoles("ROLE_USER");
            memberRepository.save(dto.toEntity());
            return new ServiceResult().success()
                    .message("Member registered successfully")
                    .data(dto);
        }

        return new ServiceResult().fail().message("Member already exists");
    }

    public ServiceResult isUserIdExist(String userId){
        Optional<Member> optMember = memberRepository.findMemberByUserId(userId);
        return optMember.isEmpty() ?
                new ServiceResult().fail().message("No member found with userId: " + userId)
                : new ServiceResult().success(true);
    }
}