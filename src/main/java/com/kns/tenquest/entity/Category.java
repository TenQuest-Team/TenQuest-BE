package com.kns.tenquest.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//@Data // setter 없도록 수정필요 -> 생성자랑 게터는 만들어야함
@Getter
@NoArgsConstructor
@Table(name="category_table")
@Entity
public class Category {
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    @Id
    @Column(name="category_id")
    private int categoryId;

    @Column(name="category_name")
    private String categoryName;

    @Builder
    public Category(int categoryId, String categoryName){
        this.categoryId=categoryId;
        this.categoryName=categoryName;
    }

}
