package controller;

import entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.MemberService;

import java.util.List;

@Controller
public class MemberController {
    @Autowired
    MemberService memberService;
    @ResponseBody
    @GetMapping("/view/members")
    public List<Member> memberView(){
        // Temporarily implemented. Just for test.
        return memberService.getAllMembers();
    }
}
