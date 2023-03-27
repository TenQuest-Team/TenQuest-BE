package com.kns.tenquest.requestBody;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class AnswerCreateRequestBody {

    /* Auto Generated */
    public String answerId = UUID.randomUUID().toString().replace("-","");
    public int replyerId; /* Assign replyerId(UUID) at the time an answer is created */
    public LocalDateTime answerTime = LocalDateTime.now();

    /* Fields required upon request  */
    public int docId; // templateDocId
    public String replyerName;
    public String answerContent;
    public boolean isPublic;

}
