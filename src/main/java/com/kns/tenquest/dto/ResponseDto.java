package com.kns.tenquest.dto;

public interface ResponseDto<E> {
    String status();
    int code();
    E data();
}
