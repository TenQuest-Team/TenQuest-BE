package com.kns.tenquest.service;

import com.kns.tenquest.dto.MemberDTO;
import com.kns.tenquest.entity.Member;
import com.kns.tenquest.repository.MemberRepository;
import com.kns.tenquest.dto.MemberResponseMapping;
import com.kns.tenquest.response.ResponseStatus;
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
    public MemberResponseMapping getMemberByMemberId(String memberId){
        return memberRepository.findMemberByMemberId(memberId).orElse(new MemberResponseMapping() {
            @Override
            public String getMemberId() {
                return null;
            }

            @Override
            public String getUserId() {
                return null;
            }

            @Override
            public String getUserName() {
                return null;
            }

            @Override
            public String getUserEmail() {
                return null;
            }
        });
    }

    public MemberResponseMapping getMemberByUserNameAndEmail(String userId, String userEmail){
        Optional<MemberResponseMapping> opt = memberRepository.findMemberByUserNameAndUserEmail(userId,userEmail);
        if (!opt.isEmpty())
            return opt.get();
        return new MemberResponseMapping() {
            @Override
            public String getMemberId() {
                return null;
            }

            @Override
            public String getUserId() {
                return null;
            }

            @Override
            public String getUserName() {
                return null;
            }

            @Override
            public String getUserEmail() {
                return null;
            }
        };
    }

    public MemberResponseMapping getMemberByUserId(String userId){
        return memberRepository.findMemberByUserId(userId).orElse(new MemberResponseMapping() {
            @Override
            public String getMemberId() {
                return null;
            }

            @Override
            public String getUserId() {
                return null;
            }

            @Override
            public String getUserName() {
                return null;
            }

            @Override
            public String getUserEmail() {
                return null;
            }
        });
    }
    public String getMemberIdByUserId(String userId) {
        Optional<MemberResponseMapping> optMember = memberRepository.findMemberByUserId(userId);
        if (!optMember.isEmpty()) return optMember.get().getMemberId();
        return NOT_FOUND;
    }

    public String getMemberIdByUserNameAndUserEmail(String userName, String userEmail) {
        Optional<MemberResponseMapping> optMember = memberRepository.findMemberByUserNameAndUserEmail(userName,userEmail);
        if (!optMember.isEmpty()) return optMember.get().getMemberId();
        return NOT_FOUND;
    }

    public ResponseStatus insertMember(MemberDTO dto) throws NoSuchAlgorithmException {
        Optional<MemberResponseMapping> optMember = memberRepository.findMemberByUserId(dto.userId);
        if (optMember.isEmpty()) {
            memberRepository.save(dto.toEntity());
            return ResponseStatus.CREATE_DONE;
        }
        return ResponseStatus.CREATE_FAIL;
    }
}