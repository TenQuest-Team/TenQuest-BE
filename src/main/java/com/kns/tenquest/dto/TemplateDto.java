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
    public String templateOwner;
    public Boolean isPublic;

    public TemplateDto(String templateId,
                       String templateName,
                       String templateOwner,
                       LocalDateTime createdAt,
                       Boolean isPublic){
        this.templateId = templateId;
        this.templateName = templateName;
        this.templateOwner = templateOwner;
        this.createdAt = createdAt;
        this.isPublic = isPublic;
    }

    public TemplateDto(Template template){
        this.templateId = template.getTemplateId();
        this.templateName = template.getTemplateName();
        this.templateOwner = template.getTemplateOwner();
        this.createdAt = template.getCreatedAt();
        this.isPublic = template.getIsPublic();
    }

    @Override
    public Template toEntity(){
        return Template.
                builder().
                templateId(this.templateId).templateName(this.templateName).templateOwner(this.templateOwner).createdAt(this.createdAt).isPublic(this.isPublic).build();
    }

    @Override
    public DataTransferObject<Template> toDto(Template template) {
        return new TemplateDto(template.getTemplateId(),template.getTemplateName(),template.getTemplateOwner(),template.getCreatedAt(),template.getIsPublic());
    }


}
