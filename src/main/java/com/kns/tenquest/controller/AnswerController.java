package com.kns.tenquest.controller;
import com.kns.tenquest.DtoList;
import com.kns.tenquest.dto.AnswerDto;
import com.kns.tenquest.entity.Answer;
import com.kns.tenquest.entity.Replyer;
import com.kns.tenquest.requestBody.AnswerCreateRequestBody;
import com.kns.tenquest.response.Response;
import com.kns.tenquest.response.ResponseStatus;
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
    @GetMapping("/answers")
    public Response<AnswerDto> apiGetAnswers(){
        return answerService.getAllAnswers().toResponse();
    }

    @GetMapping("/answers/docId")
    public Response<AnswerDto> apiGetAnswerByDocId(@RequestParam("value") String docId){
        DtoList<AnswerDto> answerDtoList = answerService.getAnswerByDocId(docId);
        ResponseStatus responseStatus = ResponseStatus.OK;

        /* if result list is empty, Set status NOT_FOUND */
        if (answerDtoList.isEmpty()) responseStatus = ResponseStatus.NOT_FOUND;

        return answerDtoList.toResponse(responseStatus);
    }

    @PostMapping("/answers")
    public Response<AnswerDto> apiCreateAnswer(@RequestBody AnswerCreateRequestBody answerCreateRequestBody){
        ResponseStatus responseStatus = ResponseStatus.CREATE_DONE;
        AnswerDto answerDto = answerService.createAnswer(answerCreateRequestBody);

        if(answerDto.answerId == null){
            responseStatus = ResponseStatus.CREATE_FAIL;
        }
        return answerDto.toResponse(responseStatus);
    }


}
