package com.kns.tenquest.requestBody;

import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class AnswerCreateRequestBody {
    //public String answerId;
    //public int replyerId;
    public String replyerName;
    public String answerContent;
    public int docId;
    //public LocalDateTime answerTime;
    public boolean isPublic;


}
