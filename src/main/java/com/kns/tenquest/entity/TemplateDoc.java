package com.kns.tenquest.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
@Table(name="template_doc_table")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = {"template","question"})
@Data
public class TemplateDoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long template_doc_id;

    @ManyToOne
    @JoinColumn(name="template_id")
    private Template template;

    @ManyToOne
    @JoinColumn(name="question_id")
    private Question question;
    @Column(nullable = false)
    private Long question_order;

    public TemplateDoc update(Question question, Long question_order){
        this.question = question;
        this.question_order = question_order;
        return this;
    }

    @Builder
    public TemplateDoc(Long question_order){
        this.question_order = question_order;
    }
}
