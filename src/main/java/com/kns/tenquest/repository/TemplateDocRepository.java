package com.kns.tenquest.repository;


import com.kns.tenquest.entity.TemplateDoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TemplateDocRepository extends JpaRepository<TemplateDoc,Long> {
    List<TemplateDoc> findAllTemplateDocByTemplate_TemplateId(String templateId);
    List<TemplateDoc> deleteAllTemplateDocByTemplate_TemplateId(String templateId);
}
