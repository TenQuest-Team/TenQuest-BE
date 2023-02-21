package com.kns.tenquest.dto;

import com.kns.tenquest.response.ResponseStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
public class MemberResponseDto implements ResponseDto<MemberDto> {
    private String status;
    private int code;
    private MemberDto data;

    public MemberResponseDto(String status, int code, MemberDto data) {
        this.status = status;
        this.code = code;
        this.data = data;
        this.data.userInfo= null;
    }
    public MemberResponseDto(ResponseStatus responseStatus, MemberDto data) {
        this.status = responseStatus.getStatus();
        this.code = responseStatus.getCode();
        this.data = data;
        this.data.userInfo= null;
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
    public MemberDto data() {
        return data;
    }
}