package com.abloom.domain.user.model

import java.time.LocalDate
import java.time.temporal.ChronoUnit

sealed interface MarriageState {
    data class BeforeMarriage(val daysUntilMarriage: Int) : MarriageState
    data object WeddingDay : MarriageState
    data class AfterMarriage(val daysSinceMarriage: Int) : MarriageState

    companion object {

        fun of(baseDate: LocalDate, marriageDate: LocalDate): MarriageState = when {
            baseDate.isBefore(marriageDate) -> BeforeMarriage(baseDate.betweenDaysWith(marriageDate))
            baseDate.isAfter(marriageDate) -> AfterMarriage(marriageDate.betweenDaysWith(baseDate))
            else -> WeddingDay
        }

        private fun LocalDate.betweenDaysWith(date: LocalDate): Int =
            ChronoUnit.DAYS.between(this, date).toInt()
    }
}
