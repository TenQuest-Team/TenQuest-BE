package com.kns.tenquest.dto;

import com.kns.tenquest.response.ResponseStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ReplyerResponseDto implements ResponseDto<ReplyerDto> {
    private String status;
    private int code;
    private ReplyerDto data;

    public ReplyerResponseDto(String status, int code, ReplyerDto data) {
        this.status = status;
        this.code = code;
        this.data = data;
    }

    public ReplyerResponseDto(ResponseStatus responseStatus, ReplyerDto data) {
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
    public ReplyerDto data() {
        return data;
    }
}
