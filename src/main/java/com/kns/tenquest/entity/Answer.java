package com.kns.tenquest.entity;
import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Table(name="answer_table")
@Entity
@NoArgsConstructor
public class Answer {
    @Id
    @Column(name = "answer_id")
    private String answerId;

    @Column(name = "replyer_id")
    private int replyerId;

    @Column(name = "answer_content")
    private String answerContent;

    @Column(name = "doc_id")
    private Long docId;

    @Column(name = "answer_time")
    private LocalDateTime answerTime;

    @Column(name="is_public")
    @JsonProperty("isPublic")
    private boolean isPublic;

    @Builder
    public Answer(String answerId, int replyerId, String answerContent, Long docId, LocalDateTime answerTime, boolean isPublic) {
        this.answerId = answerId;
        this.replyerId = replyerId;
        this.answerContent = answerContent;
        this.docId = docId;
        this.answerTime = answerTime;
        this.isPublic = isPublic;
    }


}
