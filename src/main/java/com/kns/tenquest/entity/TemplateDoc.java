package com.kns.tenquest.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="template_doc_table")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@ToString(exclude = {"template","question"})
@Data
public class TemplateDoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="template_doc_id")
    private Long templateDocId;

//    @ManyToOne
//    @JoinColumn(name="template_id")
//    private Template template;
    @Column(name="template_id")
    private String templateId;

//    @ManyToOne
//    @JoinColumn(name="question_id")
//    private Question question;
    @Column(name="question_id")
    private String questionId;

    @Column(name = "question_order")
    private Long questionOrder;

    @Builder
    public TemplateDoc(Long templateDocId, String templateId, String questionId, Long questionOrder){
        this.templateDocId = templateDocId;
        this.templateId = templateId;
        this.questionId = questionId;
        this.questionOrder = questionOrder;
    }
}
