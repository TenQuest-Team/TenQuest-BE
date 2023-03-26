package com.kns.tenquest.RequestWrapper;

import com.kns.tenquest.DtoList;
import com.kns.tenquest.dto.TemplateDocDto;
import com.kns.tenquest.dto.TemplateDto;
import lombok.Getter;

import java.util.List;


@Getter
public class CreateTemplateRequestWrapper {
    private TemplateDto templateDto;
    private List<TemplateDocDto> templateDocList;

}
//template 생성시 wrapping 되는 클래스
