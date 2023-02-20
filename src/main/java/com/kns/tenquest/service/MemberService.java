package com.kns.tenquest.service;

import com.kns.tenquest.dto.MemberDTO;
import com.kns.tenquest.entity.Member;
import com.kns.tenquest.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    public static final String NOT_FOUND = "NOT_FOUND";
    @Autowired
    MemberRepository memberRepository;

    public List<Member> getAllMembers() {
        // Temporarily implemented. Just for test.
        return memberRepository.findAll();
    }

    public String getMemberIdByUserId(String userId) {
        Optional<Member> optMember = memberRepository.findMemberByUserId(userId);
        if (!optMember.isEmpty()) return optMember.get().getMemberId();
        return NOT_FOUND;
    }

    public String getMemberIdByUserNameAndUserEmail(String userName, String userEmail) {
        Optional<Member> optMember = memberRepository.findMemberByUserNameAndUserEmail(userName,userEmail);
        if (!optMember.isEmpty()) return optMember.get().getMemberId();
        return NOT_FOUND;
    }

    public Member insertMember(MemberDTO dto) throws NoSuchAlgorithmException {
        return memberRepository.save(dto.toEntity());
    }
}