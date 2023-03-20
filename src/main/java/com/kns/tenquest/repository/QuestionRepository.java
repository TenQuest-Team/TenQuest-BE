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


     List<Question> findAllByQuestionCategoryIdAndQuestionCreatedBy(int questionCategoryId, String accessMemberId);

     List<Question> findAllQuestionContentByQuestionCategoryId(int questionCategoryId); // 부족.. 얘가 왜 question list를 반환하는지..

     Question getQuestionContentByQuestionId(String questionId);  // Repository 에선 Question 객체밖에 못 반환하는지?

    // void postQuestionContent(int questionCategoryId, String questionCreatedBy, String questionContent);

}
