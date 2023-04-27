package com.kns.tenquest.response;

import lombok.Builder;

import java.time.LocalDateTime;

public class ReplyerNameListResponseWrapper {
    public int replyerId;
    public String replyerName;
    public LocalDateTime answerTime;

    public boolean isPublic;

    @Builder
    public ReplyerNameListResponseWrapper(int replyerId, String replyerName, LocalDateTime answerTime, boolean isPublic) {
        this.replyerId = replyerId;
        this.replyerName = replyerName;
        this.answerTime = answerTime;
        this.isPublic = isPublic;
    }
}
