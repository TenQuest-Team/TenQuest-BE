package com.kns.tenquest.service;

import com.kns.tenquest.entity.Answer;
import com.kns.tenquest.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {
    @Autowired
    AnswerRepository answerRepository;

    public List<Answer> getAllAnswers(){
        return answerRepository.findAll();
    }
}
