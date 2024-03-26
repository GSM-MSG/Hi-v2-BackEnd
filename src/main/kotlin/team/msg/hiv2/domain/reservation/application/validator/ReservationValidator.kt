package team.msg.hiv2.domain.reservation.application.validator

import java.time.LocalDateTime

interface ReservationValidator {
    fun validateReservationTime(currentTime: LocalDateTime)
    fun validateReservationDay(currentTime: LocalDateTime)
}