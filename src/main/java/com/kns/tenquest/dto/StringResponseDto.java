package com.kns.tenquest.dto;

import com.kns.tenquest.response.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StringResponseDto implements ResponseDto<String> {
    private String status;
    private int code;
    private String data;

    public StringResponseDto(String status, int code, String data) {
        this.status = status;
        this.code = code;
        this.data = data;
    }
    public StringResponseDto(ResponseStatus responseStatus, String data) {
        this.status = responseStatus.getStatus();
        this.code = responseStatus.getCode();
        this.data = data;
    }

    @Override
    public String status() {
        return status;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String data() {
        return data;
    }
}
