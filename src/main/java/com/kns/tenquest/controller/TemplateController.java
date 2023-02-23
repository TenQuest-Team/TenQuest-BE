package com.kns.tenquest.controller;

import com.kns.tenquest.entity.Template;
import com.kns.tenquest.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    @GetMapping("/templates")
    public String templateView(Model model){
        model.addAttribute("templateList",templateService.templateList());
        return "template_list";
    } //template Read API

    @GetMapping("/templates/insert")
    public String templateInsert(){
        return "template_insert";
    }
    //템플릿 생성 http GET 요청 (CREATE test용)

    @PostMapping("/templates")
    public String templatePost(Template template){
        templateService.templatePost(template);
        return "redirect:/templates";
    } //template Create API

    @GetMapping("/templates/modify/{id}")
    public String templateModify(@PathVariable("id") String id, Model model){
        //@PathVariable은 "/" 뒤에 물음표 없이 id가 붙어옴
        model.addAttribute("template", templateService.templateView(id));
        return "template_modify";
    } //변경 http GET 요청 (UPDATE Test용)

    @PostMapping("/templates/update/{id}")
    public String templateUpdate(@PathVariable("id") String id, Template template){
        Template template1 = templateService.templateView(id);
        template1.setTemplateName(template.getTemplateName());
        templateService.templateSave(template1);

        return "redirect:/templates";
    } //template Update API

    @GetMapping("/templates/view")
    //PK를 소유한 하나의 레코드 정보로 들어가기
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
