package team.msg.hiv2.domain.reservation.application.usecase

import team.msg.hiv2.domain.homebase.application.service.HomeBaseService
import team.msg.hiv2.domain.homebase.exception.AlreadyExistReservationException
import team.msg.hiv2.domain.homebase.exception.TooManyUsersException
import team.msg.hiv2.domain.reservation.application.service.ReservationService
import team.msg.hiv2.domain.reservation.application.validator.ReservationValidator
import team.msg.hiv2.domain.reservation.presentation.data.request.UpdateReservationRequest
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.exception.UserNotFoundException
import team.msg.hiv2.global.annotation.usecase.UseCase
import java.time.LocalDateTime
import java.util.*

@UseCase
class UpdateReservationUseCase(
    private val reservationService: ReservationService,
    private val userService: UserService,
    private val homeBaseService: HomeBaseService,
    private val reservationValidator: ReservationValidator
) {

    fun execute(reservationId: UUID, request: UpdateReservationRequest) {

        val reservation = reservationService.queryReservationById(reservationId)

        val homeBase = homeBaseService.queryHomeBaseById(reservation.homeBaseId)

        reservationValidator.validateReservationTime(LocalDateTime.now(), homeBase.period)

        if (request.users.size > homeBase.maxCapacity)
            throw TooManyUsersException()

        val homeBases = homeBaseService.queryHomeBaseByPeriod(homeBase.period)
        val userIds = reservationService.queryAllReservationByHomeBaseIn(homeBases).flatMap { reservation -> reservation.userIds }

        if ((userIds.subtract(reservation.userIds.toSet()).any { it in request.users }))
            throw AlreadyExistReservationException()

        val users = userService.queryAllUserById(request.users)

        if (request.users.size != users.size)
            throw UserNotFoundException()

        reservationService.save(reservation.copy(reason = request.reason, userIds = users.map { it.id }.toMutableList()))
    }
}