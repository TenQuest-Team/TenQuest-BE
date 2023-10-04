package com.kns.tenquest.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
public class ServiceResult {
    /**
     * @author Woody_K
     * @apiNote
     * Service 클래스들의 반환값을 통일하기 위한 클래스이다.<br>
     * Service 클래스의 각 method들은 `ServiceResult` 객체를 반환한다.<br>
     * - code : 1(성공), 0(실패)<br>
     * - message : 서비스 수행 결과에 대한 메시지<br>
     * - data : 반환할 데이터가 없는 경우 null, 있을 경우 해당 데이터<br>
     */

    public static final int SUCCESS = 1;
    public static final int FAIL = 0;

    public int code;
    public String message;
    public Object data;

    public ServiceResult(int code, String message, Object data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccessful(){
        return this.code == SUCCESS;
    }

    public boolean isFailed(){
        return this.code == FAIL;
    }

    public ServiceResult success(){
        this.code = SUCCESS;
        this.message = "success";
        return this;
    }

    public ServiceResult success(Object data){
        this.code = SUCCESS;
        this.message = "success";
        this.data = data;
        return this;
    }

    public ServiceResult fail(){
        this.code = FAIL;
        this.message = "fail";
        return this;
    }

    public ServiceResult code(int code){
        this.code = code;
        return this;
    }

    public ServiceResult message(String message){
        this.message = message;
        return this;
    }


    public ServiceResult data(Object data){
        this.data = data;
        return this;
    }
}
