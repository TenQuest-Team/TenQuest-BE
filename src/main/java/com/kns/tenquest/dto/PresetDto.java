package com.kns.tenquest.dto;

import com.kns.tenquest.entity.Preset;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class PresetDto implements DataTransferObject<Preset> {
    public Long presetId;
    public String presetName;
    public PresetDto(Long presetId, String presetName){
        this.presetId = presetId;
        this.presetName = presetName;
    }

    public PresetDto(Preset preset){
        this.presetId = preset.getPresetId();
        this.presetName = preset.getPresetName();
    }

    @Override
    public Preset toEntity(){
        return Preset.builder().presetId(this.presetId).presetName(this.presetName).build();
    }

    @Override
    public DataTransferObject<Preset> toDto(Preset preset) {
        return new PresetDto(preset.getPresetId(),preset.getPresetName());
    }
}
