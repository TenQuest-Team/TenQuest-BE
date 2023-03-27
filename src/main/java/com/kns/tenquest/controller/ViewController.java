package com.kns.tenquest.controller;

import com.kns.tenquest.DtoList;
import com.kns.tenquest.dto.CategoryDto;
import com.kns.tenquest.dto.MemberDto;
import com.kns.tenquest.dto.TemplateDocDto;
import com.kns.tenquest.dto.TemplateDto;
import com.kns.tenquest.entity.Answer;
import com.kns.tenquest.entity.Question;
import com.kns.tenquest.entity.Replyer;
import com.kns.tenquest.service.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    TemplateDocService templateDocService;

    @Autowired
    QuestionService questionService;

    @Autowired
    CategoryService categoryService;


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


    @RequestMapping(value = "/questions", method = RequestMethod.GET)
    public String questionView(@NotNull Model model){

        List<Question> questionList = questionService.getAllQuestions();
        model.addAttribute("questionList", questionList);
        return "question_view";

    }

    //카테고리 객체 이름 & id  전체를 html 이용하여 화면에 표시하기 : GET  : 데이터 가져와서 뿌려주기
    @RequestMapping(value="/categories",method= RequestMethod.GET)
    public String categoryView(@NotNull Model model){  //Model 객체.. 스프링이 알아서 만들어줌
        // Temporarily implemented. Just for test.
        List<CategoryDto> categoryList = categoryService.getAllCategories();
        model.addAttribute("categoryList", categoryList); // 바로윗줄의 객체를 추가 ..
        return "category_view"; // html 페이지 인듯
    }
}
