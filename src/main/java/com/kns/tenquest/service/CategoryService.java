package com.kns.tenquest.service;

import com.kns.tenquest.dto.controller.CategoryIdMapping;
import com.kns.tenquest.dto.controller.CategoryNameMapping;
import com.kns.tenquest.entity.Category;
import com.kns.tenquest.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service  // 레파지토리가 갖고있는 함수 써주는 부분
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();

    }

    public CategoryNameMapping getCategoryNameByCategoryId(int categoryId) {
        return categoryRepository.findCategoryNameByCategoryId(categoryId); // 여기까진 해당 아이디 가진 특정 객체만 반환함 .. 얘의 name 값을 반환하게 하려면.. .


        //return categoryRepository.findCategoryNameByCategoryId(categoryId);
    }

    public CategoryIdMapping getCategoryIdByCategoryName(String categoryName) {
        return categoryRepository.findCategoryIdByCategoryName(categoryName);
    }
}
