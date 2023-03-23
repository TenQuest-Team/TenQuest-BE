package com.kns.tenquest.service;

import com.kns.tenquest.repository.TemplateDocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemplateDocService {
    @Autowired
    TemplateDocRepository templateDocRepository;
}
