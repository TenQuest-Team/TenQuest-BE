package com.kns.tenquest.service;

import com.kns.tenquest.DtoList;
import com.kns.tenquest.dto.ResponseDto;
import com.kns.tenquest.dto.TemplateDto;
import com.kns.tenquest.entity.Template;
import com.kns.tenquest.entity.TemplateDoc;
import com.kns.tenquest.repository.TemplateRepository;
import com.kns.tenquest.response.Response;
import com.kns.tenquest.response.ResponseStatus;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static java.lang.Boolean.TRUE;

@Service
public class TemplateService {
    @Autowired
    TemplateRepository templateRepository;

    public DtoList<TemplateDto> getAllTemplates(){
        DtoList<TemplateDto> TemplateDtoList = new DtoList<>(templateRepository.findAll());
        return TemplateDtoList;
    }
    public TemplateDto getTemplateByTemplateName(String templateName){
        return new TemplateDto(templateRepository.findTemplateByTemplateName(templateName).orElse(new Template()));
    }
    public int createTemplate(TemplateDto templatedto) {
        Optional<Template> optTemplate = templateRepository.findTemplateByTemplateName(templatedto.templateName);
        if(optTemplate.isEmpty()){
            templatedto.setCreatedAt(LocalDateTime.now());
            templatedto.setTemplateId(UUID.randomUUID().toString().replace("-",""));
            templatedto.setTemplateOwner(UUID.randomUUID().toString().replace("-",""));
            templatedto.setIsPublic(true);
            templateRepository.save(templatedto.toEntity());
            return ResponseStatus.CREATE_DONE.getCode();
        }
        else{
            return ResponseStatus.CREATE_FAIL.getCode();
        }

    } //처음 create 시 생성값 주기

    public ResponseDto<Template> templateUpdate(String templateId, TemplateDto templateDto) {
        Optional<Template> optTemplate = templateRepository.findById(templateId);
        if(optTemplate.isPresent()){
            Template template = optTemplate.get();
            if(StringUtils.isNotBlank(templateDto.getTemplateName()))
                template.setTemplateName(templateDto.getTemplateName());
            if(StringUtils.isNotBlank(templateDto.getIsPublic().toString()))
                template.setIsPublic(templateDto.getIsPublic());
            templateRepository.save(template);
            ResponseStatus responseSuccess = ResponseStatus.OK;
            return new ResponseDto<Template>(responseSuccess,template);
        }
        else{
            ResponseStatus responseFail = ResponseStatus.NOT_FOUND;
            return new ResponseDto<Template>(responseFail,null);
        }

    } //수정시 변경사항을 controller에서 적용한 후 저장

    public Template templateView(String templateId){
        return templateRepository.findById(templateId).get();
    }

    public ResponseStatus templateDelete(String templateId){
        Optional<Template> optTemplateDto = templateRepository.findById(templateId);

        if(optTemplateDto.isEmpty()){
            return ResponseStatus.NOT_FOUND;
        }

        templateRepository.deleteById(templateId);
        return ResponseStatus.OK;

    }
}
