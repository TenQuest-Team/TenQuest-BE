package com.kns.tenquest.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Table(name = "member_table")
@Entity
public class Member{
    @Id
    @Column(name = "member_id")
    private String memberId;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "user_info")
    private String userInfo;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_email")
    private String userEmail;
}