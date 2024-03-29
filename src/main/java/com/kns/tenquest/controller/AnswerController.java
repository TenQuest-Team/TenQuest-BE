package com.kns.tenquest.controller;
import com.kns.tenquest.DtoList;
import com.kns.tenquest.dto.AnswerDto;

import com.kns.tenquest.dto.ResponseDto;
import com.kns.tenquest.requestBody.MultipleAnswerRequestBody;
import com.kns.tenquest.requestBody.SingleAnswerCreateRequestBody;
import com.kns.tenquest.response.Response;
import com.kns.tenquest.response.ResponseStatus;
import com.kns.tenquest.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import com.kns.tenquest.ENV;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(ENV.API_PREFIX)
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AnswerController {
    @Autowired
    AnswerService answerService;
    @GetMapping("/answers")
    public List apiGetAnswers(){
       //return answerService.getAllAnswers().toResponse();
        return answerService.getAllAnswers();
    }

    @GetMapping("/answers/docId")
    public Response<AnswerDto> apiGetAnswerByDocId(@RequestParam("value") Long docId){
        DtoList<AnswerDto> answerDtoList = answerService.getAnswerByDocId(docId);
        ResponseStatus responseStatus = ResponseStatus.OK;

        /* if result list is empty, Set status NOT_FOUND */
        if (answerDtoList.isEmpty()) responseStatus = ResponseStatus.NOT_FOUND;

        return answerDtoList.toResponse(responseStatus);
    }


    @GetMapping("/answers/replyerNames/templateId")
    public Response<List> apiGetAnswersReplyerNameListByTemplateId(@RequestParam("value") String templateId){

        return new ResponseDto<List>
                (ResponseStatus.OK,
                        answerService
                                .getReplyerNameListByTemplateId(templateId))
                .toResponse();
    }
    @GetMapping("/answers/replyerId")
    public Response<List> apiGetAnswersByReplyerId(@RequestParam("value") int replyerId){

        return new ResponseDto<List>
                (ResponseStatus.OK,
                        answerService
                                .getAnswerListByReplyerId(replyerId))
                .toResponse();
    }

    @PostMapping("/answers")
    public Response<Object> apiCreateAnswer(@RequestBody MultipleAnswerRequestBody multipleAnswerRequestBody){
        ResponseStatus responseStatus = ResponseStatus.CREATE_DONE;
        boolean result = answerService.createAnswer(multipleAnswerRequestBody);
        if (result){
            return new ResponseDto(ResponseStatus.OK).toResponse();
        }
        else{
            return new ResponseDto(ResponseStatus.CREATE_FAIL, "DocId Not exists!").toResponse();
        }
    }

    @DeleteMapping("/answers")
    public Response<Object> apiDeleteAllAnswer(){
        ResponseStatus responseStatus = ResponseStatus.OK;
        boolean result = answerService.DeleteAllAnswer();
        if(result){
            return new ResponseDto(ResponseStatus.OK).toResponse();
        }
        else{
            return new ResponseDto(ResponseStatus.NOT_ACCEPTABLE).toResponse();
        }
    }

}
