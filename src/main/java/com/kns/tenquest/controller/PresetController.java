package com.kns.tenquest.controller;

import com.kns.tenquest.entity.Preset;
import com.kns.tenquest.service.PresetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class PresetController {
    @Autowired
    PresetService presetService;

    @GetMapping("/presets")
    public String presetView(Model model){
        model.addAttribute("presetList", presetService.presetList());
        return "preset_list";
    }
    @GetMapping("/presets/insert")
    public String presetInsert(){
        return "preset_insert";
    }

    @PostMapping("/presets")
    public String presetPost(Preset preset){
        //html에서 Post받은 preset 이름을 받아서 get preset_view로 이동
        presetService.presetPost(preset);
        return "redirect:/presets";
    }
    @GetMapping("/presets/view")
    //PK를 소유한 하나의 레코드 정보로 들어가기
    public String presetView(Model model, Long id){
        model.addAttribute("preset",presetService.presetView(id));
        return "preset_view";
    }

    @GetMapping("/presets/delete")
    public String presetDelete(Long id){
        presetService.presetDelete(id);

        return "redirect:/presets";
    }

}
