package com.kns.tenquest.dto;

import com.kns.tenquest.response.Response_Deprecated;
import com.kns.tenquest.response.ResponseStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpHeaders;

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
    @Builder
    public ResponseDto(ResponseStatus responseStatus, T responseData) {
        this.status = responseStatus.getStatus();
        this.code = responseStatus.getCode();
        if (responseData != null)
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

    public ResponseDto(ResponseStatus responseStatus) {
        this.status = responseStatus.getStatus();
        this.code = responseStatus.getCode();
        this.data = null;
    }

    public Response_Deprecated<T> toResponse(){
    return new Response_Deprecated<>((T) new ResponseDto<T>(this.getStatus(),this.getCode(), data),
            new HttpHeaders(),
            this.getCode());
    }


}
