package com.kns.tenquest.controller;

import com.kns.tenquest.DtoList;
import com.kns.tenquest.dto.ResponseDto;
import com.kns.tenquest.dto.TemplateDto;
import com.kns.tenquest.entity.Template;
import com.kns.tenquest.entity.TemplateDoc;
import com.kns.tenquest.response.Response;
import com.kns.tenquest.response.ResponseStatus;
import com.kns.tenquest.service.TemplateDocService;
import com.kns.tenquest.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    @Autowired
    private TemplateDocService templateDocService;

    @GetMapping("/templates")
    public Response<TemplateDto> apiGetAllTemplates(){
        ResponseStatus responseStatus = ResponseStatus.OK;
        DtoList<TemplateDto> templateDtoList = templateService.getAllTemplates();
        //return new ResponseDto<TemplateDto>(responseStatus,templateDtoList).toResponseJson();
        return templateDtoList.toResponse(responseStatus);
    } //template Read API

//    @GetMapping("/templates/insert")
//    public String templateInsert(){
//        return "template_insert";
//    }
//    //템플릿 생성 http GET 요청 (CREATE test용)
    @ResponseBody
    @PostMapping("/templates")
    public Response<Integer> apiCreateTemplate(@RequestBody TemplateDto templateDto) {
        int createResult = templateService.createTemplate(templateDto);
        ResponseStatus responseStatus = ResponseStatus.CREATE_DONE;
        if(responseStatus.getCode() != createResult){
            responseStatus = ResponseStatus.CREATE_FAIL;
        }
        return new ResponseDto<Integer>(responseStatus,createResult).toResponse();
    } //template Create API

//    @GetMapping("/templates/modify/{id}")
//    public String templateModify(@PathVariable("id") String id, Model model){
//        //@PathVariable은 "/" 뒤에 물음표 없이 id가 붙어옴
//        model.addAttribute("template", templateService.templateView(id));
//        return "template_modify";
//    } //변경 http GET 요청 (UPDATE Test용)

//    @GetMapping("/templates/view")
//    //HTML에서 PK를 받아 그 PK인 Id 소유한 하나의 레코드 정보로 들어가기
//    public String templateView(Model model, String id){
//        model.addAttribute("template",templateService.templateView(id));
//        return "template_view";
//    } //특정 template으로 진입

    @PatchMapping("/templates/{id}")
    public Response<Template> apiTemplateUpdate(@PathVariable("id") String templateId, @RequestBody TemplateDto templateDto) {
        ResponseDto<Template> updateResult = templateService.templateUpdate(templateId, templateDto);

        return updateResult.toResponse();
    } //template Update API
    @DeleteMapping("/templates")
    public Response<Integer> apiTemplateDelete(@RequestParam("template-id") String templateId){
        ResponseStatus deleteResult = templateService.templateDelete(templateId);
        return new ResponseDto<Integer>(deleteResult,null).toResponse();
    } //template Delete API



}