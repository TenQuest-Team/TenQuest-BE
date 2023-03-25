package com.kns.tenquest.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name="template_table")
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
//@ToString(exclude = {"member"})
@Data
public class Template {

    @Id
    @Column(name = "template_id")
    private String templateId;
    @Column(name="template_name")
    private String templateName;

    @Column(name="created_at")
    private LocalDateTime createdAt;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="template_owner",referencedColumnName = "member_id", nullable = false)
//    private Member templateOwner;

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

    @Builder
    public Template(String templateId, String templateName, String templateOwner,LocalDateTime createdAt, Boolean isPublic){
        this.templateId = templateId;
        this.templateName = templateName;
        this.templateOwner = templateOwner;
        this.createdAt = createdAt;
        this.isPublic = isPublic;
    }
}
