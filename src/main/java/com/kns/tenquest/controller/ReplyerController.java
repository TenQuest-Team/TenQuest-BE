package com.kns.tenquest.controller;

import com.kns.tenquest.dto.ReplyerDto;
import com.kns.tenquest.dto.ResponseDto;
import com.kns.tenquest.entity.Replyer;
import com.kns.tenquest.response.Response;
import com.kns.tenquest.response.ResponseStatus;
import com.kns.tenquest.service.ReplyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;
@Controller
public class ReplyerController {
    @Autowired
    ReplyerService replyerService;

    @GetMapping("/view/replyers")
    public String replyerView(Model model){
        List<Replyer> replyerList = replyerService.getAllReplyers();
        model.addAttribute("replyerList", replyerList);
        return "replyer_view";
    }
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
