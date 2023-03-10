package com.kns.tenquest.repository;

import com.kns.tenquest.entity.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TemplateRepository extends JpaRepository<Template, String> {
    Optional<Template> findTemplateByTemplateName(String templateName);
}
