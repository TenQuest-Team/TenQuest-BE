package com.kns.tenquest.service;

import com.kns.tenquest.entity.Template;
import com.kns.tenquest.repository.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateService {
    @Autowired
    TemplateRepository templateRepository;

    public List<Template> templateList(){
        return templateRepository.findAll();
    }

    public void templatePost(Template template){
        templateRepository.save(template);
    }

    public Template templateView(String id){
        return templateRepository.findById(id).get();
    }

    public  void templateDelete(String id){
        templateRepository.deleteById(id);
    }
}
