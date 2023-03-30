package com.kns.tenquest.controller;

import com.kns.tenquest.DtoList;
import com.kns.tenquest.dto.MemberDto;
import com.kns.tenquest.dto.PresetDto;
import com.kns.tenquest.dto.TemplateDocDto;
import com.kns.tenquest.dto.TemplateDto;
import com.kns.tenquest.entity.Answer;
import com.kns.tenquest.entity.PresetDoc;
import com.kns.tenquest.entity.Replyer;
import com.kns.tenquest.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/view")
@Controller
public class ViewController {

    @Autowired
    AnswerService answerService;
    @Autowired
    MemberService memberService;
    @Autowired
    ReplyerService replyerService;

    @Autowired
    TemplateService templateService;

    @Autowired
    PresetService presetService;

    @Autowired
    TemplateDocService templateDocService;


    @GetMapping("/members")
    public String memberView(Model model){
        // Temporarily implemented. Just for test.
        DtoList<MemberDto> memberDtoList = memberService.getAllMembers();
        model.addAttribute("memberList", memberDtoList);
        return "member_view";
    }

    @GetMapping("/answers")
    public String answerView(Model model){
        // Temporarily implemented. Just for test.
        List<Answer> answerList = answerService.getAllAnswers();
        model.addAttribute("answerList", answerList);
        return "answer_view";
    }

    @GetMapping("/replyers")
    public String replyerView(Model model){
        List<Replyer> replyerList = replyerService.getAllReplyers();
        model.addAttribute("replyerList", replyerList);
        return "replyer_view";
    }

    @GetMapping("/templates")
    public String templateView(Model model){
        DtoList<TemplateDto> templateList = templateService.getAllTemplates();
        model.addAttribute("templateList", templateList);
        return "template_view";
    }

    @GetMapping("/template-docs")
    public String templateDocView(Model model){
        DtoList<TemplateDocDto> templateDocList = templateDocService.getAllTemplateDocs();
        model.addAttribute("templateDocList", templateDocList);
        return "template_doc_view";
    }

    @GetMapping("/presets")
    //PK를 소유한 하나의 레코드 정보로 들어가기
    public String presetView(Model model){
        DtoList<PresetDto> presetList = presetService.getAllPresets();
        model.addAttribute("presetList",presetList);
        return "preset_view";
    }
}
