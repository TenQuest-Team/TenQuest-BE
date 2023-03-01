package com.kns.tenquest.dto;
//DTO 는  getter /setter 메소드만 가진다

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@Getter
@Setter
public class QuestionDto {
    private UUID questionId;
    private String questionContent;
    private int questionCategoryId;
    private String questionCreatedBy;


    /*@Builder
    public  QuestionDto( String questionContent, int questionCategoryId, String questionCreatedBy){
       // this.questionId=questionId;
        this.questionContent =questionContent;
       this.questionCategoryId= questionCategoryId;
        this.questionCreatedBy=questionCreatedBy;
    }*/


}
