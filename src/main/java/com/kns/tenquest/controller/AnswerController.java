package com.kns.tenquest.controller;
import org.springframework.stereotype.Controller;
import com.kns.tenquest.ENV;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(ENV.API_PREFIX)
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
public class AnswerController {

}
