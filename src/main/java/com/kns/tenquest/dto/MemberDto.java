package com.kns.tenquest.dto;
import com.kns.tenquest.entity.Member;
import com.kns.tenquest.response.Responseable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.security.NoSuchAlgorithmException;

@Getter
@Setter
@NoArgsConstructor
public class MemberDto implements DataTransferObject<Member>, Responseable<MemberDto>{
    public String memberId;
    public String userId;
    public String userInfo;
    public String userName;
    public String userEmail;
    public String userRoles;

    public MemberDto(String memberId, String userId, String userInfo, String userName, String userEmail, String userRoles) {
        this.memberId = memberId;
        this.userId = userId;
        this.userInfo = userInfo;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userRoles = userRoles;
    }

    public MemberDto(Member member){
        this.memberId = member.getMemberId();
        this.userId = member.getUserId();
        this.userInfo = member.getUserInfo();
        this.userName = member.getUserName();
        this.userEmail = member.getUserEmail();
        this.userRoles = member.getUserRoles();
    }

    @Override
    public Member toEntity() throws NoSuchAlgorithmException {
        Member member = Member.builder().memberId(this.memberId).userId(this.userId).userInfo(this.hashingInfo(this.userInfo)).userName(this.userName).userEmail(this.userEmail).userRoles(this.userRoles).build();
        return member;
    }
    @Override
    public DataTransferObject<Member> toDto(Member member) {
        return new MemberDto(member.getMemberId(),member.getUserId(),member.getUserInfo(),member.getUserName(), member.getUserEmail(), member.getUserRoles());
    }

    public String hashingInfo(String userInfo) throws NoSuchAlgorithmException {
        StringBuffer resStrBuffer = new StringBuffer();
        java.security.MessageDigest messageDigest = java.security.MessageDigest.getInstance("SHA-256");

        for (byte b : messageDigest.digest(userInfo.getBytes()))
            resStrBuffer.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        return resStrBuffer.toString();
    }
}
