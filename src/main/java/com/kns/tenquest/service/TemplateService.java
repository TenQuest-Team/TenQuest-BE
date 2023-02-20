package com.kns.tenquest.service;

import com.kns.tenquest.entity.Member;
import com.kns.tenquest.entity.Template;
import com.kns.tenquest.repository.MemberRepository;
import com.kns.tenquest.repository.TemplateRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemplateService {
    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private MemberRepository memberRepository;

    public void createTemplate(Template template){
        Template newTemplate = new Template();
        BeanUtils.copyProperties(template,newTemplate);
        return


    }
}
