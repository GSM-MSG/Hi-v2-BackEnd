package team.msg.hiv2.domain.reservation.application.usecase

import team.msg.hiv2.domain.reservation.application.service.ReservationService
import team.msg.hiv2.domain.reservation.application.spi.ReservationPort
import team.msg.hiv2.domain.reservation.exception.ForbiddenCommandReservationException
import team.msg.hiv2.domain.reservation.exception.ReservationNotFoundException
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.application.spi.UserPort
import team.msg.hiv2.domain.user.application.validator.UserValidator
import team.msg.hiv2.domain.user.domain.constant.UseStatus
import team.msg.hiv2.global.annotation.usecase.UseCase
import java.util.UUID

@UseCase
class DeleteReservationUseCase(
    private val reservationService: ReservationService,
    private val userService: UserService,
    private val userValidator: UserValidator
) {

    fun execute(reservationId: UUID){
        val reservation = reservationService.queryReservationById(reservationId)
        val users = userService.queryAllUserByReservation(reservation)
        val currentUser = userService.queryCurrentUser()

        userValidator.checkRepresentative(currentUser, reservation)

        userService.saveAll(users.map { it.copy(useStatus = UseStatus.AVAILABLE) })
        reservationService.delete(reservation)
    }
}