package com.kns.tenquest.requestBody;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SingleAnswerCreateRequestBody {

    /* Fields required upon request  */
    public Long docId; // templateDocId
    public String replyerName;
    public String answerContent;
    public String isPublic;

    @Builder
    public SingleAnswerCreateRequestBody(Long docId, String replyerName, String answerContent, String isPublic) {
        this.docId = docId;
        this.replyerName = replyerName;
        this.answerContent = answerContent;
        this.isPublic = isPublic;
    }
}
