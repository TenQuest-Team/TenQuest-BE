package com.kns.tenquest;

import com.kns.tenquest.dto.QuestionDto;
import com.kns.tenquest.dto.ServiceResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

@DisplayName("ServiceResult 테스트")
public class ServiceResultTest {


    /**
     * @author Woody_K
     * @apiNote ServiceResult의 code가 1인 경우 isSuccessful()가 true를 반환하는지 테스트
     */
    @Test
    @DisplayName("ServiceResult.isSuccessful() - code가 1인 경우 isSuccessful()가 true를 반환해야한다.")
    public void ServiceResultIsSuccessfulTest() {
        // given
        int serviceResultCode = ServiceResult.SUCCESS;

        // when
        ServiceResult serviceResult = new ServiceResult(ServiceResult.SUCCESS, "success", null);

        // then
        Assertions.assertTrue(serviceResult.isSuccessful());
    }

    /**
     * @author Woody_K
     * @apiNote ServiceResult의 code가 0인 경우 isFailed()가 true를 반환하는지 테스트
     */
    @Test
    @DisplayName("ServiceResult.isFailed() - code가 0인 경우 isFailed()가 true를 반환해야한다.")
    public void ServiceResultisFailedTest() {
        // given
        int serviceResultCode = ServiceResult.FAIL;

        // when
        ServiceResult serviceResult = new ServiceResult(serviceResultCode, "fail", null);

        // then
        Assertions.assertTrue(serviceResult.isFailed());
    }

    /**
     * @author Woody_K
     * @apiNote ServiceResult에 data가 null인 경우 getData()가 null을 반환하는지 테스트
     */
    @Test
    @DisplayName("ServiceResult.getData() - data가 null로 초기화된 경우 getData()가 null을 반환해야한다.")
    public void ServiceResultGetDataTestWithNullData() {
        // given
        Object mockData = null;

        // when
        ServiceResult serviceResult = new ServiceResult(ServiceResult.SUCCESS, "success", mockData);

        // then
        Assertions.assertNull(serviceResult.getData());
    }

    /**
     * @apiNote ServiceResult에 data가 null이 아닌 경우 getData()가 해당 데이터를 반환하는지 테스트
     * @author Woody_K
     */

    @Test
    @DisplayName("ServiceResult.getData() 테스트 - data가 null이 아닌 경우 getData()가 해당 데이터를 반환해야한다.")
    public void ServiceResultGetDataTest() {
        // given
        String mockQuestionId = UUID.randomUUID().toString().replace("-", "");
        String mockQuestionContent = "mockQuestionContent";
        int mockQuestionCategoryId = 99;

        QuestionDto questionDto = QuestionDto.builder()
                .questionId(mockQuestionId)
                .questionContent(mockQuestionContent)
                .questionCategoryId(mockQuestionCategoryId)
                .build();

        // when
        ServiceResult serviceResult =
                new ServiceResult(ServiceResult.SUCCESS, "success", questionDto);

        // then
        Assertions.assertEquals(mockQuestionId, ((QuestionDto) serviceResult.getData()).getQuestionId());
        Assertions.assertEquals(mockQuestionContent, ((QuestionDto) serviceResult.getData()).getQuestionContent());
        Assertions.assertEquals(mockQuestionCategoryId, ((QuestionDto) serviceResult.getData()).getQuestionCategoryId());

    }

}
