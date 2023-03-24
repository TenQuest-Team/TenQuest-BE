package com.kns.tenquest.jwt;
public interface JwtProperties {
    String SECRET = "Woody";
    int EXPIRATION_TIME = 864000000; // 10 day (1/1000 sec)
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}