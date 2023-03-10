package com.kns.tenquest.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;


//@Data// setter 없도록 수정필요
@Getter
@Table(name="question_table")
@NoArgsConstructor // 이게 있어야.. 필드들 final 처리 하라는 문구 안뜸 ..
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment //이거도 UUID 도 자동생성으로 만들어야하는지 모르겠음
    @Column(name="question_id")
    private UUID questionId;

    @Column(name="question_content")
    private String questionContent;


    //@ManyToOne // Many = Question , One = Category  : 한개의 카테고리는. .여러개의 질문객체를 가질수있다 . : hibernate 에서는 못씀
    @JoinColumn(name="category_id")  //이부분 맞는지 잘모르겠음 확인해봐야됨 다시
    @Column(name="question_category_id")
    private int questionCategoryId;

    @JoinColumn(name="member_table")
    @Column(name="question_created_by")
    private String questionCreatedBy;

    @Builder
    public  Question(UUID questionId, String questionContent, int questionCategoryId, String questionCreatedBy){
        this.questionId=questionId;
        this.questionContent =questionContent;
        this.questionCategoryId= questionCategoryId;
        this.questionCreatedBy=questionCreatedBy;
    }

    /*//GPT 수정
    //생성자
    public Question(){}

    public UUID getQuestionId(){
        return questionId;
    }*/

}
