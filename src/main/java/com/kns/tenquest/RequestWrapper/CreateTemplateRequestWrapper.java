package com.kns.tenquest.RequestWrapper;

import com.kns.tenquest.DtoList;
import com.kns.tenquest.dto.TemplateDocDto;
import com.kns.tenquest.dto.TemplateDto;
import lombok.Getter;


@Getter
public class CreateTemplateRequestWrapper {
    private TemplateDto templateDto;
    private DtoList<TemplateDocDto> templateDocDtoList;

}
