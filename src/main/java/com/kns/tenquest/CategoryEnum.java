package com.kns.tenquest;

public enum CategoryEnum {

    PERSONALITY(1),
    APPEARANCE(2),
    RELATIONSHIP(3);

    private final int id;

    CategoryEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static CategoryEnum fromId(int id) {   // CategoryEnum.fromId(1) 하면 CategoryEnum.PERSONALITY 반환함
        for (CategoryEnum categoryEnum : CategoryEnum.values()) {
            if (categoryEnum.id == id) {
                return categoryEnum;
            }
        }
        throw new IllegalArgumentException("Invalid id: " + id);
    }
}


