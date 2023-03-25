package com.kns.tenquest.service;

import com.kns.tenquest.dto.MemberDto;
import com.kns.tenquest.dto.ReplyerDto;
import com.kns.tenquest.entity.Answer;
import com.kns.tenquest.entity.Member;
import com.kns.tenquest.entity.Replyer;
import com.kns.tenquest.repository.AnswerRepository;
import com.kns.tenquest.requestBody.AnswerCreateRequestBody;
import com.kns.tenquest.response.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AnswerService {
    @Autowired
    AnswerRepository answerRepository;

    public List<Answer> getAllAnswers(){
        return answerRepository.findAll();
    }

    public List<Answer> getAnswerByDocId(String docId){
        return answerRepository.findAnswerByDocId(docId);
    }

    public void createAnswer(AnswerCreateRequestBody answerCreateRequestBody){
//        Answer answer = Answer.builder()
//                .answerId(UUID.randomUUID()
//                        .toString()).answerContent(answerCreateRequestBody.answerContent)
//                .docId(0)
//                .build();
//        answer.setAnswerId(UUID.randomUUID().toString().replace("-",""));
//        int replyerId = 0;
//        answer.setReplyerId(replyerId);
//        replyerDto.setReplyerId(replyerId);
//        answerRepository.save(answer);

        /* template api 구현 완료되면 다시 진행 예정 */
    }

//    public int insertMember(MemberDto dto) {
//        Optional<Member> optMember = memberRepository.findMemberByUserId(dto.userId);
//        if (optMember.isEmpty()) {
//            dto.setMemberId(UUID.randomUUID().toString().replace("-",""));
//            dto.setUserRoles("ROLE_USER");
//            memberRepository.save(dto.toEntity());
//            return ResponseStatus.CREATE_DONE.getCode();
//        }
//        return ResponseStatus.CREATE_FAIL.getCode();
//    }
}
