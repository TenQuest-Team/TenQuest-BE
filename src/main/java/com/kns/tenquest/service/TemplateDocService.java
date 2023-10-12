package com.kns.tenquest.service;

import com.kns.tenquest.DtoList;
import com.kns.tenquest.dto.ResponseDto;
import com.kns.tenquest.dto.ServiceResult;
import com.kns.tenquest.dto.TemplateDocDto;
import com.kns.tenquest.dto.TemplateDto;
import com.kns.tenquest.repository.TemplateDocRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TemplateDocService {

    private final TemplateDocRepository templateDocRepository;

    public ServiceResult getAllTemplateDocs(){

        DtoList<TemplateDocDto> templateDocDtoList = new DtoList<>(templateDocRepository.findAll());
        return new ServiceResult().success().data(templateDocDtoList);
    }

//    public ResponseDto<TemplateDocDto> createTemplateDoc (DtoList<TemplateDocDto> )


}
