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
    private List<T> responseDataList = new ArrayList<>();

    public ResponseDto(ResponseStatus responseStatus, List<T> responseDataList) {
        this.status = responseStatus.getStatus();
        this.code = responseStatus.getCode();
        this.responseDataList = responseDataList;
    }
    public ResponseDto(ResponseStatus responseStatus, T responseData) {
        this.status = responseStatus.getStatus();
        this.code = responseStatus.getCode();
        if (responseData instanceof T)
        this.responseDataList.add(responseData);
    }

    public ResponseDto(String status, int code, List<T> responseDataList) {
        this.status = status;
        this.code = code;
        this.responseDataList = responseDataList;
    }

    public Response<T> toResponse(){
    return new Response<T>((T) new ResponseDto<T>(this.getStatus(),this.getCode(), this.responseDataList),
            new HttpHeaders(),
            this.getCode());
    }


}
