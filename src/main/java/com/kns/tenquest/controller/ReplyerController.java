package com.kns.tenquest.controller;

import com.kns.tenquest.ENV;
import com.kns.tenquest.dto.ReplyerDto;
import com.kns.tenquest.dto.ResponseDto;
import com.kns.tenquest.response.Response;
import com.kns.tenquest.response.ResponseStatus;
import com.kns.tenquest.service.ReplyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(ENV.API_PREFIX)
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
public class ReplyerController {
    @Autowired
    ReplyerService replyerService;

    @GetMapping("/replyers/{replyerid}")

    public Response<ReplyerDto> apiGetReplyerByReplyerId(@PathVariable(value = "replyerid")int replyerId){
        ReplyerDto replyerDto = replyerService.getReplyerByReplyerId(replyerId);
        ResponseStatus responseStatus = ResponseStatus.OK;

        if (replyerDto.getReplyerId()==-1){
            responseStatus = ResponseStatus.NOT_FOUND;
        }
        return new ResponseDto<ReplyerDto>(responseStatus,replyerDto).toResponse();

    }
}
