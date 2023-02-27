package com.kns.tenquest.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="template_table")
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
//@ToString(exclude = {"member"})
@Data
public class Template {

    @Id
    @Column(name = "template_id") //UUID 자동 생성 함수 선언
    private String templateId;
    @Column(name="template_name") //requestBody로 받기
    private String templateName;

    @Column(name="created_at") //함수로 자동 생성
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name="template_owner")
    private Member member;

//    @Column(name="template_owner") //세션으로 받기
//    private String templateOwner;
    @Column(name = "is_public") //RequestBody로 받기
    private Boolean isPublic;

//    public Template update(String templateName, Boolean isPublic){
//        this.templateName = templateName;
//        this.createAt = LocalDateTime.now();
//        this.isPublic = isPublic;
//        return this;
//    }

    @Builder
    public Template(String templateId, String templateName,LocalDateTime createdAt, Member member, Boolean isPublic){
        this.templateId = templateId;
        this.templateName = templateName;
        this.createdAt = createdAt;
        this.member = member;
        this.isPublic = isPublic;
    }
}
