package com.kns.tenquest.controller;

import com.kns.tenquest.DtoList;
import com.kns.tenquest.dto.*;
import com.kns.tenquest.entity.Answer;
import com.kns.tenquest.entity.Question;
import com.kns.tenquest.entity.Replyer;
import com.kns.tenquest.service.*;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@SuppressWarnings("unchecked")
@RequestMapping("/view")
@Controller
@RequiredArgsConstructor
public class ViewController {

    private final AnswerService answerService;
    private final MemberService memberService;
    private final ReplyerService replyerService;
    private final TemplateService templateService;
    private final PresetService presetService;
    private final TemplateDocService templateDocService;
    private final QuestionService questionService;

    @GetMapping("/members")
    public String memberView(Model model) {
        // Temporarily implemented. Just for test.
        ServiceResult sr = memberService.getAllMembers();
        DtoList<MemberDto> memberDtoList = (DtoList<MemberDto>)sr.getData();
        model.addAttribute("memberList", memberDtoList);
        return "member_view";
    }

    @GetMapping("/answers")
    public String answerView(Model model) {
        // Temporarily implemented. Just for test.
        ServiceResult sr = answerService.getAllAnswers();
        DtoList<AnswerDto> answerList = (DtoList<AnswerDto>)sr.getData();
        model.addAttribute("answerList", answerList);
        return "answer_view";
    }

    @GetMapping("/replyers")
    public String replyerView(Model model) {
        List<Replyer> replyerList = replyerService.getAllReplyers();
        model.addAttribute("replyerList", replyerList);
        return "replyer_view";
    }

    @GetMapping("/templates")
    public String templateView(Model model) {
        DtoList<TemplateDto> templateList = templateService.getAllTemplates();
        model.addAttribute("templateList", templateList);
        return "template_view";
    }

    @GetMapping("/template-docs")
    public String templateDocView(Model model) {
        DtoList<TemplateDocDto> templateDocList = templateDocService.getAllTemplateDocs();
        model.addAttribute("templateDocList", templateDocList);
        return "template_doc_view";
    }

    @RequestMapping(value = "/questions", method = RequestMethod.GET)
    public String questionView(@NotNull Model model) {

        List<Question> questionList = questionService.getAllQuestions();
        model.addAttribute("questionList", questionList);
        return "question_view";

    }

    @GetMapping("/presets")
    //PK를 소유한 하나의 레코드 정보로 들어가기
    public String presetView(Model model) {
        DtoList<PresetDto> presetList = presetService.getAllPresets();
        model.addAttribute("presetList", presetList);
        return "preset_view";
    }


}