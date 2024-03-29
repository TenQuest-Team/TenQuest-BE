package com.kns.tenquest.RequestWrapper;

import com.kns.tenquest.DtoList;
import com.kns.tenquest.dto.TemplateDocDto;
import com.kns.tenquest.dto.TemplateDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class TemplateWrapper {
    private TemplateDto templateDto;
    private String OwnerName;
    private List<TemplateDocDto> templateDocList;

    public TemplateWrapper(TemplateDto templateDto ,String ownerName, List<TemplateDocDto> templateDocList){
        this.OwnerName = ownerName;
        this.templateDto = templateDto;
        this.templateDocList = templateDocList;
    }
}
//template 생성시 wrapping 되는 클래스
