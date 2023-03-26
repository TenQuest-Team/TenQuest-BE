package com.kns.tenquest.service;

import com.kns.tenquest.DtoList;
import com.kns.tenquest.dto.ResponseDto;
import com.kns.tenquest.dto.TemplateDocDto;
import com.kns.tenquest.dto.TemplateDto;
import com.kns.tenquest.repository.TemplateDocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemplateDocService {
    @Autowired
    TemplateDocRepository templateDocRepository;

    public DtoList<TemplateDocDto> getAllTemplateDocs(){
        DtoList<TemplateDocDto> templateDocDtoList = new DtoList<>(templateDocRepository.findAll());
        return templateDocDtoList;
    }

//    public ResponseDto<TemplateDocDto> createTemplateDoc (DtoList<TemplateDocDto> )


}
