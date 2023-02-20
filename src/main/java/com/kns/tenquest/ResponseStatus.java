package com.kns.tenquest;

public enum ResponseStatus {

    CREATE_DONE(201),
    CREATE_FAIL(409);

    private final int code;
    ResponseStatus(int code) {
        this.code = code;
    }
    public int getCode(){
        return this.code;
    }
}
