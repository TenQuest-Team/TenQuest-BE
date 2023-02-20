package com.kns.tenquest.dto;
import com.kns.tenquest.entity.Member;
import java.security.NoSuchAlgorithmException;

public class MemberDTO implements DataTransferObject<Member> {

    //public String memberId;
    public String userId;
    public String userInfo;
    public String userName;
    public String userEmail;

    @Override
    public Member toEntity() throws NoSuchAlgorithmException {
        Member member = Member.builder().userId(this.userId).userInfo(this.hashingInfo(this.userInfo)).userName(this.userName).userEmail(this.userEmail).build();
        return member;
    }

    public String hashingInfo(String userInfo) throws NoSuchAlgorithmException {
        StringBuffer resultStringBuffer = new StringBuffer();
        java.security.MessageDigest messageDigest = java.security.MessageDigest.getInstance("SHA-256");

        for (byte b : messageDigest.digest(userInfo.getBytes()))
            resultStringBuffer.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        return resultStringBuffer.toString();
    }
}
