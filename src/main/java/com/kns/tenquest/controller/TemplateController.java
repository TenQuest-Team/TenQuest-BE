package com.kns.tenquest.controller;

import com.kns.tenquest.DtoList;
import com.kns.tenquest.ENV;
import com.kns.tenquest.RequestWrapper.CreateTemplateRequestWrapper;
import com.kns.tenquest.dto.ResponseDto;
import com.kns.tenquest.dto.TemplateDocDto;
import com.kns.tenquest.dto.TemplateDto;
import com.kns.tenquest.entity.Template;
import com.kns.tenquest.response.Response;
import com.kns.tenquest.response.ResponseStatus;
import com.kns.tenquest.service.TemplateDocService;
import com.kns.tenquest.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(ENV.API_PREFIX+"/templates")
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    @Autowired
    private TemplateDocService templateDocService;

    @ResponseBody
    @GetMapping("/templates")
    public Response<TemplateDto> apiGetAllTemplates(){
        ResponseStatus responseStatus = ResponseStatus.OK;
        DtoList<TemplateDto> templateDtoList = templateService.getAllTemplates();
        //return new ResponseDto<TemplateDto>(responseStatus,templateDtoList).toResponseJson();
        return templateDtoList.toResponse(responseStatus);
    } //template Read API

    @ResponseBody
    @PostMapping("/templates/member-id")
    public Response<CreateTemplateRequestWrapper> apiCreateTemplate(@RequestBody CreateTemplateRequestWrapper requestWrapper, @RequestParam("value")String memberId) {
//        TemplateDto createdTemplate = templateService.createTemplate(templateDto,memberId);
//        ResponseStatus responseStatus = ResponseStatus.CREATE_DONE;
//        if(createdTemplate == null){
//            responseStatus = ResponseStatus.CREATE_FAIL;
//        }
//        return new ResponseDto<TemplateDto>(responseStatus,createdTemplate).toResponse();
        try{
            CreateTemplateRequestWrapper createdTemplate = templateService.createTemplate(requestWrapper,memberId);
            ResponseStatus responseStatus = ResponseStatus.CREATE_DONE;
            return new ResponseDto<CreateTemplateRequestWrapper>(responseStatus,createdTemplate).toResponse();
        }
        catch(NoSuchElementException e){
            ResponseStatus responseStatus = ResponseStatus.NOT_FOUND;
            return new ResponseDto<CreateTemplateRequestWrapper>(responseStatus,null).toResponse();
        }
        catch (RuntimeException e){
            ResponseStatus responseStatus = ResponseStatus.CREATE_FAIL;
            return new ResponseDto<CreateTemplateRequestWrapper>(responseStatus,null).toResponse();
        }
    } //template Create API


    @PutMapping("/templates/{id}")
    public Response<TemplateDto> apiTemplateUpdate(@PathVariable("id") String templateId, @RequestBody TemplateDto templateDto) {
        TemplateDto updatedTemplate = templateService.templateUpdate(templateId, templateDto);
        ResponseStatus responseStatus = ResponseStatus.OK;
        if(updatedTemplate == null){
            responseStatus = ResponseStatus.NOT_FOUND;
        }

        return new ResponseDto<TemplateDto>(responseStatus,updatedTemplate).toResponse();
    } //template Update API
    @DeleteMapping("/templates/{template-id}")
    public Response<TemplateDto> apiTemplateDelete(@PathVariable("template-id") String templateId){
        TemplateDto deletedTemplate = templateService.templateDelete(templateId);
        ResponseStatus responseStatus = ResponseStatus.OK;
        if(deletedTemplate == null){
            responseStatus = ResponseStatus.NOT_FOUND;
        }

        return new ResponseDto<TemplateDto>(responseStatus,deletedTemplate).toResponse();
    } //template Delete API

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

    //    @GetMapping("/templates/insert")
//    public String templateInsert(){
//        return "template_insert";
//    }
//    //템플릿 생성 http GET 요청 (CREATE test용)


}