package com.kns.tenquest.dto;

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
