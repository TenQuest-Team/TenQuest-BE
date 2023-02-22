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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@ToString(exclude = {"member"})
@Data
public class Template {

    @Id
    @Column(name = "template_id")
    private String templateId = UUID.randomUUID().toString().replace("-","");
    @Column(name="template_name")
    private String templateName;

    @CreatedDate
    @Column(name="create_at" , updatable = false)
    private LocalDateTime createAt;

//    @Column(nullable = false)
//    @ManyToOne
//    @JoinColumn(name="template_owner")
//    private Member member;

    @Column(name="template_owner")
    private String templateOwner;
    @Column(name = "is_public")
    private Boolean isPublic;

//    public Template update(String templateName, Boolean isPublic){
//        this.templateName = templateName;
//        this.createAt = LocalDateTime.now();
//        this.isPublic = isPublic;
//        return this;
//    }

//    @Builder
//    public Template(){
//        this.templateId = UUID.randomUUID().toString().replace("-","");
//        this.templateOwner = UUID.randomUUID().toString().replace("-","");
//        this.isPublic = Boolean.TRUE;
//        this.createAt = LocalDateTime.now();
//    }
}
