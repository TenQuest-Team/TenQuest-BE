package com.kns.tenquest.service;

import com.kns.tenquest.dto.TemplateDto;
import com.kns.tenquest.entity.Template;
import com.kns.tenquest.repository.TemplateRepository;
import com.kns.tenquest.response.ResponseStatus;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.lang.Boolean.TRUE;

@Service
public class TemplateService {
    @Autowired
    TemplateRepository templateRepository;

    public List<TemplateDto> getAllTemplates(){
        List<Template> allTemplateList = templateRepository.findAll();
        List<TemplateDto> allTemplateListDto = new ArrayList<>();
        for(Template template : allTemplateList){
            allTemplateListDto.add(new TemplateDto(template));
        }
        return allTemplateListDto;
    }
    public TemplateDto getTemplateByTemplateName(String templateName){
        return new TemplateDto(templateRepository.findTemplateByTemplateName(templateName).orElse(new Template()));
    }
    public int createTemplate(TemplateDto templatedto) {
        Optional<Template> optTemplate = templateRepository.findTemplateByTemplateName(templatedto.templateName);
        if(optTemplate.isEmpty()){
            templatedto.setCreatedAt(LocalDateTime.now());
            templatedto.setTemplateId(UUID.randomUUID().toString().replace("-",""));
            templatedto.setIsPublic(TRUE);
            templateRepository.save(templatedto.toEntity());
            return ResponseStatus.CREATE_DONE.getCode();
        }
        return ResponseStatus.CREATE_FAIL.getCode();
    } //처음 create 시 생성값 주기

    public int templateUpdate(String templateId, TemplateDto templateDto) {
        Optional<Template> optTemplateDto = templateRepository.findById(templateId);
        if(optTemplateDto.isPresent()){
            Template template = optTemplateDto.get();
            if(StringUtils.isNotBlank(templateDto.getTemplateName()))
                template.setTemplateName(templateDto.getTemplateName());
            if(StringUtils.isNotBlank(templateDto.getIsPublic().toString()))
                template.setIsPublic(templateDto.getIsPublic());
            templateRepository.save(template);
            return ResponseStatus.CREATE_DONE.getCode();
        }
        return ResponseStatus.CREATE_FAIL.getCode();
    } //수정시 변경사항을 controller에서 적용한 후 저장

    public Template templateView(String templateId){
        return templateRepository.findById(templateId).get();
    }

    public  void templateDelete(String templateId){
        templateRepository.deleteById(templateId);
    }
}
