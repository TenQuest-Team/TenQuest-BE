package com.kns.tenquest.response;

import com.kns.tenquest.entity.Member;

public class MemberResponse implements ApiResponse<Member>{
    private String statusDescription;
    private int statusCode;
    private Member responseData;
    @Override
    public String statusDescription() {
        return this.statusDescription;
    }

    @Override
    public int statusCode() {
        return this.statusCode;
    }

    @Override
    public Member responseData() {
        return this.responseData;
    }

    @Override
    public String getResponse() {
        // [!] to be implemented later
        String res=
                "{\"statusCode\": \""+ this.statusCode() + "\"," +
                        "\"statusDescription\": \""+ this.statusDescription() + "\"" +
                        "}";
        return res;
    }
}
