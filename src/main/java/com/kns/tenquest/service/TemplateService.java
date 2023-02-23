package com.kns.tenquest.service;

import com.kns.tenquest.entity.Template;
import com.kns.tenquest.repository.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static java.lang.Boolean.TRUE;

@Service
public class TemplateService {
    @Autowired
    TemplateRepository templateRepository;

    public List<Template> templateList(){
        return templateRepository.findAll();
    }

    public void templatePost(Template template){
        template.setCreatedAt(LocalDateTime.now());
        template.setTemplateId(UUID.randomUUID().toString().replace("-",""));
//        template.setTemplateOwner(UUID.randomUUID().toString().replace("-",""));
        template.setIsPublic(TRUE);
        templateRepository.save(template);
    } //처음 create 시 생성값 주기

    public void templateSave(Template template){
        templateRepository.save(template);
    } //수정시 변경사항을 controller에서 적용한 후 저장

    public Template templateView(String id){
        return templateRepository.findById(id).get();
    }

    public  void templateDelete(String id){
        templateRepository.deleteById(id);
    }
}
