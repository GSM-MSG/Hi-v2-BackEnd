package team.msg.hiv2.domain.homebase.application.usecase

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional
import team.msg.hiv2.domain.homebase.application.service.HomeBaseService
import team.msg.hiv2.domain.homebase.exception.AlreadyExistReservationException
import team.msg.hiv2.domain.homebase.exception.AlreadyReservedAtSamePeriodException
import team.msg.hiv2.domain.homebase.exception.NotReserveHomeBaseDayException
import team.msg.hiv2.domain.homebase.exception.TooManyUsersException
import team.msg.hiv2.domain.homebase.presentation.data.request.ReservationHomeBaseRequest
import team.msg.hiv2.domain.reservation.application.service.ReservationService
import team.msg.hiv2.domain.reservation.application.validator.ReservationValidator
import team.msg.hiv2.domain.reservation.domain.Reservation
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.exception.UserNotFoundException
import team.msg.hiv2.global.annotation.usecase.UseCase
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.util.*

//@UseCase
@Service
class ReserveHomeBaseUseCase(
    private val userService: UserService,
    private val reservationService: ReservationService,
    private val homeBaseService: HomeBaseService,
    private val reservationValidator: ReservationValidator
) {

    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = [Exception::class])
    fun execute(floor: Int, period: Int, homeBaseNumber: Int, request: ReservationHomeBaseRequest) {

        reservationValidator.validateReservationTime(LocalDateTime.now(), period)

        val dayOfWeek = reservationValidator.validateReservationDay(LocalDateTime.now())

        if (dayOfWeek != DayOfWeek.MONDAY && period == 7)
            throw NotReserveHomeBaseDayException()

        val homeBase = homeBaseService.queryHomeBaseByFloorAndPeriodAndHomeBaseNumber(floor, period, homeBaseNumber)

        if(reservationService.existsByHomeBaseId(homeBase.id))
            throw AlreadyExistReservationException()

        if (request.users.size > homeBase.maxCapacity)
            throw TooManyUsersException()

        val users = userService.queryAllUserById(request.users)

        if (request.users.size != users.size)
            throw UserNotFoundException()

        val homeBases = homeBaseService.queryHomeBaseByPeriod(period)
        val userIds = reservationService.queryAllReservationByHomeBaseIn(homeBases).flatMap { reservation -> reservation.userIds }

        if (userIds.any { it in request.users })
            throw AlreadyReservedAtSamePeriodException()

        reservationService.save(
            Reservation(
                id = UUID.randomUUID(),
                reason = request.reason,
                homeBaseId = homeBase.id,
                checkStatus = false,
                userIds = request.users
            )
        )
    }
}