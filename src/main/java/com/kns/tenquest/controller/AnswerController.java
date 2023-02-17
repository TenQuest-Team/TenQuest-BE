package com.kns.tenquest.controller;

import com.kns.tenquest.entity.Answer;
import com.kns.tenquest.entity.Member;
import com.kns.tenquest.service.AnswerService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AnswerController {
    @Service
    AnswerService answerService;

    @GetMapping("/view/answers")
    public String answerView(Model model){
        // Temporarily implemented. Just for test.
        List<Answer> answerList = answerService.getAllAnswers();
        model.addAttribute("answerList", answerList);
        return "answer_view";
    }
}
