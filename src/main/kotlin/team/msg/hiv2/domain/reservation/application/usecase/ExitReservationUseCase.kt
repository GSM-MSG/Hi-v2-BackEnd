package team.msg.hiv2.domain.reservation.application.usecase

import team.msg.hiv2.domain.reservation.application.service.ReservationService
import team.msg.hiv2.domain.reservation.exception.ForbiddenExitReservationException
import team.msg.hiv2.domain.user.application.service.UserService
import team.msg.hiv2.domain.user.application.validator.UserValidator
import team.msg.hiv2.global.annotation.usecase.UseCase
import java.util.UUID

@UseCase
class ExitReservationUseCase(
    private val userService: UserService,
    private val reservationService: ReservationService,
) {

    fun execute(reservationId: UUID){
        val reservation = reservationService.queryReservationById(reservationId)

        val currentUser = userService.queryCurrentUser()

        if(reservation.representativeId == currentUser.id)
            throw ForbiddenExitReservationException()

        userService.save(currentUser.copy(reservationId = null))
    }
}