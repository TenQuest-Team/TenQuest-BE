
package com.kns.tenquest.controller;

import com.kns.tenquest.DtoList;
import com.kns.tenquest.ENV;
import com.kns.tenquest.RequestWrapper.TemplateWrapper;
import com.kns.tenquest.dto.ResponseDto;
import com.kns.tenquest.dto.ServiceResult;
import com.kns.tenquest.dto.TemplateDocDto;
import com.kns.tenquest.dto.TemplateDto;
import com.kns.tenquest.response.Response;
import com.kns.tenquest.response.Response_Deprecated;
import com.kns.tenquest.response.ResponseStatus;
import com.kns.tenquest.service.TemplateDocService;
import com.kns.tenquest.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(ENV.API_PREFIX)
@RequiredArgsConstructor
public class TemplateController {

    private final TemplateService templateService;
    private final TemplateDocService templateDocService;

    @ResponseBody
    @GetMapping("/templates")
    public Response apiGetAllTemplates() {
        ServiceResult sr = templateService.getAllTemplates();
        return sr.isFailed() ?
                new Response().BadRequest().message(sr.getMessage());
        new Response().Ok().data(sr.getData());
    }

    @ResponseBody
    @GetMapping("/templates/{memberId}")
    public Response apiGetAllMemberTemplates(@PathVariable("memberId")String memberId){
        ServiceResult sr = templateService.getAllTemplatesByMember(memberId);
        return sr.isFailed() ?
                new Response().BadRequest().message(sr.getMessage()) :
                new Response().Ok().data(sr.getData());
    }

    @ResponseBody
    @GetMapping("/template-docs")
    public Response apiGetAllTemplateDocs(){
        ServiceResult sr = templateDocService.getAllTemplateDocs();
        return sr.isFailed() ?
                new Response().BadRequest().message(sr.getMessage()) :
                new Response().Ok().data(sr.getData());
    }

    @ResponseBody
    @GetMapping("templates/template-id")
    public Response apiGetTemplate(@RequestParam("value") String templateId){
        ServiceResult sr = templateService.getTemplate(templateId);
        return sr.isFailed() ?
                new Response().BadRequest().message(sr.getMessage()) :
                new Response().Ok().data(sr.getData());
    }

    @ResponseBody
    @PostMapping("/templates/{memberId}")
    public Response apiCreateTemplate(@RequestBody TemplateWrapper requestWrapper, @PathVariable("memberId")String memberId) {
        ServiceResult sr = templateService.createTemplate(requestWrapper,memberId);
        return sr.isFailed() ?
                new Response().BadRequest().message(sr.getMessage()) :
                new Response().Ok().data(sr.getData());
    }


    @PutMapping("/templates/{memberId}/template-id")
    public Response apiTemplateUpdate(@PathVariable("memberId") String memberId, @RequestParam("value") String templateId, @RequestBody TemplateDto templateDto) {
        ServiceResult sr = templateService.templateUpdate(memberId,templateId, templateDto);
        return sr.isFailed() ?
                new Response().BadRequest().message(sr.getMessage()) :
                new Response().Ok().data(sr.getData());
    }

    @DeleteMapping("/templates/{memberId}/template-id")
    public Response apiTemplateDelete(@PathVariable("memberId") String memberId, @RequestParam("value") String templateId) {
        ServiceResult sr = templateService.templateDelete(memberId, templateId);
        return sr.isFailed() ?
                new Response().BadRequest().message(sr.getMessage()) :
                new Response().Ok().data(sr.getData());
    }
}

