package com.kns.tenquest.response;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public class ResponseJson<T> extends ResponseEntity<T> {

    public ResponseJson(HttpStatusCode status) {
        super(status);
    }

    public ResponseJson(T body, HttpStatusCode status) {
        super(body, status);
    }

    public ResponseJson(MultiValueMap<String, String> headers, HttpStatusCode status) {
        super(headers, status);
    }

    public ResponseJson(T body, MultiValueMap<String, String> headers, HttpStatusCode status) {
        super(body, headers, status);
    }

    public ResponseJson(T body, MultiValueMap<String, String> headers, int rawStatus) {
        super(body, headers, rawStatus);
    }
}
