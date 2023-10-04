package com.kns.tenquest.response;

import lombok.Builder;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
@Deprecated
/**
 * 더 간단하고 직관적인 Response 클래스를 도입하기 위해 deprecated 처리
 */
public class Response_Deprecated<T> extends ResponseEntity<T> {

    public Response_Deprecated(HttpStatusCode status) {
        super(status);
    }

    public Response_Deprecated(T body, HttpStatusCode status) {
        super(body, status);
    }

    public Response_Deprecated(MultiValueMap<String, String> headers, HttpStatusCode httpStatusCode) {
        super(headers, httpStatusCode);
    }

    public Response_Deprecated(T body, MultiValueMap<String, String> headers, HttpStatusCode status) {
        super(body, headers, status);
    }

    @Builder
    public Response_Deprecated(T body, MultiValueMap<String, String> headers, int rawStatus) {
        super(body, headers, rawStatus);
    }
}
