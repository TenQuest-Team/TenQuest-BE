package com.kns.tenquest.dto;

//DTO 는  getter /setter 메소드만 가진다

import com.kns.tenquest.entity.Category;
import com.kns.tenquest.entity.Member;
import com.kns.tenquest.response.Responseable;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Builder
@Getter
@Setter
public class CategoryDto implements DataTransferObject<Category>, Responseable<CategoryDto> {
    public int categoryId;  //이거왜 private/ public 되면 안되는지
    public String categoryName;



    @Override
    public Category toEntity() {

    Category category = Category.builder().categoryId(categoryId).categoryName(categoryName).build();
    return  category;
    }

    @Override
    public DataTransferObject<Category> toDto(Category category) {
        return new CategoryDto(category.getCategoryId(),category.getCategoryName());
    }

}