package com.kns.tenquest.controller;

import com.kns.tenquest.DtoList;
import com.kns.tenquest.ENV;
import com.kns.tenquest.RequestWrapper.PresetWrapper;
import com.kns.tenquest.dto.PresetDto;
import com.kns.tenquest.dto.ResponseDto;
import com.kns.tenquest.entity.Preset;
import com.kns.tenquest.response.Response;
import com.kns.tenquest.response.ResponseStatus;
import com.kns.tenquest.service.PresetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(ENV.API_PREFIX)
public class PresetController {
    @Autowired
    PresetService presetService;

    @ResponseBody
    @GetMapping("/presets")
    public Response<DtoList<PresetDto>> apiGetAllPresets(){
        ResponseStatus responseStatus = ResponseStatus.OK;
        DtoList<PresetDto> presetDtoList = presetService.getAllPresets();
        return new ResponseDto<DtoList<PresetDto>> (responseStatus,presetDtoList).toResponse();
    }

    @ResponseBody
    @GetMapping("/presets/preset-id")
    public Response<PresetWrapper> apiGetPreset(@RequestParam("value") Long presetId){
        ResponseStatus responseStatus = ResponseStatus.OK;
        PresetWrapper presetWrapper = presetService.getPreset(presetId);
        if(presetWrapper == null){
            responseStatus = ResponseStatus.NOT_FOUND;
        }
        return new ResponseDto<PresetWrapper>(responseStatus,presetWrapper).toResponse();
    }

}
