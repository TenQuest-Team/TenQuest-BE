package com.kns.tenquest.repository;

import com.kns.tenquest.dto.mapping.CategoryIdMapping;
import com.kns.tenquest.dto.mapping.CategoryNameMapping;
import com.kns.tenquest.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    CategoryNameMapping findCategoryNameByCategoryId(int categoryId);
    CategoryIdMapping findCategoryIdByCategoryName(String categoryName);
}
