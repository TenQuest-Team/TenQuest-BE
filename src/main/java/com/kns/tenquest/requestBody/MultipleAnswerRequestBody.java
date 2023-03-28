package com.kns.tenquest.requestBody;

import java.util.List;

public class MultipleAnswerRequestBody {

    /* Fields required upon request  */
    public List<Long> docIdList; // templateDocId
    public String replyerName;
    public List<String> answerContentList;
    public String isPublic;
}
