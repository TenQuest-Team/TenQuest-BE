package com.kns.tenquest.repository;

import com.kns.tenquest.entity.Replyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReplyerRepository extends JpaRepository<Replyer,Integer> {
}
