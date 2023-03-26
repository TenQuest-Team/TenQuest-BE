package com.kns.tenquest.requestBody;

import com.kns.tenquest.entity.Question;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TemplateRequestBody {
    public String templateId = UUID.randomUUID().toString();
    public String templateName;
    public String templateOwner;
    public Boolean isPublic;
    public List<String> QuestionDocuments = new ArrayList<>();
    public List<Long> QuestionOrder = new ArrayList<>();
}
