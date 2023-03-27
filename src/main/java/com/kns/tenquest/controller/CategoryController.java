package com.kns.tenquest.controller;

import com.kns.tenquest.DtoList;
import com.kns.tenquest.ENV;
import com.kns.tenquest.dto.CategoryDto;
import com.kns.tenquest.dto.MemberDto;
import com.kns.tenquest.dto.ResponseDto;
import com.kns.tenquest.entity.Category;
import com.kns.tenquest.response.Response;
import com.kns.tenquest.response.ResponseStatus;
import com.kns.tenquest.service.CategoryService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController  // 레스트 api 생성 // 이거만 붙이면 /view/categories" 쳤을때 return "category_view" 이거만뜸.. .
@RequestMapping(ENV.API_PREFIX+"/categories")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoryController {

    @Autowired
    CategoryService categoryService;




//#################################################################

    //RestController 구현 부분 : 데이터만 프론트에 전달
    // **********************************GET ( 자료 찾아오기만 하는부분 )

        //카테고리 테이블 이름 적으면 테이블 전체값 보내주기
        //@ResponseBody// Jason 형태로 값을 프론트로 전달해줌
        @RequestMapping(value = "",method = RequestMethod.GET)
        public Response<CategoryDto> apiGetAllCategories() {
            ResponseStatus responseStatus = ResponseStatus.OK;
            DtoList<CategoryDto> categoryDtoList = categoryService.getAllCategories();


            return categoryDtoList.toResponse(responseStatus);

            } // 함수() 괄호 적기.. .
            //toResponse 완료 //postman 확인완료




        // category_id 를 받으면  해당하는 category_name 보내주기 (화면에 카테고리 이름 각각 표시할때 필요)

        @RequestMapping(value = "/name/categoryId", method = RequestMethod.GET)
        public Response<String> findCategoryNameByCategoryId(@RequestParam( name = "value",required = false, defaultValue = "0") int category_id ){

            String nullableString = categoryService.getCategoryNameByCategoryId(category_id).getCategoryName();
            ResponseStatus responseStatus = ResponseStatus.OK;

            if (nullableString.equals(ResponseStatus.NOT_FOUND.getStatus())){ //이해안됨
                responseStatus = ResponseStatus.NOT_FOUND;  //이해안됨
                nullableString = null;}

            return new ResponseDto<String>(responseStatus, nullableString).toResponse();

            }//toResponse 완료 //postman 확인완료





        // category_name 받으면  해당하는 category_id 보내주기 ( 카테고리 id 찾을때 필요)

        @RequestMapping(value="/categoryId/categoryName",method = RequestMethod.GET)
        public Response<Integer> findCategoryIdByCategoryName(@RequestParam(name="value",required = false,defaultValue = "") String category_name){

            Integer nullableInteger =  categoryService.getCategoryIdByCategoryName(category_name).getCategoryId();
            ResponseStatus responseStatus = ResponseStatus.OK;

            if (nullableInteger.equals(ResponseStatus.NOT_FOUND.getStatus())){ //이해안됨
                responseStatus = ResponseStatus.NOT_FOUND;  //이해안됨
                nullableInteger = null;}

            return new ResponseDto<Integer>(responseStatus, nullableInteger).toResponse();
        }//toResponse 완료 //postman  //없는거 적으면 500 오류뜸 ..수정필요






    //카테고리 추가하기 : POST  : 데이터 추가 : 현재는 개발자만 개발페이지(MySQL DATA BASE)에서 가능

    //카테고리 수정하기 : UPDATE : 데이터 수정: 현재는 개발자만 개발페이지(MySQL DATA BASE)에서 가능

    //카테고리 삭제하기 : DELETE : 데이터 삭제: 현재는 개발자만 개발페이지(MySQL DATA BASE)에서 가능

}
