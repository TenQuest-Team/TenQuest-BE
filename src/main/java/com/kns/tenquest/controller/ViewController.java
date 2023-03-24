package com.kns.tenquest.controller;

import com.kns.tenquest.DtoList;
import com.kns.tenquest.dto.MemberDto;
import com.kns.tenquest.entity.Answer;
import com.kns.tenquest.entity.Replyer;
import com.kns.tenquest.service.AnswerService;
import com.kns.tenquest.service.MemberService;
import com.kns.tenquest.service.ReplyerService;
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
}
