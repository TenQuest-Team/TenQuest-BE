package com.kns.tenquest.service;

import com.kns.tenquest.DtoList;
import com.kns.tenquest.RequestWrapper.TemplateWrapper;
import com.kns.tenquest.dto.QuestionDto;
import com.kns.tenquest.dto.TemplateDocDto;
import com.kns.tenquest.dto.TemplateDto;
import com.kns.tenquest.entity.Member;
import com.kns.tenquest.entity.Question;
import com.kns.tenquest.entity.Template;
import com.kns.tenquest.entity.TemplateDoc;
import com.kns.tenquest.repository.MemberRepository;
import com.kns.tenquest.repository.QuestionRepository;
import com.kns.tenquest.repository.TemplateDocRepository;
import com.kns.tenquest.repository.TemplateRepository;
import com.kns.tenquest.requestBody.TemplateRequestBody;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class TemplateService {
    @Autowired
    TemplateRepository templateRepository;

    @Autowired
    TemplateDocRepository templateDocRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    QuestionRepository questionRepository;

    public DtoList<TemplateDto> getAllTemplates(){
        DtoList<TemplateDto> TemplateDtoList = new DtoList<>(templateRepository.findAll());
        return TemplateDtoList;
    }
    public DtoList<TemplateDto> getAllMemberTemplates(String templateOwner){
        DtoList<TemplateDto> templateDtoList = new DtoList<>(templateRepository.findAllByTemplateOwner(templateOwner));
        return templateDtoList;
    }

    public TemplateWrapper getTemplate(String templateId){
        Optional<Template> optTemplate = templateRepository.findById(templateId);
        if(optTemplate.isEmpty()){
            return null;
        }
        TemplateDto templateDto = new TemplateDto(optTemplate.get());
        List<TemplateDoc> templateDocList1 = templateDocRepository.findAllByTemplateId(templateId);
        List<TemplateDocDto> templateDocList = new DtoList<>();

        for (TemplateDoc doc : templateDocList1) {
            Optional<Question> optQuestion = questionRepository.findById(doc.getQuestionId());
            if(optQuestion.isEmpty()){
                return null;
            }
            QuestionDto questionDto = new QuestionDto(optQuestion.get());
            TemplateDocDto templateDocDto = new TemplateDocDto(doc);
            templateDocDto.setQuestionContent(questionDto.getQuestionContent());
            templateDocList.add(templateDocDto);
        }
        return new TemplateWrapper(templateDto,templateDocList);
    }
    public TemplateDto getTemplateByTemplateName(String templateName){
        return new TemplateDto(templateRepository.findTemplateByTemplateName(templateName).orElse(new Template()));
    }

    @Transactional
    public TemplateWrapper createTemplate(TemplateWrapper requestWrapper, String memberId) {
        Optional<Member> nullableMember = memberRepository.findById(memberId);
        if(nullableMember.isEmpty()){
            throw new NoSuchElementException("존재하지 않는 사용자 입니다.");
        }
        TemplateDto creatingTemplate = requestWrapper.getTemplateDto();
        Optional<Template> optTemplate = templateRepository.findTemplateByTemplateNameAndTemplateOwner(creatingTemplate.templateName,memberId);
        if (optTemplate.isEmpty()) {
            try {
                String thisTemplateId = UUID.randomUUID().toString().replace("-", "");
                creatingTemplate.setCreatedAt(LocalDateTime.now());
                creatingTemplate.setTemplateId(thisTemplateId);
                creatingTemplate.setTemplateOwner(memberId);
                templateRepository.save(creatingTemplate.toEntity());
                //template 생성 로직

                List<TemplateDocDto> creatingTemplateDocList = requestWrapper.getTemplateDocList();
                for(int i=0;i<creatingTemplateDocList.size();i++){
                    TemplateDocDto creatingTemplateDoc = creatingTemplateDocList.get(i);
                    creatingTemplateDoc.setTemplateId(thisTemplateId);
                    templateDocRepository.save(creatingTemplateDoc.toEntity());
                }
                //templateDoc 생성 로직

                return requestWrapper;
            } catch (RuntimeException e) {
                e.printStackTrace();
                throw new RuntimeException("template 생성 중 오류가 발생하였습니다.");
            }
        }
        return null;
    } //처음 create 시 생성값 주기

    public TemplateDto templateUpdate(String memberId,String templateId, TemplateDto templateDto) {
        Optional<Template> optTemplate = templateRepository.findByTemplateIdAndTemplateOwner(templateId,memberId);
        if(optTemplate.isEmpty()){
            return null;
        }
        else{
            TemplateDto updatingTemplateDto = new TemplateDto(optTemplate.get());
            if(StringUtils.isNotBlank(templateDto.getTemplateName()))
                updatingTemplateDto.setTemplateName(templateDto.getTemplateName());
            if(StringUtils.isNotBlank(templateDto.getIsPublic().toString()))
                updatingTemplateDto.setIsPublic(templateDto.getIsPublic());
            templateRepository.save(updatingTemplateDto.toEntity());
            return updatingTemplateDto;
        }
    } //수정시 변경사항을 controller에서 적용한 후 저장

    public Template templateView(String templateId){
        return templateRepository.findById(templateId).get();
    }

    public TemplateDto templateDelete(String memberId, String templateId){
        Optional<Template> optTemplate = templateRepository.findByTemplateIdAndTemplateOwner(templateId,memberId);
        if(optTemplate.isEmpty()){
            return null;
        }
        TemplateDto deletedTemplateDto = new TemplateDto(optTemplate.get());
        templateRepository.deleteById(templateId);
        return deletedTemplateDto;

    }



    //건모형이 만든거 (참고용)
    public TemplateDto _addTemplate(TemplateRequestBody templateRequestBody){
        /* Just Example. Implement Needed */

        // generate template id
        String generatedTemplateId = UUID.randomUUID().toString().replace("-", "");

        // 1. add template doc to db
        for (int i=0; i<templateRequestBody.QuestionDocuments.size(); i++){
            TemplateDoc templateDoc = TemplateDoc.builder()
                    .templateDocId((long)i)
                    .templateId(generatedTemplateId)
                    .questionId(templateRequestBody.QuestionDocuments.get(i))
                    .questionOrder(templateRequestBody.QuestionOrder.get(i))
                    .build();
            templateDocRepository.save(templateDoc);
        }

        // 2. add template to db
        Template template = Template.builder()
                .templateId(generatedTemplateId)
                .templateOwner(templateRequestBody.templateOwner)
                .templateName(templateRequestBody.templateName)
                .build();

        templateRepository.save(template);
        return new TemplateDto();
    }
}

