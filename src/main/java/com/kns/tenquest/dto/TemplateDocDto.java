package com.kns.tenquest.dto;

import com.kns.tenquest.entity.TemplateDoc;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TemplateDocDto implements DataTransferObject<TemplateDoc> {
    public Long templateDocId;
    public String templateId;
    public String questionId;
    public Long questionOrder;

    public TemplateDocDto(Long templateDocId, String templateId, String questionId, Long questionOrder){
        this.templateDocId = templateDocId;
        this.templateId = templateId;
        this.questionId = questionId;
        this.questionOrder = questionOrder;
    }

    public TemplateDocDto(TemplateDoc templateDoc){
        this.templateDocId = templateDoc.getTemplateDocId();
        this.questionId = templateDoc.getQuestionId();
        this.templateId = templateDoc.getTemplateId();
        this.questionOrder = templateDoc.getQuestionOrder();
    }

    @Override
    public TemplateDoc toEntity() {
        return TemplateDoc.builder().templateDocId(this.templateDocId).templateId(this.templateId).questionId(this.questionId).questionOrder(this.questionOrder).build();
    }

    @Override
    public DataTransferObject<TemplateDoc> toDto(TemplateDoc templateDoc) {
        return new TemplateDocDto(templateDoc.getTemplateDocId(),templateDoc.getTemplateId(),templateDoc.getQuestionId(),templateDoc.getQuestionOrder());
    }
}
