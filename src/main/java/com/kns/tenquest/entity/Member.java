package com.kns.tenquest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Getter
@Table(name = "member_table")
@NoArgsConstructor
@Entity
public class Member{
    @Id
    @Column(name = "member_id")
    private String memberId;
    @Column(name = "user_id")
    private String userId;
    @JsonIgnore
    @Column(name = "user_info")
    private String userInfo;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_email")
    private String userEmail;
    @Column(name = "user_roles")
    private String userRoles;

    @Builder
    public Member(String memberId, String userId, String userInfo, String userName, String userEmail, String userRoles) {
        this.memberId = memberId;
        this.userId = userId;
        this.userInfo = userInfo;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userRoles = userRoles;
    }

    public List<String> getRoleList(){

        if(this.userRoles != null && this.userRoles.length() > 0){
            return Arrays.asList(this.userRoles.split(","));
        }
        return new ArrayList<>();
    }
}