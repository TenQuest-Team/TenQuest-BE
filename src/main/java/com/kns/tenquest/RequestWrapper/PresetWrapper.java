package com.kns.tenquest.RequestWrapper;

import com.kns.tenquest.DtoList;
import com.kns.tenquest.dto.PresetDocDto;
import com.kns.tenquest.dto.PresetDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PresetWrapper {

    PresetDto presetDto;
    DtoList<PresetDocDto> presetDocList;

    public PresetWrapper(PresetDto presetDto, DtoList<PresetDocDto> presetDocList){
        this.presetDto.presetId = presetDto.presetId;
        this.presetDto.presetName = presetDto.presetName;
        this.presetDocList = presetDocList;
    }

}
