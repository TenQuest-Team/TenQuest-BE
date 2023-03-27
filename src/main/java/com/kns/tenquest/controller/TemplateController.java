package com.kns.tenquest.controller;

import com.kns.tenquest.DtoList;
import com.kns.tenquest.ENV;
import com.kns.tenquest.RequestWrapper.TemplateWrapper;
import com.kns.tenquest.dto.ResponseDto;
import com.kns.tenquest.dto.TemplateDocDto;
import com.kns.tenquest.dto.TemplateDto;
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
@RequestMapping(ENV.API_PREFIX)
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
    @GetMapping("/templates/{memberId}")
    public Response<DtoList<TemplateDto>> apiGetAllMemberTemplates(@PathVariable("memberId")String memberId){
        ResponseStatus responseStatus = ResponseStatus.OK;
        DtoList<TemplateDto> allMemberTemplates = templateService.getAllMemberTemplates(memberId);
        return new ResponseDto<DtoList<TemplateDto>>(responseStatus,allMemberTemplates).toResponse();
    }

    @ResponseBody
    @GetMapping("/template-docs")
    public Response<TemplateDocDto> apiGetAllTemplateDocs(){
        ResponseStatus responseStatus = ResponseStatus.OK;
        DtoList<TemplateDocDto> templateDocDtoList = templateDocService.getAllTemplateDocs();
        //return new ResponseDto<TemplateDto>(responseStatus,templateDtoList).toResponseJson();
        return templateDocDtoList.toResponse(responseStatus);
    } //template Read API

    @ResponseBody
    @GetMapping("templates/{memberId}/template-id")
    public Response<TemplateWrapper> apiGetMemberTemplate(@PathVariable("memberId")String memberId, @RequestParam("value") String templateId){
        TemplateWrapper templateWrapper = templateService.getMemberTemplate(memberId,templateId);
        ResponseStatus responseStatus = ResponseStatus.OK;
        if(templateWrapper == null){
            responseStatus = ResponseStatus.NOT_FOUND;
        }
        return new ResponseDto<TemplateWrapper>(responseStatus,templateWrapper).toResponse();
    }

    @ResponseBody
    @PostMapping("/templates/{memberId}")
    public Response<TemplateWrapper> apiCreateTemplate(@RequestBody TemplateWrapper requestWrapper, @PathVariable("memberId")String memberId) {
        try{
            TemplateWrapper createdTemplate = templateService.createTemplate(requestWrapper,memberId);
            ResponseStatus responseStatus = ResponseStatus.CREATE_DONE;
            if(createdTemplate == null){
                responseStatus = ResponseStatus.CREATE_FAIL;
            }
            return new ResponseDto<TemplateWrapper>(responseStatus,createdTemplate).toResponse();
        }
        catch(NoSuchElementException e){
            ResponseStatus responseStatus = ResponseStatus.NOT_FOUND;
            return new ResponseDto<TemplateWrapper>(responseStatus,null).toResponse();
        }
        catch (RuntimeException e){
            ResponseStatus responseStatus = ResponseStatus.CREATE_FAIL;
            return new ResponseDto<TemplateWrapper>(responseStatus,null).toResponse();
        }
    } //template Create API


    @PutMapping("/templates/{memberId}/template-id")
    public Response<TemplateDto> apiTemplateUpdate(@PathVariable("memberId") String memberId,@RequestParam("value") String templateId, @RequestBody TemplateDto templateDto) {
        TemplateDto updatedTemplate = templateService.templateUpdate(memberId,templateId, templateDto);
        ResponseStatus responseStatus = ResponseStatus.OK;
        if(updatedTemplate == null){
            responseStatus = ResponseStatus.NOT_FOUND;
        }

        return new ResponseDto<TemplateDto>(responseStatus,updatedTemplate).toResponse();
    } //template Update API
    @DeleteMapping("/templates/{memberId}/template-id")
    public Response<TemplateDto> apiTemplateDelete(@PathVariable("memberId") String memberId, @RequestParam("value") String templateId){
        TemplateDto deletedTemplate = templateService.templateDelete(memberId, templateId);
        ResponseStatus responseStatus = ResponseStatus.OK;
        if(deletedTemplate == null){
            responseStatus = ResponseStatus.NOT_FOUND;
        }

        return new ResponseDto<TemplateDto>(responseStatus,deletedTemplate).toResponse();
    }   //template Delete API
}