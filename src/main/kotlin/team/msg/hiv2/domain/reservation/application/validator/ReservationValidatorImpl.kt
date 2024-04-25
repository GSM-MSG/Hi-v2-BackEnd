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

        when (period) {
            7 -> if ((hour == 3 && minute > 30) || (hour == 4 && minute < 20)) throw NotReserveHomeBaseHourException()
            8 -> if ((hour == 4 && minute > 40) || (hour == 5 && minute < 30)) throw NotReserveHomeBaseHourException()
            9 -> if ((hour == 5 && minute > 40) || (hour == 6 && minute < 30)) throw NotReserveHomeBaseHourException()
            10 -> if ((hour == 7 && minute > 30) || (hour == 8 && minute < 20)) throw NotReserveHomeBaseHourException()
            11 -> if ((hour == 8 && minute > 20) || (hour == 9 && minute < 20)) throw NotReserveHomeBaseHourException()
        }
    }

    // 금요일, 토요일, 일요일은 예약 불가능
    override fun validateReservationDay(currentTime: LocalDateTime): DayOfWeek {
        val dayOfWeek = currentTime.dayOfWeek

        if (dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY)
            throw NotReserveHomeBaseHourException()

        return dayOfWeek
    }
}