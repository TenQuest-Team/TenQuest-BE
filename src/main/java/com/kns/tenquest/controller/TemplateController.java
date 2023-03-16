package com.kns.tenquest.controller;

import com.kns.tenquest.dto.ResponseDto;
import com.kns.tenquest.dto.TemplateDto;
import com.kns.tenquest.response.Response;
import com.kns.tenquest.response.ResponseStatus;
import com.kns.tenquest.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@Controller
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    @GetMapping("/templates")
    public String templateView(Model model){
        model.addAttribute("templateList",templateService.getAllTemplates());
        return "template_list";
    } //template Read API

    @GetMapping("/templates/insert")
    public String templateInsert(){
        return "template_insert";
    }
    //템플릿 생성 http GET 요청 (CREATE test용)

    @ResponseBody
    @PostMapping("/templates")
    public Response<Integer> apiCreateTemplate(@RequestBody TemplateDto templateDto)throws NoSuchAlgorithmException {
        int createResult = templateService.createTemplate(templateDto);

        ResponseStatus responseStatus = ResponseStatus.CREATE_DONE;
        if(createResult == ResponseStatus.CREATE_FAIL.getCode()){
            responseStatus = ResponseStatus.CREATE_FAIL;
        }
        return new ResponseDto<Integer>(responseStatus,createResult).toResponse();
    } //template Create API

    @GetMapping("/templates/modify/{id}")
    public String templateModify(@PathVariable("id") String id, Model model){
        //@PathVariable은 "/" 뒤에 물음표 없이 id가 붙어옴
        model.addAttribute("template", templateService.templateView(id));
        return "template_modify";
    } //변경 http GET 요청 (UPDATE Test용)

    @PatchMapping("/templates/{id}")
    public Response<Integer> apiTemplateUpdate(@PathVariable("id") String templateId, @RequestBody TemplateDto templateDto) throws NoSuchAlgorithmException {
        int updateResult = templateService.templateUpdate(templateId, templateDto);
        ResponseStatus responseStatus = ResponseStatus.CREATE_DONE;

        if(updateResult == ResponseStatus.CREATE_FAIL.getCode()){
            responseStatus = ResponseStatus.CREATE_FAIL;
        }
        return new ResponseDto<Integer>(responseStatus,updateResult).toResponse();
    } //template Update API

    @GetMapping("/templates/view")
    //HTML에서 PK를 받아 그 PK인 Id 소유한 하나의 레코드 정보로 들어가기
    public String templateView(Model model, String id){
        model.addAttribute("template",templateService.templateView(id));
        return "template_view";
    } //특정 template으로 진입
    @GetMapping("/templates/delete")
    public String templateDelete(String id){
        templateService.templateDelete(id);

        return "redirect:/templates";
    } //template Delete API



}
