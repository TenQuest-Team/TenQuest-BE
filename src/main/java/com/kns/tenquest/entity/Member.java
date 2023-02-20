package com.kns.tenquest.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name="member_test")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class Member {
    @Id
    private UUID member_id;
    private String user_id;
    private String user_info;
    private String user_name;
    private String user_email;

    public Member update(String user_email, String user_name){
        this.user_email = user_email;
        this.user_name = user_name;
        return this;
    }

    @Builder
    public Member(UUID member_id, String user_id, String user_info, String user_name, String user_email){
        this.member_id = UUID.randomUUID();
        this.user_id = user_id;
        this.user_info = user_info;
        this.user_name = user_name;
        this.user_email = user_email;
    }

}