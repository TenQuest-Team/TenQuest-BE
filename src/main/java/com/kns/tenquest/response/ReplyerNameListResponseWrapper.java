package com.kns.tenquest.response;

import lombok.Builder;

import java.time.LocalDateTime;

public class ReplyerNameListResponseWrapper {
    public int replyerId;
    public String replyerName;
    public LocalDateTime answerTime;

    @Builder
    public ReplyerNameListResponseWrapper(int replyerId, String replyerName, LocalDateTime answerTime) {
        this.replyerId = replyerId;
        this.replyerName = replyerName;
        this.answerTime = answerTime;
    }
}
