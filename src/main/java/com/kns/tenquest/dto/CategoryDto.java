package com.kns.tenquest.dto;

//DTO 는  getter /setter 메소드만 가진다

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Getter
@Setter
public class CategoryDto {
    private int categoryId;
    private String categoryName;
}
