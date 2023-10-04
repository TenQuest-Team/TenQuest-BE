package com.kns.tenquest.controller;

import com.kns.tenquest.DtoList;
import com.kns.tenquest.ENV;
import com.kns.tenquest.RequestWrapper.PresetWrapper;
import com.kns.tenquest.dto.PresetDto;
import com.kns.tenquest.dto.ResponseDto;
import com.kns.tenquest.entity.Preset;
import com.kns.tenquest.entity.PresetDoc;
import com.kns.tenquest.response.Response;
import com.kns.tenquest.response.ResponseStatus;
import com.kns.tenquest.service.PresetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(ENV.API_PREFIX + "/presets")
@RequiredArgsConstructor
public class PresetController {

    private final PresetService presetService;

    @GetMapping("")
    public Response<DtoList<PresetDto>> apiGetAllPresets(){
        ResponseStatus responseStatus = ResponseStatus.OK;
        DtoList<PresetDto> presetDtoList = presetService.getAllPresets();
        return new ResponseDto<DtoList<PresetDto>> (responseStatus,presetDtoList).toResponse();
    }

    @GetMapping("/preset-id")
    public Response<PresetWrapper> apiGetPreset(@RequestParam("value") String presetId){
        ResponseStatus responseStatus = ResponseStatus.OK;
        PresetWrapper presetWrapper = presetService.getPreset(presetId);
        if(presetWrapper == null){
            responseStatus = ResponseStatus.NOT_FOUND;
        }
        return new ResponseDto<PresetWrapper>(responseStatus,presetWrapper).toResponse();
    }

    @PostMapping("")
    public Response<PresetWrapper> apiCreatePreset(@RequestBody PresetWrapper presetWrapper){
        ResponseStatus responseStatus = ResponseStatus.CREATE_DONE;
        PresetWrapper createdPreset = presetService.createPreset(presetWrapper);
        return new ResponseDto<PresetWrapper>(responseStatus,createdPreset).toResponse();
    }

    @DeleteMapping("/{presetId}")
    public Response<PresetDto> apiDeletePreset(@PathVariable("presetId")String presetId){
        ResponseStatus responseStatus = ResponseStatus.OK;
        PresetDto deletedPreset = presetService.deletePreset(presetId);
        return new ResponseDto<PresetDto>(responseStatus,deletedPreset).toResponse();
    }

    @DeleteMapping("")
    public Response<DtoList<PresetDto>> deleteAll(){
        DtoList<PresetDto> deletedPresetDto = presetService.deleteAll();
        ResponseStatus responseStatus = ResponseStatus.OK;
        if(deletedPresetDto == null){
            responseStatus = ResponseStatus.NOT_FOUND;
        }
        return new ResponseDto<DtoList<PresetDto>>(responseStatus,deletedPresetDto).toResponse();
    }


}
