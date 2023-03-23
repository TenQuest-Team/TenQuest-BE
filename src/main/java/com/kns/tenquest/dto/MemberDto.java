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

    public MemberDto(String memberId, String userId, String userInfo, String userName, String userEmail) {
        this.memberId = memberId;
        this.userId = userId;
        this.userInfo = userInfo;
        this.userName = userName;
        this.userEmail = userEmail;
    }

    public MemberDto(Member member){
        this.memberId = member.getMemberId();
        this.userId = member.getUserId();
        this.userInfo = member.getUserInfo();
        this.userName = member.getUserName();
        this.userEmail = member.getUserEmail();
    }

    @Override
    public Member toEntity() {
        Member member = Member.builder().memberId(this.memberId).userId(this.userId).userInfo(this.hashingInfo(this.userInfo)).userName(this.userName).userEmail(this.userEmail).build();
        return member;
    }
    @Override
    public DataTransferObject<Member> toDto(Member member) {
        return new MemberDto(member.getMemberId(),member.getUserId(),member.getUserInfo(),member.getUserName(), member.getUserEmail());
    }

    public String hashingInfo(String userInfo){
        StringBuffer resStrBuffer = new StringBuffer();
        try {
            java.security.MessageDigest messageDigest = java.security.MessageDigest.getInstance("SHA-256");
            for (byte b : messageDigest.digest(userInfo.getBytes()))
                resStrBuffer.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return resStrBuffer.toString();
    }
}
