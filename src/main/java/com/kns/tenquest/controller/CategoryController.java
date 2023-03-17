package com.kns.tenquest.controller;

import com.kns.tenquest.entity.Category;
import com.kns.tenquest.service.CategoryService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

//@RestController  // 레스트 api 생성 // 이거만 붙이면 /view/categories" 쳤을때 return "category_view" 이거만뜸.. .
@Controller
public class CategoryController {

    @Autowired
    CategoryService categoryService;


    //카테고리 객체 이름 & id  전체를 html 이용하여 화면에 표시하기 : GET  : 데이터 가져와서 뿌려주기
    @RequestMapping(value="/view/categories",method= RequestMethod.GET)
    public String categoryView(@NotNull Model model){  //Model 객체.. 스프링이 알아서 만들어줌
        // Temporarily implemented. Just for test.
        List<Category> categoryList = categoryService.getAllCategories();
        model.addAttribute("categoryList", categoryList); // 바로윗줄의 객체를 추가 ..
        return "category_view"; // html 페이지 인듯
    }

//#################################################################

    //RestController 구현 부분 : 데이터만 프론트에 전달
    // **********************************GET ( 자료 찾아오기만 하는부분 )

        //카테고리 테이블 이름 적으면 테이블 전체값 보내주기
        @ResponseBody // Jason 형태로 값을 프론트로 전달해줌
        @RequestMapping(value = "/get/categories",method = RequestMethod.GET)
        public List<Category> apiGetAllCategories() {return categoryService.getAllCategories();} // 함수() 괄호 적기.. .




        // category_id 를 받으면  해당하는 category_name 보내주기 (화면에 카테고리 이름 각각 표시할때 필요)
        @ResponseBody
        @RequestMapping(value = "/get/category/name", method = RequestMethod.GET)
        public String findCategoryNameByCategoryId(@RequestParam( name = "categoryId",required = false, defaultValue = "0") int category_id ){
            return categoryService.getCategoryNameByCategoryId(category_id).getCategoryName(); //뒤에있는말 틀렸음.. 레파지토리에서 구현해줘야함..//이건 안만들어도 있는거임. .근데 해당 아이디를 가진 객체 자체가 나오는듯? Name 이 나오는건가..안되면 엔티티일부값 가져오기 참조하기
            }





        // category_name 받으면  해당하는 category_id 보내주기 ( 카테고리 id 찾을때 필요)
        @ResponseBody
        @RequestMapping(value="/get/category/id",method = RequestMethod.GET)
        public int findCategoryIdByCategoryName(@RequestParam(name="categoryName",required = false,defaultValue = "") String category_name){
            return categoryService.getCategoryIdByCategoryName(category_name).getCategoryId(); // 수정필요한지 확인하기..
        }







    //카테고리 추가하기 : POST  : 데이터 추가 : 현재는 개발자만 개발페이지(MySQL DATA BASE)에서 가능

    //카테고리 수정하기 : UPDATE : 데이터 수정: 현재는 개발자만 개발페이지(MySQL DATA BASE)에서 가능

    //카테고리 삭제하기 : DELETE : 데이터 삭제: 현재는 개발자만 개발페이지(MySQL DATA BASE)에서 가능

}
