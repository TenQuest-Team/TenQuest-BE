package com.kns.tenquest.RequestWrapper;

import com.kns.tenquest.DtoList;
import com.kns.tenquest.dto.PresetDocDto;
import com.kns.tenquest.dto.PresetDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class PresetWrapper {

    private PresetDto presetDto;
    private List<PresetDocDto> presetDocList;

    public PresetWrapper(PresetDto presetDto, List<PresetDocDto> presetDocList){
        this.presetDto = presetDto;
        this.presetDocList = presetDocList;
    }

}
