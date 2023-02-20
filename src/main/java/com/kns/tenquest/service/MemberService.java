package com.kns.tenquest.service;

import com.kns.tenquest.dto.MemberDTO;
import com.kns.tenquest.entity.Member;
import com.kns.tenquest.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepository;
    public List<Member> getAllMembers(){
        // Temporarily implemented. Just for test.
        return memberRepository.findAll();
    }
    public Member insertMember(MemberDTO dto) throws NoSuchAlgorithmException {
        return memberRepository.save(dto.toEntity());
    }
}