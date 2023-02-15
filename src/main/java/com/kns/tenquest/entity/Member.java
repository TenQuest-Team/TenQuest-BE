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
    private UUID member_id;
    @Column(name = "user_id")
    private String user_id;
    @Column(name = "user_info")
    private String user_info;
    @Column(name = "user_name")
    private String user_name;
    @Column(name = "user_email")
    private String user_email;
}