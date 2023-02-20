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
    private UUID template_id;
    @Column(nullable = false)
    private String template_name;
    @Column(nullable = false)
    private LocalDateTime create_at;
    @Column(nullable = false)
    @ManyToOne
    @JoinColumn(name="template_owner")
    private Member member;
    @Column(nullable = false)
    private Boolean is_public;

    public Template update(String template_name, LocalDateTime create_at, Boolean is_public){
        this.template_name = template_name;
        this.create_at = LocalDateTime.now();
        this.is_public = is_public;
        return this;
    }

    @Builder
    public Template(UUID template_id, String template_name, LocalDateTime create_at, Boolean is_public){
        this.template_id = UUID.randomUUID();
        this.template_name = template_name;
        this.create_at = LocalDateTime.now();
        this.is_public = is_public;
    }
}
