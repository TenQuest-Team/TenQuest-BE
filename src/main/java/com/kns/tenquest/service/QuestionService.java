package com.kns.tenquest.service;


import com.kns.tenquest.CategoryEnum;
import com.kns.tenquest.DtoList;
import com.kns.tenquest.dto.QuestionDto;
import com.kns.tenquest.dto.QuestionSaveRequestDto;
import com.kns.tenquest.entity.Question;
import com.kns.tenquest.repository.QuestionRepository;
import com.kns.tenquest.requestBody.MultipleQuestionRequestBody;
import com.kns.tenquest.requestBody.QuestionRequestBody;
import com.kns.tenquest.response.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;



    public DtoList<QuestionDto>  getAllQuestions(){

        DtoList<QuestionDto> questionDtoList = new DtoList<>(questionRepository.findAll());

        return questionDtoList;

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

        List<Question> questionList = questionRepository.findAllByQuestionCategoryIdAndQuestionCreatedBy(questionCategoryId,accessMemberId);
        List<String> questionContentList = new ArrayList<>();
        for (Question question: questionList){
            questionContentList.add(question.getQuestionContent()); //부족 이부분을 추가해야했음.. .
        }
        return questionContentList;
    }

    /* Woody */
    public List<Question> getQuestionsByQuestionCategoryIdAndQuestionCreatedBy_v2(int questionCategoryId, String memberId) {
        if(questionCategoryId != 0)  return questionRepository.findAllByQuestionCategoryId(questionCategoryId);
        return questionRepository.findAllByQuestionCategoryIdAndQuestionCreatedBy(questionCategoryId, memberId);
    }

    public List<Question> getQuestionContentByQuestionCategoryId(int questionCategoryId) {

        List<Question> questionList = questionRepository.findAllQuestionContentByQuestionCategoryId(questionCategoryId);

        return questionList;
    }

    public String getQuestionContentByQuestionId(String questionId) {

        Optional<Question> question = questionRepository.getQuestionContentByQuestionId(questionId);
        if (!question.isEmpty())
            return question.get().getQuestionContent();
        return ResponseStatus.NOT_FOUND.getStatus();


    }

    @Transactional //db트랜젝션 자동으로 commit 해줌 ??
    public int save(QuestionSaveRequestDto requestDto) {
        // dto를 entity 화 해서 repository 의 save 메소드를 통해 db에 저장.
        Optional<Question> optQuestion = questionRepository.getQuestionContentByQuestionId(requestDto.questionId);
        if (optQuestion.isEmpty()) {
            questionRepository.save(requestDto.toEntity());
            return ResponseStatus.CREATE_DONE.getCode();
        }
        return ResponseStatus.CREATE_FAIL.getCode();

    }
    /* Woody, not use */
    public List<String> insertMultipleQuestions(List<QuestionRequestBody> requestBody){
        var resUUID = new ArrayList<String>();
        for(int i=0; i<requestBody.size(); i++){
            var uuid = UUID.randomUUID().toString().replace("-","");
            questionRepository.save(Question.builder()
                    .questionId(uuid)
                    .questionContent(requestBody.get(i).questionContent)
                    .questionCreatedBy(requestBody.get(i).questionCreatedBy)
                    .questionCategoryId(CategoryEnum.USER_CREATED.getId())
                    .build());
            resUUID.add(uuid);
        }
        return resUUID;
    }
    /* Woody */
    public String insertQuestion(QuestionRequestBody reqBody){
            var uuid = UUID.randomUUID().toString().replace("-","");
            questionRepository.save(Question.builder()
                    .questionId(uuid)
                    .questionContent(reqBody.questionContent)
                    .questionCreatedBy(reqBody.questionCreatedBy)
                    .questionCategoryId(CategoryEnum.USER_CREATED.getId())
                    .build());

        return uuid;
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

    public List<Integer> getAllCategoryIds() {


        return questionRepository.findAllCategoryIds();
    }

    public QuestionDto deleteQuestion(String questionId){
        QuestionDto deletingQuestion = new QuestionDto(questionRepository.findById(questionId).get());
        questionRepository.deleteById(questionId);
        return deletingQuestion;
    }

}

      //  UUID uuid = UUID.randomUUID();
      //  Question question =new Question(uuid, questionContent,questionCategoryId,questionCreatedBy);

       // question.setQuestionCategoryId(questionCategoryId);
       // question.setQuestionCreatedBy(questionCreatedBy);
       // question.setQuestionContent(questionContent);
        //question.toEntity;




