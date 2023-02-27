package com.kns.tenquest.service;

import com.kns.tenquest.entity.Preset;
import com.kns.tenquest.repository.PresetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PresetService {

    @Autowired
    PresetRepository presetRepository;

    public List<Preset> presetList(){
        return presetRepository.findAll();
    }

    public void presetPost(Preset preset){
        presetRepository.save(preset);
    }

    public  Preset presetView(Long id){
        return presetRepository.findById(id).get();
    }

    public void presetDelete(Long id) {
        presetRepository.deleteById(id);
    }
}
