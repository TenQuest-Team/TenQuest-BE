package com.kns.tenquest.controller;

import com.kns.tenquest.ENV;
import com.kns.tenquest.RequestWrapper.TemplateWrapper;
import com.kns.tenquest.dto.ServiceResult;
import com.kns.tenquest.dto.TemplateDto;
import com.kns.tenquest.response.Response;
import com.kns.tenquest.service.TemplateDocService;
import com.kns.tenquest.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(ENV.API_PREFIX)
@RequiredArgsConstructor
public class TemplateController {

    private final TemplateService templateService;
    private final TemplateDocService templateDocService;

    /**
     * Request: [GET] /api/v1/templates<br>
     * @apiNote 등록된 모든 회원 정보를 조회한다.<br>
     * @see com.kns.tenquest.service.TemplateService#getAllTemplates()
     * @return  com.kns.tenquest.response.Response<br>
     * 성공 시, 모든 템플릿 정보를 담은 Response를 반환한다.
     **/
    @ResponseBody
    @GetMapping("/templates")
    public Response apiGetAllTemplates() {
        ServiceResult sr = templateService.getAllTemplates();
        return sr.isFailed() ?
                new Response().BadRequest().message(sr.getMessage()) :
                new Response().Ok().data(sr.getData());
    }

    /**
     * Request: [GET] /api/v1/templates/${memberId}<br>
     * @apiNote 특정 회원이 생성한 모든 템플릿 정보를 조회한다.<br>
     * @param memberId 조회할 회원의 `회원 식별자(memberId)`
     * @see com.kns.tenquest.service.TemplateService#getAllTemplatesByMember(String)
     * @return  com.kns.tenquest.response.Response<br>
     * 성공 시, 요청한 회원이 생성한 모든 템플릿 정보를 담은 Response를 반환한다.
     **/
    @ResponseBody
    @GetMapping("/templates/{memberId}")
    public Response apiGetAllMemberTemplates(@PathVariable("memberId")String memberId){
        ServiceResult sr = templateService.getAllTemplatesByMember(memberId);
        return sr.isFailed() ?
                new Response().BadRequest().message(sr.getMessage()) :
                new Response().Ok().data(sr.getData());
    }

    /**
     * Request: [GET] /api/v1/template-docs<br>
     * @apiNote 등록된 모든 템플릿 도큐먼트 정보를 조회한다.<br>
     * @see com.kns.tenquest.service.TemplateDocService#getAllTemplateDocs()
     * @return  com.kns.tenquest.response.Response<br>
     * 성공 시, 등록된 모든 템플릿 도큐먼트 정보를 담은 Response를 반환한다.
     **/
    @ResponseBody
    @GetMapping("/template-docs")
    public Response apiGetAllTemplateDocs(){
        ServiceResult sr = templateDocService.getAllTemplateDocs();
        return sr.isFailed() ?
                new Response().BadRequest().message(sr.getMessage()) :
                new Response().Ok().data(sr.getData());
    }

    /**
     * Request: [GET] /api/v1/templates/template-id?value=${templateId}<br>
     * @apiNote 특정 템플릿에 대한 정보를 조회한다.<br>
     * @param templateId 조회할 템플릿의 `템플릿 식별자(templateId)`
     * @see com.kns.tenquest.service.TemplateService#getTemplate(String)
     * @return  com.kns.tenquest.response.Response<br>
     * 성공 시, 요청한 템플릿 정보를 담은 Response를 반환한다.
     **/
    @ResponseBody
    @GetMapping("/templates/template-id")
    public Response apiGetTemplate(@RequestParam("value") String templateId){
        ServiceResult sr = templateService.getTemplate(templateId);
        return sr.isFailed() ?
                new Response().BadRequest().message(sr.getMessage()) :
                new Response().Ok().data(sr.getData());
    }

    /**
     * Request: [POST] /api/v1/templates/${memberId}<br>
     * @apiNote 템플릿을 생성한다.<br>
     * @param memberId 템플릿을 생성할 회원의 `회원 식별자(memberId)`
     * @param requestWrapper 생성할 템플릿 정보를 담은 Request Body
     * @see com.kns.tenquest.service.TemplateDocService#getAllTemplateDocs()
     * @return  com.kns.tenquest.response.Response<br>
     * 성공 시, 생성된 템플릿 정보를 담은 Response를 반환한다.
     **/
    @ResponseBody
    @PostMapping("/templates/{memberId}")
    public Response apiCreateTemplate(@RequestBody TemplateWrapper requestWrapper, @PathVariable("memberId")String memberId) {
        ServiceResult sr = templateService.createTemplate(requestWrapper,memberId);
        return sr.isFailed() ?
                new Response().BadRequest().message(sr.getMessage()) :
                new Response().Ok().data(sr.getData());
    }

    /**
     * Request: [PUT] /api/v1/templates/${memberId}/template-id?value=${templateId}<br>
     * @apiNote 특정 멤버가 생성한 템플릿을 수정한다.<br>
     * @param memberId 템플릿을 생성한 회원의 `회원 식별자(memberId)`
     * @param templateId 수정할 템플릿의 `템플릿 식별자(templateId)`
     * @param templateDto 생성할 템플릿 정보를 담은 Request Body
     * @see com.kns.tenquest.service.TemplateDocService#getAllTemplateDocs()
     * @return  com.kns.tenquest.response.Response<br>
     * 성공 시, 수정된 템플릿 정보를 담은 Response를 반환한다.
     **/
    @PutMapping("/templates/{memberId}/template-id")
    public Response apiTemplateUpdate(@PathVariable("memberId") String memberId, @RequestParam("value") String templateId, @RequestBody TemplateDto templateDto) {
        ServiceResult sr = templateService.templateUpdate(memberId,templateId, templateDto);
        return sr.isFailed() ?
                new Response().BadRequest().message(sr.getMessage()) :
                new Response().Ok().data(sr.getData());
    }


    /**
     * Request: [DELETE] /api/v1/templates/${memberId}/template-id?value=${templateId}<br>
     * @apiNote 특정 멤버가 생성한 템플릿을 삭제한다.<br>
     * @param memberId 템플릿을 생성한 회원의 `회원 식별자(memberId)`
     * @param templateId 삭제할 템플릿의 `템플릿 식별자(templateId)`
     * @return com.kns.tenquest.response.Response<br>
     * 성공 시, 삭제된 템플릿 정보를 담은 Response를 반환한다.
     */
    @DeleteMapping("/templates/{memberId}/template-id")
    public Response apiTemplateDelete(@PathVariable("memberId") String memberId, @RequestParam("value") String templateId) {
        ServiceResult sr = templateService.templateDelete(memberId, templateId);
        return sr.isFailed() ?
                new Response().BadRequest().message(sr.getMessage()) :
                new Response().Ok().data(sr.getData());
    }
}

