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
}
