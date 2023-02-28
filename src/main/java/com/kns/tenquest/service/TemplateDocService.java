package com.kns.tenquest.service;


import com.kns.tenquest.entity.TemplateDoc;
import com.kns.tenquest.repository.TemplateDocRepository;
import com.kns.tenquest.response.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TemplateDocService {
    @Autowired
    TemplateDocRepository templateDocRepository;


    public int templateDocDelete(String templateId){
        List<TemplateDoc> optTemplateDocDto = templateDocRepository.findAllTemplateDocByTemplate_TemplateId(templateId);
        if(optTemplateDocDto.isEmpty()){
            return ResponseStatus.NOT_FOUND.getCode();
        }
        templateDocRepository.deleteAllTemplateDocByTemplate_TemplateId(templateId);
        return ResponseStatus.OK.getCode();
    }

}
