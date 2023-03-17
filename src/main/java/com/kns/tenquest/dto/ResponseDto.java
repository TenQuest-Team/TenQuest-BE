package com.kns.tenquest.dto;

import com.kns.tenquest.response.Response;
import com.kns.tenquest.response.ResponseStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpHeaders;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@SuppressWarnings("unchecked")
public class ResponseDto<T> {
    private String status;
    private int code;
    private T data;

//    public ResponseDto(ResponseStatus responseStatus, List<T> responseDataList) {
//        this.status = responseStatus.getStatus();
//        this.code = responseStatus.getCode();
//        this.data = responseDataList;
//    }
    public ResponseDto(ResponseStatus responseStatus, T responseData) {
        this.status = responseStatus.getStatus();
        this.code = responseStatus.getCode();
        if (responseData instanceof T)
        this.data = responseData;
    }

//    public ResponseDto(String status, int code, List<T> responseDataList) {
//        this.status = status;
//        this.code = code;
//        this.data = responseDataList;
//    }

    public ResponseDto(String status, int code, T responseData) {
        this.status = status;
        this.code = code;
        this.data = responseData;
    }

    public Response<T> toResponse(){
    return new Response<>((T) new ResponseDto<T>(this.getStatus(),this.getCode(), data),
            new HttpHeaders(),
            this.getCode());
    }


}
