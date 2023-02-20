package com.kns.tenquest.response;

public interface ApiResponse<E> {
    public String statusDescription();
    public int statusCode();
    public E responseData();
    public String getResponse();
}
