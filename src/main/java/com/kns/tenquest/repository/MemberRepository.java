package com.kns.tenquest.repository;

import com.kns.tenquest.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MemberRepository extends JpaRepository<Member, UUID> {
    Optional<Member> findMemberByUserId(String userId);
    Optional<Member> findMemberByUserNameAndUserEmail(String userName,String userEmail);
    Optional<Member> findMemberByMemberId(String memberId);
}
