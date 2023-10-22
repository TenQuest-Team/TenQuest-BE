package com.kns.tenquest.controller;

import com.kns.tenquest.ENV;
import com.kns.tenquest.dto.ReplyerDto;
import com.kns.tenquest.dto.ResponseDto;
import com.kns.tenquest.dto.ServiceResult;
import com.kns.tenquest.entity.Replyer;
import com.kns.tenquest.response.Response_Deprecated;
import com.kns.tenquest.response.Response;
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
    public Response apiGetReplyers(){
        
        ServiceResult sr = replyerService.getAllReplyers();
        
        return sr.isFailed() ? 
            new Response().BadRequest() :
            new Response().Ok().data(sr.getData());
    }

    @GetMapping("/replyers/{replyerid}")
    public Response apiGetReplyerByReplyerId(@PathVariable(value = "replyerid")int replyerId){
       
        ServiceResult sr = replyerService.getReplyerByReplyerId(replyerId);
       
        return sr.isFailed() ? 
            new Response().BadRequest() :
            new Response().Ok().data(sr.getData());
    }

}
