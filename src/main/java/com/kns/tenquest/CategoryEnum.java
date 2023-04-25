package com.kns.tenquest;

public enum CategoryEnum {
    USER_CREATED(0,"USER_CREATED"),
    PERSONALITY(1,"PERSONALITY"),
    APPEARANCE(2,"APPEARANCE"),
    RELATIONSHIP(3,"RELATIONSHIP");

    private final int id;
    private final String categoryName;

    CategoryEnum(int id,String categoryName) {
        this.id = id;
        this.categoryName=categoryName;
    }


    public int getId() {
        return id;
    }



    // CategoryEnum.fromId(1) 하면 CategoryEnum.PERSONALITY 반환하도록 하는 코드
    public static CategoryEnum fromId(int id) {
        for (CategoryEnum categoryEnum : CategoryEnum.values()) {
            if (categoryEnum.id == id) {
                return categoryEnum;
            }
        }
        throw new IllegalArgumentException("Invalid id: " + id);
    }
}


