package com.kns.tenquest.dto;

import com.kns.tenquest.entity.Question;
import com.kns.tenquest.entity.Template;
import com.kns.tenquest.entity.TemplateDoc;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TemplateDocDto implements DataTransferObject<TemplateDoc> {
    public Long templateDocId;
    public Template template;
    public Question question;
    public Long questionOrder;

    public TemplateDocDto(Long templateDocId,
                          Template template,
                          Question question,
                          Long questionOrder){
        this.templateDocId = templateDocId;
        this.template = template;
        this.question = question;
        this.questionOrder = questionOrder;
    }

    public TemplateDocDto(TemplateDoc templateDoc){
        this.templateDocId = templateDoc.getTemplateDocId();
        this.question = templateDoc.getQuestion();
        this.template = templateDoc.getTemplate();
        this.questionOrder = templateDoc.getQuestionOrder();
    }

    @Override
    public TemplateDoc toEntity() {
        return TemplateDoc.builder().templateDocId(this.templateDocId).template(this.template).question(this.question).questionOrder(this.questionOrder).build();
    }

    @Override
    public DataTransferObject<TemplateDoc> toDto(TemplateDoc templateDoc) {
        return new TemplateDocDto(templateDoc.getTemplateDocId(),templateDoc.getTemplate(),templateDoc.getQuestion(),templateDoc.getQuestionOrder());
    }
}
