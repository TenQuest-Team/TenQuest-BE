package com.kns.tenquest.repository;

import com.kns.tenquest.dto.QuestionDto;
import com.kns.tenquest.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<Question, UUID> {

     List<Question> findAllByQuestionCreatedBy(String memberId);

     List<Question> findAllByQuestionCategoryId(int questionCategoryId);


     List<String> findAllByQuestionCategoryIdAndQuestionCreatedBy(int questionCategoryId, String accessMemberId);

     List<String> findAllQuestionContentByQuestionCategoryId(int questionCategoryId);

     String getQuestionContentByQuestionId(String questionId);

    // void postQuestionContent(int questionCategoryId, String questionCreatedBy, String questionContent);

}
