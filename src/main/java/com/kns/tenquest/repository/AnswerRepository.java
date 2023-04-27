package com.kns.tenquest.repository;

import com.kns.tenquest.entity.Answer;
import com.kns.tenquest.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface AnswerRepository extends JpaRepository<Answer, String> {
    List<Answer> findAll();
    List<Answer> findAnswerByDocId(Long docId);
    List<Answer> findAnswerByReplyerId(int replyerId);


}
