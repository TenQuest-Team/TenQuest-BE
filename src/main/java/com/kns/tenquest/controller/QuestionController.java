package com.kns.tenquest.controller;


import com.kns.tenquest.entity.Question;
import com.kns.tenquest.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller // 어떤 요청이랑 어떤 함수(api)를 맵핑하면 좋을지 알려주는 역할
public class QuestionController {

    @Autowired
    QuestionService questionService;
    @Autowired
    MemberController memberController;

    //개발자의 MemberId(uuid) 찾기
    String memberIdForDeveloper = memberController.apiGetMemberIdByUserId("developer"); // NullPointExeption 발생한다는데 ???

    //이거 요청한 사람의 MemberId(uuid) 찾기




    //질문  객체 전체를 html 이용하여 화면에 표시하기  (확인)  : GET //개발자용
    @RequestMapping(value = "/view/questions", method = RequestMethod.GET)
    public String questionView(Model model){

        List<Question> questionList = questionService.getAllQuestions();
        model.addAttribute("questionList", questionList);
        return "question_view";

    }

    //#################################################################

    //RestController 구현 부분 : 데이터만 프론트에 전달
    // **********************************GET ( 자료 찾아오기만 하는부분 )*************************************

    //전체 질문 객체들 정보 보내주기
    @ResponseBody
    @RequestMapping(value = "get/questions",method=RequestMethod.GET) // 접근 아이디 받아서 그걸로 비교하면 될듯
    public List<Question> apiGetAllQuestions(@RequestParam(name = "accessId",defaultValue ="developer") String accessId) { // 접근중인 사람의 id 받아오기


        // 현재 접근중인 사람의 memberId : UUID(String)   가져오는 코드
        String accessMemberId =  memberController.apiGetMemberIdByUserId(accessId);


            //전체 질문 객체들 보내주기1// 개발자용 ( 이상한 질문추가 됐는지 아닌지 확인할때 쓸수있을듯 )
        if (accessMemberId.equals(memberIdForDeveloper)) { // 멤버 id 가 developer 의 id와 동일한경우 ( 개발자가 접근하는경우)
            return questionService.getAllQuestions(); // 모든 질문 객체들 보냄

        }else{
            //전체 질문 객체들 보내주기2// 사용자용
            //  질문 객체들 중에서도 accessMemberId  가  question_created_by 컬럼값 OR  memberIdForDeveloper 와 같은 경우만 반환한다
            List<Question> allQuestionsForAccessor = new ArrayList<Question>(); // 빈리스트 생성
            // 리스트에 추가
            allQuestionsForAccessor.addAll(questionService.getAllQuestionsByQuestionCreatedBy(accessMemberId));
            allQuestionsForAccessor.addAll( questionService.getAllQuestionsByQuestionCreatedBy(memberIdForDeveloper));
            return allQuestionsForAccessor;

        }


    }




    //카테고리 별 확인 //특정 카테고리에 해당하는 질문 확인하기 (외래키 question_category_id 이용) : GET : 데이터 가져와서 뿌려주기
    @ResponseBody
    @RequestMapping(value="get/questions/category",method=RequestMethod.GET)


    public List<Question> apiGetQuestionsInCategory(@RequestParam(name="questionCategoryId",required = false,defaultValue = "") int questionCategoryId,
                                                    @RequestParam(name="accessId") String accessId){
            //사용자 지정  아닌경우(question_category_id != 1)
        if (questionCategoryId != 1){
            // 해당 question_category_id 컬럼 값과 같은 레코드 전체 값 반환  (나중에 question_content 컬럼값 만 반환하는게 나을지 물어봐야할듯
                return questionService.getQuestionsByQuestionCategoryId(questionCategoryId);

            //사용자 지정인 경우 (question_category_id != 1)
            // question_created_by  와 accessMemberId 가 같은지 확인하고 같은경우만 반환한다  => questionCategoryId ==1 이고 question_created_by == accessId 인 레코드 반환

        }else{
                //접근하는 이용자의 MemberId를 매개변수로 전달받은 accessId(userId) 로 찾기
                String accessMemberId = memberController.apiGetMemberIdByUserId(accessId);



                return null; // 구현예정

        }














    }




    //question_id로 호출하면.. (UUID) 해당질문(question_content) 1개 보내줌 GET (각템플릿에서 호출하거나 할때 필요함)




    //질문지 내용만 확인 하는 거



     //*************************************POST *************************************
    //질문 추가하기 : POST  : 데이터 추가 : 개발자& 이용자

    //질문 수정하기 : UPDATE : 데이터 수정: 개발자& 이용자

    //질문 삭제하기 : DELETE : 데이터 삭제: 일부는 개발자 & 일부는 이용자
}
