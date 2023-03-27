package com.kns.tenquest.repository;

import com.kns.tenquest.DtoList;
import com.kns.tenquest.dto.TemplateDto;
import com.kns.tenquest.entity.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TemplateRepository extends JpaRepository<Template, String> {
    Optional<Template> findTemplateByTemplateName(String templateName);

    Optional<Template> findTemplateByTemplateNameAndTemplateOwner(String templateName, String templateOwner);

    DtoList<Template> findAllByTemplateOwner(String templateOwner);

    Optional<Template> findByTemplateIdAndTemplateOwner(String templateId, String templateOwner);
}
