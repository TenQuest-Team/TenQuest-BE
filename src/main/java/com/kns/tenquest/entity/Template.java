package com.kns.tenquest.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="template_table")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = {"member"})
@Data
public class Template {

    @Id
    @Column(name = "template_id")
    private UUID templateId;
    @Column(name="template_name", nullable = false)
    private String templateName;
    @Column(name="create_at", nullable = false)
    private LocalDateTime createAt;
    @Column(nullable = false)
    @ManyToOne
    @JoinColumn(name="template_owner")
    private Member member;
    @Column(name = "is_public", nullable = false)
    private Boolean isPublic;

    public Template update(String templateName, Boolean isPublic){
        this.templateName = templateName;
        this.createAt = LocalDateTime.now();
        this.isPublic = isPublic;
        return this;
    }

    @Builder
    public Template(String templateName, Boolean isPublic){
        this.templateId = UUID.randomUUID();
        this.templateName = templateName;
        this.createAt = LocalDateTime.now();
        this.isPublic = isPublic;
    }
}
