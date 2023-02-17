package com.kns.tenquest.repository;

import com.kns.tenquest.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface AnswerRepository extends JpaRepository<Answer, UUID> {
}
