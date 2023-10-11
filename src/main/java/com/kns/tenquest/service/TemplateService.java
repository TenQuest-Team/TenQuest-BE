package com.kns.tenquest.service;

import com.kns.tenquest.DtoList;
import com.kns.tenquest.RequestWrapper.TemplateWrapper;
import com.kns.tenquest.dto.*;
import com.kns.tenquest.entity.Member;
import com.kns.tenquest.entity.Question;
import com.kns.tenquest.entity.Template;
import com.kns.tenquest.entity.TemplateDoc;
import com.kns.tenquest.repository.MemberRepository;
import com.kns.tenquest.repository.QuestionRepository;
import com.kns.tenquest.repository.TemplateDocRepository;
import com.kns.tenquest.repository.TemplateRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TemplateService {

    private final TemplateRepository templateRepository;
    private final TemplateDocRepository templateDocRepository;
    private final MemberRepository memberRepository;
    private final QuestionRepository questionRepository;

    public ServiceResult getAllTemplates(){
        DtoList<TemplateDto> TemplateDtoList = new DtoList<>(templateRepository.findAll());
        return new ServiceResult().success().data(TemplateDtoList);
    }
    public ServiceResult getAllTemplatesByMember(String templateOwner){
        List<Template> templateList = new ArrayList<Template>(templateRepository.findAllByTemplateOwner(templateOwner));
        DtoList<TemplateDto> templateDtoList = new DtoList<>();
        for(Template ele : templateList){
            TemplateDto optTempDto = new TemplateDto(ele);
            templateDtoList.add(optTempDto);
        }
        Collections.sort(templateDtoList, new Comparator<TemplateDto>() {
            @Override
            public int compare(TemplateDto template1, TemplateDto template2) {
                return template2.getCreatedAt().compareTo(template1.getCreatedAt());
            }
        });
        return new ServiceResult().success().data(templateDtoList);
    }

    public ServiceResult getTemplate(String templateId){
        Optional<Template> optTemplate = templateRepository.findById(templateId);
        if(optTemplate.isEmpty()){
            return new ServiceResult().fail().message("Template not found");
        }
        TemplateDto templateDto = new TemplateDto(optTemplate.get());
        String templateOwnerId = templateDto.getTemplateOwner();
        Optional<Member> optMem = memberRepository.findById(templateOwnerId);
        if(optMem.isEmpty()){
            return new ServiceResult().fail().message("Template owner not found");
        }
        MemberDto templateOwner = new MemberDto(optMem.get());
        String templateOwnerName = templateOwner.userName;
        List<TemplateDoc> templateDocList1 = templateDocRepository.findAllByTemplateId(templateId);
        List<TemplateDocDto> templateDocList = new DtoList<>();

        for (TemplateDoc doc : templateDocList1) {
            Optional<Question> optQuestion = questionRepository.findById(doc.getQuestionId());
            if(optQuestion.isEmpty()){
                return new ServiceResult().fail().message("Question not found");
            }
            QuestionDto questionDto = new QuestionDto(optQuestion.get());
            TemplateDocDto templateDocDto = new TemplateDocDto(doc);
            templateDocDto.setQuestionContent(questionDto.getQuestionContent());
            templateDocList.add(templateDocDto);
        }
        return new ServiceResult().success().data( new TemplateWrapper(templateDto,templateOwnerName,templateDocList));
    }

    public ServiceResult getTemplateByTemplateName(String templateName){
        return
                new ServiceResult()
                        .success()
                        .data(new TemplateDto(templateRepository.findTemplateByTemplateName(templateName).orElse(new Template())));
    }

    @Transactional
    public ServiceResult createTemplate(TemplateWrapper requestWrapper, String memberId) {
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

                return new ServiceResult().success().data(requestWrapper);
            } catch (RuntimeException e) {
                e.printStackTrace();
                throw new RuntimeException("template 생성 중 오류가 발생하였습니다.");
            }
        }
        return new ServiceResult().fail().message("template creation failed");
    } //처음 create 시 생성값 주기

    public ServiceResult templateUpdate(String memberId,String templateId, TemplateDto templateDto) {
        Optional<Template> optTemplate = templateRepository.findByTemplateIdAndTemplateOwner(templateId,memberId);
        if(optTemplate.isEmpty()){
            return new ServiceResult().fail().message("Template not found");
        }
        else{
            TemplateDto updatingTemplateDto = new TemplateDto(optTemplate.get());
            if(StringUtils.isNotBlank(templateDto.getTemplateName()))
                updatingTemplateDto.setTemplateName(templateDto.getTemplateName());
            if(StringUtils.isNotBlank(templateDto.getIsPublic().toString()))
                updatingTemplateDto.setIsPublic(templateDto.getIsPublic());
            templateRepository.save(updatingTemplateDto.toEntity());

            return new ServiceResult().success()
                    .data(updatingTemplateDto);
        }
    } //수정시 변경사항을 controller에서 적용한 후 저장

    public ServiceResult templateView(String templateId)
    {
        return new ServiceResult().success().data(templateRepository.findById(templateId).get());
    }

    public ServiceResult templateDelete(String memberId, String templateId){
        Optional<Template> optTemplate = templateRepository.findByTemplateIdAndTemplateOwner(templateId,memberId);
        if(optTemplate.isEmpty()){
            return new ServiceResult().fail().message("Template not found");
        }
        List deletingTemplateDocList = new DtoList<>(templateDocRepository.findAllByTemplateId(templateId));

        TemplateDto deletedTemplateDto = new TemplateDto(optTemplate.get());
        templateDocRepository.deleteAllInBatch(deletingTemplateDocList);
        templateRepository.deleteById(templateId);

        return new ServiceResult().success()
                .data(deletedTemplateDto);

    }
}

