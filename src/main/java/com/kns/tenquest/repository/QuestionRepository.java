package com.kns.tenquest.repository;

import com.kns.tenquest.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<Question, UUID> {

     List<Question> findAllByQuestionCreatedBy(String memberId);

     List<Question> findAllByQuestionCategoryId(int questionCategoryId);


}
