package com.kns.tenquest.service;

import com.kns.tenquest.DtoList;
import com.kns.tenquest.dto.MemberDto;
import com.kns.tenquest.entity.Member;
import com.kns.tenquest.repository.MemberRepository;
import com.kns.tenquest.response.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepository;

    public DtoList<MemberDto> getAllMembers() {
        // Temporarily implemented. Just for test.
        //List<Member> allMemberList= memberRepository.findAll();
        DtoList<MemberDto> memberDtoList = new DtoList<>(memberRepository.findAll());
//        List<MemberDto> allMemberListDto = new ArrayList<>();
//        for (Member member: allMemberList){
//            allMemberListDto.add(new MemberDto(member));
//        }
        return memberDtoList;
    }
    public MemberDto getMemberByMemberId(String memberId){
        return new MemberDto(
                memberRepository.findMemberByMemberId(memberId)
                        .orElse(new Member()));
    }

    public MemberDto getMemberByUserNameAndEmail(String userId, String userEmail){
        return new MemberDto(
                memberRepository.findMemberByUserNameAndUserEmail(userId,userEmail)
                        .orElse(new Member()));
    }

    public MemberDto getMemberByUserId(String userId){
        return new MemberDto(memberRepository.findMemberByUserId(userId).orElse(new Member()));
    }
    public String getMemberIdByUserId(String userId) {
        Optional<Member> optMember = memberRepository.findMemberByUserId(userId);
        if (!optMember.isEmpty()) return optMember.get().getMemberId();
        return ResponseStatus.NOT_FOUND.getStatus();
    }

    public String getMemberIdByUserNameAndUserEmail(String userName, String userEmail) {
        Optional<Member> optMember = memberRepository.findMemberByUserNameAndUserEmail(userName,userEmail);
        if (!optMember.isEmpty())
            return optMember.get().getMemberId();
        return ResponseStatus.NOT_FOUND.getStatus();
    }

    public int insertMember(MemberDto dto) throws NoSuchAlgorithmException {
        Optional<Member> optMember = memberRepository.findMemberByUserId(dto.userId);
        if (optMember.isEmpty()) {
            dto.setMemberId(UUID.randomUUID().toString().replace("-",""));
            memberRepository.save(dto.toEntity());
            return ResponseStatus.CREATE_DONE.getCode();
        }
        return ResponseStatus.CREATE_FAIL.getCode();
    }
}