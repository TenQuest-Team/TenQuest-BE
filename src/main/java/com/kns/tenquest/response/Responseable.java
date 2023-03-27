package com.kns.tenquest.response;

import com.kns.tenquest.dto.DataTransferObject;
import com.kns.tenquest.dto.ResponseDto;
import com.kns.tenquest.response.Response;
import com.kns.tenquest.response.ResponseStatus;
@SuppressWarnings("unchecked")
public interface Responseable<E extends DataTransferObject>{
    /**
     * @author woody
     * @since JDK 1.8
     * @param responseStatus 클라이언트의 요청에 대한 처리 결과를 반영하기 위한 파라미터 입니다.
     *                       [예시]클라이언트의 요청이 정상적으로 처리된 경우
     *                          ResponseStatus.OK
     *
     *                       [예시]클라이언트가 찾고자하는 결과가 없어서 반환할 수 없는 경우
     *                          ResponseStatus.NOT_FOUND
     *
     *                       ** ResponseStatus class 참고
     * @return Response Object를 리턴합니다.
     */
    default Response<E> toResponse(ResponseStatus responseStatus){
        return new ResponseDto<E>(responseStatus, (E)this).toResponse();
    };

    default Response<E> toResponse(){
        return new ResponseDto<E>(ResponseStatus.OK, (E)this).toResponse();
    };
}