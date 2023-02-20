package com.kns.tenquest;

public enum ResponseStatus {

    CREATE_DONE(201),
    CREATE_FAIL(409);

    private final int code;
    ResponseStatus(int statusCode) {
        this.code = statusCode;
    }
    int getCode(){
        return this.code;
    }
}
