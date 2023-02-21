package com.kns.tenquest.controller;

import com.kns.tenquest.entity.Replyer;
import com.kns.tenquest.service.ReplyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
