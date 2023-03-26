package com.kns.tenquest.dto;

import com.kns.tenquest.entity.Question;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class QuestionSaveRequestDto {

    public String questionId = UUID.randomUUID().toString().replace("-", "");
    public String questionContent;
    private int questionCategoryId;
    private String questionCreatedBy;

    @Builder
    public QuestionSaveRequestDto(String questionContent,int questionCategoryId, String questionCreatedBy){

        this.questionContent =questionContent;
        this.questionCategoryId= questionCategoryId;
        this.questionCreatedBy = questionCreatedBy;
    }
    // dto 로 받은객체를 entity 화하여 저장
    public Question toEntity(){
        return Question.builder()
                .questionId(questionId)
                .questionContent(questionContent)
                .questionCategoryId(questionCategoryId)
                .questionCreatedBy(questionCreatedBy).build();
    }


}
