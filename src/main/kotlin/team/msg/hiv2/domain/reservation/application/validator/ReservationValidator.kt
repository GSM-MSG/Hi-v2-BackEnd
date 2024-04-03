package team.msg.hiv2.domain.reservation.application.validator

import java.time.DayOfWeek
import java.time.LocalDateTime

interface ReservationValidator {
    fun validateReservationTime(currentTime: LocalDateTime, period: Int)
    fun validateReservationDay(currentTime: LocalDateTime): DayOfWeek
}