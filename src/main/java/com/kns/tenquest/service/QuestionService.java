package com.kns.tenquest.service;


import com.kns.tenquest.dto.QuestionDto;
import com.kns.tenquest.entity.Question;
import com.kns.tenquest.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    public List<Question> getAllQuestions(){
        return questionRepository.findAll();
    }

    //특정한 question_created_by 컬럼 값에 해당하는 질문객체 들을 찾아서 리스트로 반환
    public List<Question> getAllQuestionsByQuestionCreatedBy(String memberId) {
        return questionRepository.findAllByQuestionCreatedBy(memberId);
    }

    //특정한 question_category_id 값을 받아서.. 해당하는 질문객체들을 반환
    public List<Question> getQuestionsByQuestionCategoryId(int questionCategoryId){
        return questionRepository.findAllByQuestionCategoryId(questionCategoryId);
    }


    public List<String> getQuestionsByQuestionCategoryIdAndQuestionCreatedBy(int questionCategoryId, String accessMemberId) {
        return questionRepository.findAllByQuestionCategoryIdAndQuestionCreatedBy(questionCategoryId,accessMemberId);
    }

    public List<String> getQuestionContentByQuestionCategoryId(int questionCategoryId) {

        List<Question> questionList = questionRepository.findAllQuestionContentByQuestionCategoryId(questionCategoryId);
        List<String> questionContentList = new ArrayList<>();
        for (Question question: questionList){
            questionContentList.add(question.getQuestionContent()); //부족
        }
        return questionContentList;
    }

    public String getQuestionContentByQuestionId(String questionId) {
        return questionRepository.getQuestionContentByQuestionId(questionId);

    }
/*
    public void postQuestionContent(QuestionDto questionDto) throws NoSuchAlgorithmException {


        Question question = questionDto.toEntity();

        questionRepository.save(question);
        questionRepository.postQuestionContent(questionDto.getQuestionCategoryId()
                ,questionDto.getQuestionCreatedBy()
                ,questionDto.getQuestionContent());
        return ;
    }*/
}

      //  UUID uuid = UUID.randomUUID();
      //  Question question =new Question(uuid, questionContent,questionCategoryId,questionCreatedBy);

       // question.setQuestionCategoryId(questionCategoryId);
       // question.setQuestionCreatedBy(questionCreatedBy);
       // question.setQuestionContent(questionContent);
        //question.toEntity;


