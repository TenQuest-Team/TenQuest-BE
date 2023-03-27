package com.kns.tenquest.requestBody;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class AnswerCreateRequestBody {

    /* Fields required upon request  */
    public Long docId; // templateDocId
    public String replyerName;
    public String answerContent;
    public String isPublic;

}
