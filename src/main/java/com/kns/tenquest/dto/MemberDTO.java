package com.kns.tenquest.dto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kns.tenquest.entity.Member;
import java.security.NoSuchAlgorithmException;

public class MemberDTO implements DataTransferObject<Member> {
    public String userId;
    //@JsonIgnore
    public String userInfo;
    public String userName;
    public String userEmail;

    public MemberDTO(String userId, String userInfo, String userName, String userEmail) {
        this.userId = userId;
        this.userInfo = userInfo;
        this.userName = userName;
        this.userEmail = userEmail;
    }

    @Override
    public Member toEntity() throws NoSuchAlgorithmException {
        Member member = Member.builder().userId(this.userId).userInfo(this.hashingInfo(this.userInfo)).userName(this.userName).userEmail(this.userEmail).build();
        return member;
    }
    @Override
    public DataTransferObject<Member> toDto(Member member) {
        return new MemberDTO(member.getUserId(),member.getUserInfo(),member.getUserName(), member.getUserEmail());
    }

    public String hashingInfo(String userInfo) throws NoSuchAlgorithmException {
        StringBuffer resStrBuffer = new StringBuffer();
        java.security.MessageDigest messageDigest = java.security.MessageDigest.getInstance("SHA-256");

        for (byte b : messageDigest.digest(userInfo.getBytes()))
            resStrBuffer.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        return resStrBuffer.toString();
    }
}
