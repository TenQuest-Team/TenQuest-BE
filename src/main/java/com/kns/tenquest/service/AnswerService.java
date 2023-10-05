package com.kns.tenquest.service;

import com.kns.tenquest.DtoList;
import com.kns.tenquest.dto.AnswerDto;
import com.kns.tenquest.dto.ReplyerDto;
import com.kns.tenquest.dto.ServiceResult;
import com.kns.tenquest.entity.Answer;
import com.kns.tenquest.entity.TemplateDoc;
import com.kns.tenquest.repository.AnswerRepository;
import com.kns.tenquest.repository.ReplyerRepository;
import com.kns.tenquest.repository.TemplateDocRepository;
import com.kns.tenquest.requestBody.MultipleAnswerRequestBody;
import com.kns.tenquest.requestBody.SingleAnswerCreateRequestBody;
import com.kns.tenquest.response.ReplyerNameListResponseWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.kns.tenquest.util.PrimaryKeyGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final ReplyerRepository replyerRepository;
    private final TemplateDocRepository templateDocRepository;
    private final PrimaryKeyGenerator generator;

    public ServiceResult getAllAnswers(){

        var DtoList = new DtoList<>(answerRepository.findAll(Sort.by(Sort.Direction.DESC, "answerTime")));

        return new ServiceResult().success()
                .data(DtoList);

    }

    /* Test Done [23/03/27] */
    public ServiceResult getAnswerByDocId(Long docId){
        // Sorting Answer by unique question on template(docid)

        DtoList<AnswerDto> answerDtoList = new DtoList(answerRepository.findAnswerByDocId(docId));
        return new ServiceResult().success()
                .data(answerDtoList);
    }

    /* working... */
    public ServiceResult getAnswerListByReplyerId(int replyerId){

        List<Answer> answers = answerRepository.findAnswerByReplyerId(replyerId);

        return new ServiceResult().success().data(answers);
    }

    /* working... */
    public ServiceResult getReplyerNameListByTemplateId(String templateId){

        List<TemplateDoc> templateDocList = templateDocRepository.findAllByTemplateId(templateId);

        if(templateDocList.isEmpty()) {
            return new ServiceResult().fail().message("No such templateId");
        }

        var wrapperList = new ArrayList<ReplyerNameListResponseWrapper>();
        Long templateDocId = templateDocList.get(0).getTemplateDocId();
        // get answers
        List<Answer> answers = answerRepository.findAnswerByDocId(templateDocId);

        List<String> replyerNameList = new ArrayList<>();
        for (int i=0; i <answers.size(); i++){
            int replyerId = answers.get(i).getReplyerId();
            LocalDateTime answerTime = answers.get(i).getAnswerTime();
            String replyerName = replyerRepository.findReplyerByReplyerId(replyerId).get().getReplyerName();
            boolean isPublic = answers.get(i).isPublic();
            replyerNameList.add(replyerName);

            ReplyerNameListResponseWrapper wrapper =
                    ReplyerNameListResponseWrapper.builder()
                                    .replyerName(replyerName)
                                .replyerId(replyerId)
                                    .answerTime(answerTime)
                            .isPublic(isPublic).build() ;
            wrapperList.add(wrapper);
        }
        return new ServiceResult().success().data(wrapperList);
    }

    public ServiceResult createAnswer(MultipleAnswerRequestBody reqBody){

        int generatedReplyerId = generator.replyerId();
        boolean isPublic = false;
        if (reqBody.isPublic.equals("true")) isPublic = true;

        for (int i =0; i<reqBody.docIdList.size(); i++){
            Optional<TemplateDoc> nullableDoc = templateDocRepository.findById(reqBody.docIdList.get(i));
            if (nullableDoc.isEmpty()){
                return new ServiceResult().fail().message("No such docId");
            }
            var sReqBody = SingleAnswerCreateRequestBody.builder()
                    .docId(reqBody.docIdList.get(i))
                    .answerContent(reqBody.answerContentList.get(i))
                    .replyerName(reqBody.replyerName)
                    .isPublic(reqBody.isPublic)
                    .build();


            this.createSingleAnswer(sReqBody,generatedReplyerId);
        }
        return new ServiceResult().success().data("Create Done");
    }

        /* Test Done [23/03/27] */
    public ServiceResult createSingleAnswer(SingleAnswerCreateRequestBody reqBody, int replyerId) {
        /* check fk valid */
        if(templateDocRepository.findById(reqBody.docId).isEmpty()){
            return new ServiceResult().fail().message("No such docId");
        }

        /* setting values */
        //int generatedReplyerId = generator.replyerId();
        boolean isPublic = false;
        if (reqBody.isPublic.equals("true")) isPublic = true;


        /* Create Replyer */
        ReplyerDto replyerDto = ReplyerDto.builder()
                .replyerId(replyerId)
                .replyerName(reqBody.replyerName)
                .build();

        /* Save Replyer to database(replyer_table) */
        replyerRepository.save(replyerDto.toEntity());

        /* Create Answer */
        AnswerDto answerDto = AnswerDto.builder()
                .answerId(generator.UUID())
                .answerTime(generator.localDateTime())
                //.answerContent(reqBody.answerContent.replace("\n", "\\r\\n"))
                .answerContent(reqBody.answerContent)
                .docId(reqBody.docId)
                .replyerId(replyerId)
                .isPublic(isPublic)
                .build();

        /* Save answer to database(answer_table) */
        answerRepository.save(answerDto.toEntity());
        return new ServiceResult().success().data(answerDto);

    }

    public ServiceResult DeleteAllAnswer() {
        answerRepository.deleteAll();
        replyerRepository.deleteAll();
        return new ServiceResult().success().message("Delete Done");
    }
}
