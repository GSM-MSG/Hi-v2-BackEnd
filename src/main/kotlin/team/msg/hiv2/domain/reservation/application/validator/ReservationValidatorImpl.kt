package team.msg.hiv2.domain.reservation.application.validator

import org.springframework.stereotype.Component
import team.msg.hiv2.domain.homebase.exception.NotReserveHomeBaseHourException
import java.time.DayOfWeek
import java.time.LocalDateTime

@Component
class ReservationValidatorImpl : ReservationValidator {

    override fun validateReservationTime(currentTime: LocalDateTime, period: Int) {
        val hour = currentTime.hour
        val minute = currentTime.minute

        /*
         오전 8시 10분부터 오후 8시 30분까지만 예약 가능
         */
        if (hour < 8 || (hour == 8 && minute < 10) || hour > 20 || (hour == 20 && minute > 30))
            throw NotReserveHomeBaseHourException()
    }

    override fun validateReservationDay(currentTime: LocalDateTime) {
        val dayOfWeek = currentTime.dayOfWeek

        // 금요일, 토요일, 일요일은 예약 불가능
        if (dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY)
            throw NotReserveHomeBaseHourException()
    }

}