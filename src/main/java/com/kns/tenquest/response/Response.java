package com.kns.tenquest.response;

public class Response {
    public int code;
    public String message;
    public Object data;

    public Response code(int code){
        this.code = code;
        return this;
    }

    public Response message(String message){
        this.message = message;
        return this;
    }

    public Response data(Object data){
        this.data = data;
        return this;
    }
}

