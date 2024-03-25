package com.abloom.domain.user.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.time.LocalDate
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class MarriageStateTest {

    @ParameterizedTest
    @MethodSource
    fun `MarriageState 팩토리 메서드를 사용할 때 기준일이 결혼일보다 더 이전이라면 남은 일 수와 함께 BeforeMarraige 객체를 반환한다`(
        baseDate: LocalDate,
        marriageDate: LocalDate,
        daysUntilMarriage: Int
    ) {
        val marriageState = MarriageState.of(baseDate, marriageDate)

        assertThat(marriageState).isInstanceOf(MarriageState.BeforeMarriage::class.java)
        val beforeMarriage = marriageState as MarriageState.BeforeMarriage
        assertThat(beforeMarriage.daysUntilMarriage).isEqualTo(daysUntilMarriage)
    }

    private fun `MarriageState 팩토리 메서드를 사용할 때 기준일이 결혼일보다 더 이전이라면 남은 일 수와 함께 BeforeMarraige 객체를 반환한다`(): Stream<Arguments> =
        Stream.of(
            Arguments.of(LocalDate.of(2024, 3, 25), LocalDate.of(2024, 3, 26), 1),
            Arguments.of(LocalDate.of(2024, 3, 25), LocalDate.of(2025, 3, 25), 365),
        )

    @ParameterizedTest
    @MethodSource
    fun `MarriageState 팩토리 메서드를 사용할 때 기준일이 결혼일보다 더 이후라면 지난 일 수와 함께 AfterMarriage 객체를 반환한다`(
        baseDate: LocalDate,
        marriageDate: LocalDate,
        daysSinceMarriage: Int,
    ) {
        val marriageState = MarriageState.of(baseDate, marriageDate)

        assertThat(marriageState).isInstanceOf(MarriageState.AfterMarriage::class.java)
        val afterMarriage = marriageState as MarriageState.AfterMarriage
        assertThat(afterMarriage.daysSinceMarriage).isEqualTo(daysSinceMarriage)
    }

    private fun `MarriageState 팩토리 메서드를 사용할 때 기준일이 결혼일보다 더 이후라면 지난 일 수와 함께 AfterMarriage 객체를 반환한다`(): Stream<Arguments> =
        Stream.of(
            Arguments.of(LocalDate.of(2024, 3, 25), LocalDate.of(2024, 3, 24), 1),
            Arguments.of(LocalDate.of(2024, 3, 25), LocalDate.of(2023, 3, 25), 366),
        )

    @Test
    fun `MarriageState 팩토리 메서드를 사용할 때 기준일과 결혼일이 같다면 WeddingDay 객체를 반환한다`() {
        val baseDate = LocalDate.now()
        val marriageDate = LocalDate.now()

        val marriageState = MarriageState.of(baseDate, marriageDate)

        assertThat(marriageState).isInstanceOf(MarriageState.WeddingDay::class.java)
    }
}
