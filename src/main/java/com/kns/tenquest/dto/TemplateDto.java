package com.kns.tenquest.dto;

import com.kns.tenquest.entity.Member;
import com.kns.tenquest.entity.Template;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
public class TemplateDto implements DataTransferObject<Template> {
    public String templateId;
    public String templateName;
    public LocalDateTime createdAt;
    public Member member;
    public Boolean isPublic;

    public TemplateDto(String templateId,
                       String templateName, LocalDateTime createdAt,
                       Member member,
                       Boolean isPublic){
        this.templateId = templateId;
        this.templateName = templateName;
        this.createdAt = createdAt;
        this.member = member;
        this.isPublic = isPublic;
    }

    public TemplateDto(Template template){
        this.templateId = template.getTemplateId();
        this.templateName = template.getTemplateName();
        this.createdAt = template.getCreatedAt();
        this.member = template.getMember();
        this.isPublic = template.getIsPublic();
    }

    @Override
    public Template toEntity(){
        return Template.
                builder().
                templateId(this.templateId).templateName(this.templateName).member(this.member).createdAt(this.createdAt).isPublic(this.isPublic).build();
    }

    @Override
    public DataTransferObject<Template> toDto(Template template) {
        return new TemplateDto(template.getTemplateId(),template.getTemplateName(),template.getCreatedAt(),template.getMember(),template.getIsPublic());
    }
}
