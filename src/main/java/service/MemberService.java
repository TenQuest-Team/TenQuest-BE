package service;

import entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.MemberRepository;

import java.util.List;
import java.util.UUID;


@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepository;
    public List<Member> getAllMembers(){
        // Temporarily implemented. Just for test.
        return memberRepository.findAll();
    }
}