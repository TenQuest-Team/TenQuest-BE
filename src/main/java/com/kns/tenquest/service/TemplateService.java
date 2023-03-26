package com.kns.tenquest.service;

import com.kns.tenquest.DtoList;
import com.kns.tenquest.RequestWrapper.CreateTemplateRequestWrapper;
import com.kns.tenquest.dto.ResponseDto;
import com.kns.tenquest.dto.TemplateDto;
import com.kns.tenquest.entity.Member;
import com.kns.tenquest.entity.Template;
import com.kns.tenquest.repository.MemberRepository;
import com.kns.tenquest.repository.TemplateRepository;
import com.kns.tenquest.response.ResponseStatus;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class TemplateService {
    @Autowired
    TemplateRepository templateRepository;

    @Autowired
    MemberRepository memberRepository;

    public DtoList<TemplateDto> getAllTemplates(){
        DtoList<TemplateDto> TemplateDtoList = new DtoList<>(templateRepository.findAll());
        return TemplateDtoList;
    }
    public DtoList<TemplateDto> getAllTemplatesByTemplateOwner(String templateOwner){
        DtoList<TemplateDto> templateDtoList = new DtoList<>(templateRepository.findAllByTemplateOwner(templateOwner));
        return templateDtoList;
    }
    public TemplateDto getTemplateByTemplateName(String templateName){
        return new TemplateDto(templateRepository.findTemplateByTemplateName(templateName).orElse(new Template()));
    }
    public CreateTemplateRequestWrapper createTemplate(CreateTemplateRequestWrapper requestWrapper, String memberId) {
        Optional<Member> nullableMember = memberRepository.findById(memberId);
        if(nullableMember.isEmpty()){
            throw new NoSuchElementException("존재하지 않는 사용자 입니다.");
        }
        Optional<Template> optTemplate = templateRepository.findTemplateByTemplateNameAndTemplateOwner(requestWrapper.getTemplateDto().templateName,memberId);
        if (optTemplate.isEmpty()) {
            try {
                String thisTemplateId = UUID.randomUUID().toString().replace("-", "");
                requestWrapper.getTemplateDto().setCreatedAt(LocalDateTime.now());
                requestWrapper.getTemplateDto().setTemplateId(thisTemplateId);
                requestWrapper.getTemplateDto().setTemplateOwner(memberId);
                requestWrapper.getTemplateDto().setIsPublic(true);
                templateRepository.save(requestWrapper.getTemplateDto().toEntity());
                return requestWrapper;
            } catch (RuntimeException e) {
                e.printStackTrace();
                throw new RuntimeException("template 생성 중 오류가 발생하였습니다.");
            }
        }
        return null;
    } //처음 create 시 생성값 주기

    public TemplateDto templateUpdate(String templateId, TemplateDto templateDto) {
        Optional<Template> optTemplate = templateRepository.findById(templateId);
        if(optTemplate.isPresent()){
            TemplateDto updatingTemplateDto = new TemplateDto(optTemplate.get());
            if(StringUtils.isNotBlank(templateDto.getTemplateName()))
                updatingTemplateDto.setTemplateName(templateDto.getTemplateName());
            if(StringUtils.isNotBlank(templateDto.getIsPublic().toString()))
                updatingTemplateDto.setIsPublic(templateDto.getIsPublic());
            templateRepository.save(updatingTemplateDto.toEntity());
            return updatingTemplateDto;
        }
        else{
            return null;
        }

    } //수정시 변경사항을 controller에서 적용한 후 저장

    public Template templateView(String templateId){
        return templateRepository.findById(templateId).get();
    }

    public TemplateDto templateDelete(String templateId){
        Optional<Template> optTemplate = templateRepository.findById(templateId);

        if(optTemplate.isEmpty()){
            return null;
        }
        Template deletingTemplate = optTemplate.get();
        TemplateDto deletedTemplateDto = new TemplateDto(deletingTemplate);
        templateRepository.deleteById(templateId);
        return deletedTemplateDto;

    }
}
