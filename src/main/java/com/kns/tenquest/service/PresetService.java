package com.kns.tenquest.service;

import com.kns.tenquest.DtoList;
import com.kns.tenquest.RequestWrapper.PresetWrapper;
import com.kns.tenquest.dto.PresetDocDto;
import com.kns.tenquest.dto.PresetDto;
import com.kns.tenquest.entity.Preset;
import com.kns.tenquest.entity.PresetDoc;
import com.kns.tenquest.repository.PresetDocRepository;
import com.kns.tenquest.repository.PresetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class PresetService {

    @Autowired
    PresetRepository presetRepository;

    @Autowired
    PresetDocRepository presetDocRepository;
    public DtoList<PresetDto> getAllPresets(){
        DtoList<PresetDto> presetList = new DtoList<>(presetRepository.findAll());
        return presetList;
    }

    public PresetWrapper getPreset(String presetId){
        Optional<Preset> optPreset = presetRepository.findById(presetId);
        if(optPreset.isEmpty()){
            return null;
        }
        PresetDto presetDto = new PresetDto(optPreset.get());
        DtoList<PresetDocDto> presetDocList = new DtoList<>(presetDocRepository.findAllByPresetId(presetId));
        PresetWrapper presetWrapper = new PresetWrapper(presetDto,presetDocList);
        return presetWrapper;
    }

    @Transactional
    public PresetWrapper createPreset(PresetWrapper presetWrapper){
        PresetDto presetDto = presetWrapper.getPresetDto();
        presetDto.setPresetId(UUID.randomUUID().toString().replace("-",""));
        String thisPresetId = presetDto.presetId;
        presetRepository.save(presetDto.toEntity());

        List<PresetDocDto> presetDocList = new DtoList<>(presetWrapper.getPresetDocList());
        for(PresetDocDto element : presetDocList){
            element.setPresetId(thisPresetId);
            element.setPresetDocId(UUID.randomUUID().toString().replace("-",""));
            presetDocRepository.save(element.toEntity());
        }
        return presetWrapper;
    }

    public PresetDto deletePreset(String presetId){
        PresetDto presetDto = new PresetDto(presetRepository.findById(presetId).get());
        presetRepository.deleteById(presetId);

        return presetDto;

    }

    public DtoList<PresetDto> deleteAll(){
        List<PresetDoc> presetDocList = new DtoList<PresetDoc>(presetDocRepository.findAll());
        DtoList<PresetDto> presetDtoList = new DtoList<PresetDto>(presetRepository.findAll());
        presetDocRepository.deleteAll();
        presetRepository.deleteAll();
        return presetDtoList;
    }
}
