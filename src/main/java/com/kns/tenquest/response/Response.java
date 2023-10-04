package com.kns.tenquest.response;

import lombok.NoArgsConstructor;


/**
 * @apiNote
 * 현재는 ResponseEntity를 상속하지 않기 때문에,<br>
 * Response 클래스의 필드가 가진 code는 실제 Http Message 상에서 나타나는 코드가 아님<br>
 * 빠르게 기존 코드 변경을 위해 임시로 간단하게 구성하였음
 * 추후 변경할 예정<br>
 *
 * @author WoodyK
 */
@NoArgsConstructor
public class Response {
    public int code;
    public String message;
    public Object data;

    public Response Ok(){
        this.code = ResponseStatus.OK.getCode();
        this.message = ResponseStatus.OK.getStatus();
        return this;
    }

    public Response Ok(Object data){
        this.code = ResponseStatus.OK.getCode();
        this.message = ResponseStatus.OK.getStatus();
        this.data = data;
        return this;
    }

    public Response BadRequest(){
        this.code = ResponseStatus.BAD_REQUEST.getCode();
        this.message = ResponseStatus.BAD_REQUEST.getStatus();
        return this;
    }

    public Response code(int code){
        this.code = code;
        return this;
    }

    public Response message(String message){
        this.message = message;
        return this;
    }

    public Response data(Object data){
        this.data = data;
        return this;
    }
}

