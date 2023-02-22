package com.kns.tenquest.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="template_doc_table")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = {"template","question"})
@Data
public class TemplateDoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="template_doc_id")
    private Long templateDocId;

    @ManyToOne
    @JoinColumn(name="template_id")
    private Template template;

    @ManyToOne
    @JoinColumn(name="question_id")
    private Question question;
    @Column(name = "question_order",nullable = false)
    private Long questionOrder;

    public TemplateDoc update(Question question, Long questionOrder){
        this.question = question;
        this.questionOrder = questionOrder;
        return this;
    }

    @Builder
    public TemplateDoc(Long questionOrder){
        this.questionOrder = questionOrder;
    }
}
