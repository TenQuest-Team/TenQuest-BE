package com.kns.tenquest.response;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum ResponseStatus {
    CREATE_DONE("CREATE_DONE",201),
    CREATE_FAIL("CREATE_FAIL" ,409),
    NOT_ACCEPTABLE("NOT_ACCEPTABLE", 406),
    NOT_FOUND("NOT_FOUND", 404),
    OK("OK", 200);

    private final String status;
    private final int code;

    ResponseStatus(String des, int code) {
        this.status = des;
        this.code = code;
    }

}
