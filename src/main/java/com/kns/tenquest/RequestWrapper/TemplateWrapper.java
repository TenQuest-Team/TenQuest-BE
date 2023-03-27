package com.kns.tenquest.RequestWrapper;

import com.kns.tenquest.DtoList;
import com.kns.tenquest.dto.TemplateDocDto;
import com.kns.tenquest.dto.TemplateDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class TemplateWrapper {
    private TemplateDto templateDto;
    private List<TemplateDocDto> templateDocList;

    public TemplateWrapper(){
    }//기본생성자가 없다면서 에러가 뜨길래 추가해줬음
    public TemplateWrapper(TemplateDto templateDto , List<TemplateDocDto> templateDocList){
        this.templateDto = templateDto;
        this.templateDocList = templateDocList;
    }
}
//template 생성시 wrapping 되는 클래스
