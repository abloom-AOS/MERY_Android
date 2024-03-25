package com.abloom.domain.qna.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class ResponseResultTest {

    @Test
    fun `ResponseResult 팩토리 메서드를 사용할 때 두 유저의 반응이 둘 다 긍정적이라면 DOING_WELL을 반환한다`() {
        val user1Response = Response.GOOD
        val user2Response = Response.BETTER_KNOW

        val responseResult = ResponseResult.of(user1Response, user2Response)

        assertThat(responseResult).isEqualTo(ResponseResult.DOING_WELL)
    }

    @Test
    fun `ResponseResult 팩토리 메서드를 사용할 때 어떤 유저의 반응이 LETS_TALK라면 MORE_TALK를 반환한다`() {
        val user1Response = Response.LETS_FIND
        val user2Response = Response.LETS_TALK

        val responseResult = ResponseResult.of(user1Response, user2Response)

        assertThat(responseResult).isEqualTo(ResponseResult.MORE_TALK)
    }

    @Test
    fun `ResponseResult 팩토리 메서드를 사용할 때 모든 유저의 반응이 LETS_TALK가 아니고 어떤 유저의 반응이 LETS_FIND라면 MORE_FIND를 반환한다`() {
        val user1Response = Response.LETS_FIND
        val user2Response = Response.BETTER_KNOW

        val responseResult = ResponseResult.of(user1Response, user2Response)

        assertThat(responseResult).isEqualTo(ResponseResult.MORE_FIND)
    }

    @ParameterizedTest
    @MethodSource("allCaseResponse")
    fun `ResponseResult 팩토리 메서드를 사용할 때 모든 경우의 수에서도 에러가 발생하지 않는다`(
        user1Response: Response,
        user2Response: Response
    ) {
        assertDoesNotThrow { ResponseResult.of(user1Response, user2Response) }
    }

    private fun allCaseResponse(): Stream<Arguments> = Response.entries.flatMap { user1Response ->
        Response.entries.map { user2Response ->
            Arguments.of(user1Response, user2Response)
        }
    }.stream()
}

