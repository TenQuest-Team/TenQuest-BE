package com.kns.tenquest.controller;


import com.kns.tenquest.DtoList;
import com.kns.tenquest.ENV;
import com.kns.tenquest.dto.*;
import com.kns.tenquest.entity.Question;
import com.kns.tenquest.response.Response;
import com.kns.tenquest.response.ResponseStatus;
import com.kns.tenquest.service.MemberService;
import com.kns.tenquest.service.QuestionService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(ENV.API_PREFIX+"/questions")
@RestController // 어떤 요청이랑 어떤 함수(api)를 맵핑하면 좋을지 알려주는 역할
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class QuestionController {

    @Autowired
    QuestionService questionService;
//    @Autowired
//    MemberController memberController;
    @Autowired
    MemberService memberService;

    String developer = "root_user";

    //개발자의 MemberId(uuid) 선언
    String memberIdForDeveloper;




    //질문  객체 전체를 html 이용하여 화면에 표시하기  (확인)  : GET //개발자용


    //#################################################################

    //RestController 구현 부분 : 데이터만 프론트에 전달
    // **********************************GET ( 자료 찾아오기만 하는부분 )*************************************
  /*  public Response<CategoryDto> apiGetAllCategories() {
        com.kns.tenquest.response.ResponseStatus responseStatus = ResponseStatus.OK;
        DtoList<CategoryDto> categoryDtoList = categoryService.getAllCategories();


        return categoryDtoList.toResponse(responseStatus);*/

    //전체 질문 객체들 정보 보내주기

    @RequestMapping(value = "/{accessId}",method=RequestMethod.GET)

    public Response<QuestionDto>  apiGetAllQuestions(@PathVariable(name = "accessId") String accessId) { // 접근중인 사람의 id 받아오기 //default 가 developer 이면 안될듯..


        // 현재 접근중인 사람의 memberId : UUID(String)   가져오는 코드
        String accessMemberId =  memberService.getMemberIdByUserId(accessId); //accessId 를 매개변수로 받아서.. 해당 id의 uuid를 받아온다 ..

        //개발자의 MemberId(uuid) 찾기
        if (memberIdForDeveloper == null) {
            memberIdForDeveloper = memberService.getMemberIdByUserId(developer);
        }

            //전체 질문 객체들 보내주기1// 개발자용 ( 이상한 질문추가 됐는지 아닌지 확인할때 쓸수있을듯 )
        if (accessMemberId.equals(memberIdForDeveloper)) { // 멤버 id 가 developer 의 id와 동일한경우 ( 개발자가 접근하는경우)
            com.kns.tenquest.response.ResponseStatus responseStatus = ResponseStatus.OK;
            DtoList<QuestionDto> questionDtoList = questionService.getAllQuestions();

            return questionDtoList.toResponse(responseStatus);



        }else{
            //전체 질문 객체들 보내주기2// 사용자용
            //  질문 객체들 중에서도 accessMemberId  가  question_created_by 컬럼값 OR  memberIdForDeveloper 와 같은 경우만 반환한다
            DtoList<QuestionDto> allQuestionsForAccessor = new DtoList<QuestionDto>(); // 빈리스트 생성
            // 리스트에 추가
            allQuestionsForAccessor.addAll(questionService.getAllQuestionsByQuestionCreatedBy(accessMemberId));
            allQuestionsForAccessor.addAll( questionService.getAllQuestionsByQuestionCreatedBy(memberIdForDeveloper)); // 질문 추가한거보고 테스팅 해봐야할듯

            com.kns.tenquest.response.ResponseStatus responseStatus = ResponseStatus.OK;


            return allQuestionsForAccessor.toResponse(responseStatus);


        }


    } // toResponse(  )  로 변경 완료//postman 확인완료





    //카테고리 별 확인 //특정 카테고리에 해당하는 질문 content(String 글자) 확인하기 (외래키 question_category_id 이용) : GET : 데이터 가져와서 뿌려주기

    @RequestMapping(value="/contents/questionCategoryIdAndAccessId",method=RequestMethod.GET)
    public Response<DtoList> apiGetQuestionsInCategory(@RequestParam(name="questionCategoryId",required = false,defaultValue = "") int questionCategoryId,   // postman 확인완료
                                                  @RequestParam(name="accessId") String accessId){

        DtoList<Question> allQuestionsInCategory = new DtoList<Question>(); // 빈리스트 생성

        com.kns.tenquest.response.ResponseStatus responseStatus = ResponseStatus.OK;




        //사용자 정의 카테고리가  아닌경우(question_category_id != 0)
        if (questionCategoryId != 0){
            // 해당 question_category_id 컬럼 값과 같은 레코드 전체 값 반환  (나중에 question_content 컬럼값 만 반환하는게 나을지 물어봐야할듯
            allQuestionsInCategory.addAll( questionService.getQuestionContentByQuestionCategoryId(questionCategoryId));
            return allQuestionsInCategory.toResponse(responseStatus);
            //사용자 정의 카테고리인 경우 (question_category_id == 0)
            // question_created_by  와 accessMemberId 가 같은지 확인하고 같은경우만 반환한다  => questionCategoryId ==0 이고 question_created_by == accessId 인 레코드 반환

        }else{
                //접근하는 이용자의 MemberId를 매개변수로 전달받은 accessId(userId) 로 찾기
                String accessMemberId = memberService.getMemberIdByUserId(accessId);

            allQuestionsInCategory.addAll( questionService.getQuestionsByQuestionCategoryIdAndQuestionCreatedBy(questionCategoryId,accessMemberId));  // postman 확인완료
            return allQuestionsInCategory.toResponse(responseStatus);
        }



    } // postman 확인 했긴한데.. 0 번넣엇을대 잘안나옴..




    //question_id로 호출하면.. (UUID) 해당질문(question_content) 1개 보내줌 GET (각템플릿에서 호출하거나 할때 필요함)

    @RequestMapping(value="/content/questionId",method = RequestMethod.GET)
    public Response<String> apiGetQuestionContentByQuestionId(@RequestParam(name="value",required = false,defaultValue = "") String questionId){  //postman 확인 완료 // UUID 구현부분 좀 수정필요 .. .일단 String으로 해놓음
        String nullableString = questionService.getQuestionContentByQuestionId(questionId);
        ResponseStatus responseStatus = ResponseStatus.OK;

        if (nullableString.equals(ResponseStatus.NOT_FOUND.getStatus())){ //이해안됨
            responseStatus = ResponseStatus.NOT_FOUND;  //이해안됨
            nullableString = null;}

        return new ResponseDto<String>(responseStatus, nullableString).toResponse();

    } // postman 확인완료




     //*************************************POST *************************************
    //질문 추가하기 : POST  : 데이터 추가 : 개발자& 이용자



    @RequestMapping(value ="",method = RequestMethod.POST) //post 방식은 data 를 body 에 받아왹 때문에 @RequestParam 이 아닌 @RequestBody 어노테이션을 사용해야한다.
    public Response<Integer> apiSaveQuestion(@RequestBody QuestionSaveRequestDto requestDto ){

        int insertResult = questionService.save(requestDto);


        ResponseStatus responseStatus = ResponseStatus.CREATE_DONE;

        if (insertResult == ResponseStatus.CREATE_FAIL.getCode()){
            responseStatus = ResponseStatus.CREATE_FAIL;
        }

        return new ResponseDto<Integer>(responseStatus, insertResult).toResponse();

    }

    //질문 수정하기 : UPDATE : 데이터 수정: 개발자& 이용자

    //질문 삭제하기 : DELETE : 데이터 삭제: 일부는 개발자 & 일부는 이용자


}
