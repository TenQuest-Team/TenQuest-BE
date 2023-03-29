package com.kns.tenquest.dto;
//DTO 는  getter /setter 메소드만 가진다

import com.kns.tenquest.entity.Member;
import com.kns.tenquest.entity.Question;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.JoinColumn;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuestionDto implements DataTransferObject<Question>{

    //@GeneratedValue(generator = "uuid2")
    //@GenericGenerator(name = "uuid2", strategy = "uuid2")
    //@Column(name = "question_id", columnDefinition = "String(256)") // columnDefinition 수정필요
    //@Type (value = "uuid")
    private String questionId;
    private String questionContent;
    private int questionCategoryId;
    private String questionCreatedBy; // 이거 외래키로 갖고와야함

    public QuestionDto (Question question){
        this.questionId = question.getQuestionId();
        this.questionContent = question.getQuestionContent();
    }

    // DTO 를 Entity 로 변환
    @Override
    public Question toEntity() throws NoSuchAlgorithmException {
        Question question = Question.builder()
                                    .questionId(this.questionId)
                                    .questionContent(this.questionContent)
                                    .questionCategoryId(this.questionCategoryId)
                                    .questionCreatedBy(this.questionCreatedBy)
                                    .build();

        return question;
    }

    // Entity 를 DTO 로 변환
    @Override
    public DataTransferObject<Question> toDto(Question question) {
        return new QuestionDto(question.getQuestionId(),question.getQuestionContent(),question.getQuestionCategoryId(),question.getQuestionCreatedBy());
        //(member.getMemberId(),member.getUserId(),member.getUserInfo(),member.getUserName(), member.getUserEmail());

    }
}

    //생성자 -> 객체 자체가 들어왔을때랑.. 필드값들이 들어왔을때 구분해서 해야할듯..
  /*  @Builder
    public  QuestionDto( String questionContent, int questionCategoryId, String questionCreatedBy){
       //this.questionId=questionId;
        this.questionContent =questionContent;
       this.questionCategoryId= questionCategoryId;
        this.questionCreatedBy=questionCreatedBy;
    }

*/

