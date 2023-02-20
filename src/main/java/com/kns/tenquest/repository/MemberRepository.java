package com.kns.tenquest.repository;

import com.kns.tenquest.dto.MemberResponseMapping;
import com.kns.tenquest.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MemberRepository extends JpaRepository<Member, UUID> {
    Optional<MemberResponseMapping> findMemberByUserId(String userId);
    Optional<MemberResponseMapping> findMemberByUserNameAndUserEmail(String userName,String userEmail);
    Optional<MemberResponseMapping> findMemberByMemberId(String memberId);
}
