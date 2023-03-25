package com.kns.tenquest.dto;

import com.kns.tenquest.entity.Answer;
import com.kns.tenquest.entity.Member;
import com.kns.tenquest.response.Response;
import com.kns.tenquest.response.ResponseStatus;
import com.kns.tenquest.response.Responseable;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Builder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.UUID;

public class AnswerDto implements DataTransferObject<Answer>, Responseable<AnswerDto> {
    public String answerId;
    public int replyerId;
    public String answerContent;
    public int docId;
    public LocalDateTime answerTime;
    public boolean isPublic;


    @Builder
    public AnswerDto(String answerId, int replyerId, String answerContent, int docId, LocalDateTime answerTime, boolean isPublic) {
        this.answerId = answerId;
        this.replyerId = replyerId;
        this.answerContent = answerContent;
        this.docId = docId;
        this.answerTime = answerTime;
        this.isPublic = isPublic;
    }

    @Override
    public Answer toEntity() throws NoSuchAlgorithmException {
        Answer answer = Answer.builder()
                .answerId(this.answerId)
                .replyerId(this.replyerId)
                .answerContent(this.answerContent)
                .docId(docId)
                .answerTime(answerTime)
                .isPublic(isPublic)
                .build();

        return answer;
    }

    @Override
    public DataTransferObject<Answer> toDto(Answer answer) {

        return AnswerDto.builder()
                .answerId(answer.getAnswerId())
                .replyerId(answer.getReplyerId())
                .answerContent(answer.getAnswerContent())
                .answerTime(answer.getAnswerTime())
                .isPublic(answer.isPublic())
                .build();

    }

    @Override
    public Response<AnswerDto> toResponse(ResponseStatus responseStatus) {
        return Responseable.super.toResponse(responseStatus);
    }
}
