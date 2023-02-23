package com.kns.tenquest.repository;

import com.kns.tenquest.entity.TemplateDoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateDocRepository extends JpaRepository<TemplateDoc,Long> {
}
