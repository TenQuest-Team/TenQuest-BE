package com.kns.tenquest.controller;
import com.kns.tenquest.DtoList;
import com.kns.tenquest.dto.AnswerDto;

import com.kns.tenquest.dto.ResponseDto;
import com.kns.tenquest.dto.ServiceResult;
import com.kns.tenquest.requestBody.MultipleAnswerRequestBody;
import com.kns.tenquest.response.Response;
import com.kns.tenquest.response.Response_Deprecated;
import com.kns.tenquest.response.ResponseStatus;
import com.kns.tenquest.service.AnswerService;
import lombok.RequiredArgsConstructor;
import com.kns.tenquest.ENV;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(ENV.API_PREFIX)
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    @GetMapping("/answers")
    public Response apiGetAnswers(){
        ServiceResult sr = answerService.getAllAnswers();

        return sr.isFailed() ?
                new Response().BadRequest() : new Response().Ok().data(sr.getData());
    }

    @GetMapping("/answers/docId")
    public Response apiGetAnswerByDocId(@RequestParam("value") Long docId){
        ServiceResult sr = answerService.getAnswerByDocId(docId);

        return sr.isFailed() ?
                new Response().BadRequest() : new Response().Ok(sr.getData());
    }

    @GetMapping("/answers/replyerNames/templateId")
    public Response apiGetAnswersReplyerNameListByTemplateId(@RequestParam("value") String templateId){
        ServiceResult sr = answerService.getReplyerNameListByTemplateId(templateId);
        return sr.isFailed() ?
                new Response().BadRequest() : new Response().Ok(sr.getData());
    }

    @GetMapping("/answers/replyerId")
    public Response apiGetAnswersByReplyerId(@RequestParam("value") int replyerId){
        ServiceResult sr = answerService.getAnswerListByReplyerId(replyerId);
        return sr.isFailed() ?
                new Response().BadRequest() : new Response().Ok(sr.getData());
    }

    @PostMapping("/answers")
    public Response apiCreateAnswer(@RequestBody MultipleAnswerRequestBody multipleAnswerRequestBody){
        ServiceResult sr = answerService.createAnswer(multipleAnswerRequestBody);

        return sr.isFailed() ?
                new Response().BadRequest().message("Answer creation failed")
                : new Response().Ok(sr.getData()).message("Answer Created");
    }

    @DeleteMapping("/answers")
    public Response apiDeleteAllAnswer(){
        ServiceResult sr = answerService.DeleteAllAnswer();
        return sr.isFailed() ?
                new Response().BadRequest().message("Answer deletion failed")
                : new Response().Ok().message("Answer Deleted");
    }

}
