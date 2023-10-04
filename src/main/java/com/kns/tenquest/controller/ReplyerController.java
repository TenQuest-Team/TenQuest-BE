package com.kns.tenquest.controller;

import com.kns.tenquest.ENV;
import com.kns.tenquest.dto.ReplyerDto;
import com.kns.tenquest.dto.ResponseDto;
import com.kns.tenquest.entity.Replyer;
import com.kns.tenquest.response.Response_Deprecated;
import com.kns.tenquest.response.ResponseStatus;
import com.kns.tenquest.service.ReplyerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping(ENV.API_PREFIX)
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
@RequiredArgsConstructor
public class ReplyerController {

    private final ReplyerService replyerService;

    @ResponseBody
    @GetMapping("/replyers")
    public List<Replyer> apiGetReplyers(){
        return replyerService.getAllReplyers();
    }

    @GetMapping("/replyers/{replyerid}")
    public Response_Deprecated<ReplyerDto> apiGetReplyerByReplyerId(@PathVariable(value = "replyerid")int replyerId){
        ReplyerDto replyerDto = replyerService.getReplyerByReplyerId(replyerId);
        ResponseStatus responseStatus = ResponseStatus.OK;

        if (replyerDto.getReplyerId()==-1){
            responseStatus = ResponseStatus.NOT_FOUND;
        }
        return new ResponseDto<ReplyerDto>(responseStatus,replyerDto).toResponse();
    }


}
