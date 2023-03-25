package com.kns.tenquest.controller;
import com.kns.tenquest.entity.Answer;
import com.kns.tenquest.entity.Replyer;
import com.kns.tenquest.requestBody.AnswerCreateRequestBody;
import com.kns.tenquest.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.kns.tenquest.ENV;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(ENV.API_PREFIX)
@RestController
public class AnswerController {
    @Autowired
    AnswerService answerService;

    @GetMapping("/answers/docId")
    public List<Answer> apiGetAnswerByDocId(String docId){
        return answerService.getAnswerByDocId(docId);
    }

    @PostMapping("/answers")
    public void apiCreateAnswer(@RequestBody AnswerCreateRequestBody answerCreateRequestBody){
        //answerService.createAnswer(answerCreateRequestBody);
    }
}
